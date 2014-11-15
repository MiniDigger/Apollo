package io.lordlambda.apollo.io;

import io.lordlambda.apollo.Apollo;
import org.apache.log4j.Level;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Creator: LordLambda
 * Date: 11/13/14.
 * Project: Apollo
 * Usage: Parse XML config files
 */
public class XMLConfiguration extends DefaultHandler {

    /////////////////////////////////////////////////////////////
    //  Variables
    //
    //      Class Variables
    //          xc - The static instance of this manager.
    //          map - A linked hash map of loaded documents,
    //              and their names.
    //          dbf - A instance of a document builder factory
    //              for xml parsing.
    //
    /////////////////////////////////////////////////////////////
    static XMLConfiguration xc;
    LinkedHashMap<String, Document> map;
    DocumentBuilderFactory dbf;

    /**
     * Default constructor.
     */
    public XMLConfiguration() {
        xc = this;
        map = new LinkedHashMap<String, Document>();
        dbf = DocumentBuilderFactory.newInstance();
    }

    /**
     * Constructor with adding one file.
     * @param filename
     *  The filename to add.
     */
    public XMLConfiguration(String filename) {
        xc = this;
        map = new LinkedHashMap<String, Document>();
        dbf = DocumentBuilderFactory.newInstance();
        addFile(filename);
    }

    /**
     * Constructor with adding multiple files.
     * @param filenames
     *  Files to add on startup.
     */
    public XMLConfiguration(List<String> filenames) {
        xc = this;
        map = new LinkedHashMap<String, Document>();
        dbf = DocumentBuilderFactory.newInstance();
        for(String s : filenames) {
            addFile(s);
        }
    }

    /**
     * Add a file to the list to parse values from.
     * @param filePath
     *  The path to the file.
     * @throws IllegalArgumentException
     *  If the value already is registered.
     */
    public void addFile(String filePath) throws IllegalArgumentException {
        if(!map.keySet().contains(filePath)) {
            try {
                DocumentBuilder db = dbf.newDocumentBuilder();
                map.put(filePath, db.parse(new File(filePath)));
            }catch(ParserConfigurationException e) {
                Apollo.getApollo().log(Level.ERROR, String.format("Couldn't parse file: %s", filePath));
            }catch(IOException e) {
                Apollo.getApollo().log(Level.ERROR, String.format("Couldn't parse file: %s", filePath));
            }catch(SAXException e) {
                Apollo.getApollo().log(Level.ERROR, String.format("Couldn't parse file: %s", filePath));
            }
            return;
        }
        throw new IllegalArgumentException("Value already exists!");
    }

    /**
     * Parses a value from an XML file.
     * @param fileName
     *  The filename of the file to parse.
     * @param element
     *  The element in the XML document to check.
     * @param node
     *  The specific node to check.
     * @return
     *  The specified node in the specified element.
     */
    public String parseValue(String fileName, String element, String node) {
        Document dom = get(fileName);
        Element docElm = dom.getDocumentElement();
        NodeList nL = docElm.getElementsByTagName(element);
        if(nL != null && nL.getLength() > 0) {
            for(int i = 0; i < nL.getLength(); ++i) {
                Element el = (Element) nL.item(i);
                return getTextValue(el, node);
            }
        }
        return null;
    }

    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if(nl != null && nl.getLength() > 0) {
            Element el = (Element)nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }

        return textVal;
    }

    /**
     * Register a list of files.
     * @param files
     *  The list of files to add.
     * @throws IllegalArgumentException
     *  If any of the files have been registered before this gets thrown.
     */
    public void addFiles(List<String> files) throws IllegalArgumentException {
        for(String s : files) {
            addFile(s);
        }
    }

    /**
     * Get a document instance based on filename.
     * @param filename
     *  The filename of the document instance to get.
     * @return
     *  The document instance of the file to get.
     */
    public Document get(String filename) {
        return map.get(filename);
    }

    /**
     * Checks if a file is registered.
     * @param filename
     *  The filename to check.
     * @return
     *  If the file is registered.
     */
    public boolean fileRegistered(String filename) {return map.keySet().contains(filename);}

    /**
     * Get the non-static version of this manager.
     * @return
     *  The non-static version of this manager.
     */
    public static XMLConfiguration getSelf() {return xc;}
}