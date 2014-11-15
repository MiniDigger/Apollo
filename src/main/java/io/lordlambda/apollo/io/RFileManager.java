package io.lordlambda.apollo.io;

import io.lordlambda.apollo.Apollo;
import org.apache.log4j.Level;
import rcaller.RCaller;
import rcaller.RCode;
import rcaller.ROutputParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Creator: LordLambda
 * Date: 11/12/14.
 * Project: Apollo
 * Usage: RScript File management
 */
public class RFileManager {

    /////////////////////////////////////////////////////////////
    //  Variables
    //
    //      Class Variables
    //          caller - an RCaller instance to call RScript,
    //              and run them.
    //          rfm - A static instance of this manager.
    //          data - a map of a filename/rscript, and it's data
    //
    /////////////////////////////////////////////////////////////
    RCaller caller;
    static RFileManager rfm;
    LinkedHashMap<RCode, String> data;

    /**
     * Default constructor.
     */
    public RFileManager() {
        rfm = this;
        caller = new RCaller();
        caller.setRscriptExecutable("/usr/bin/Rscript");
        data = new LinkedHashMap<RCode, String>();
        caller.cleanRCode();
    }

    /**
     * Constructor with parsing a file on startup.
     * @param file
     *  The file to parse on startup.
     */
    public RFileManager(String file) {
        rfm = this;
        caller = new RCaller();
        caller.setRscriptExecutable("/usr/bin/Rscript");
        data = new LinkedHashMap<RCode, String>();
        caller.cleanRCode();
        data.put(parseFile(file), file);
    }

    /**
     * Get a non-static version of self.
     * @return
     *  A non-static version of self.
     */
    public static RFileManager getSelf() {return rfm;}

    /**
     * Parse a file for all possible RCode.
     * @param filename
     *  The filename to parse.
     * @return
     *  The data parsed as RCode.
     * </p>
     * Note no checks are done for the data being actually supported RScript code. There is a certain trust relationship
     * here.
     */
    public RCode parseFile(String filename) {
        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        }catch(Exception e) {
            Apollo.getApollo().log(Level.FATAL, "Couldn't read R Script File");
            return null;
        }
        RCode rCode = new RCode();
        for(String s : lines) {
            s = s.replace("\\s+", "");
            if(!(s.isEmpty())) {
                rCode.addRCode(s);
            }
        }
        return rCode;
    }

    /**
     * Get the RCode instance for a filename
     * @param id
     *  The id to parse.
     * @return
     *  The RCode.
     */
    public RCode getRCode(String id) {
        RCode rcode = null;
        if(parsed(id)) {
            for (int i = 0; i < data.size(); ++i) {
                if (((String) data.values().toArray()[i]).equalsIgnoreCase(id)) {
                    rcode = (RCode) data.keySet().toArray()[i];
                    break;
                }
            }
        }
        return rcode;
    }

    /**
     * If a file has been parsed.
     * @param filename
     *  The filename to check.
     * @return
     *  If the file has been parsed.
     */
    public boolean parsed(String filename) {
        return data.containsValue(filename);
    }

    /**
     * Set the rcode for an id/filename
     * @param filename
     *  The id/filename to set rcode for
     * @param rCode
     *  The rcode to set.
     */
    public void setRCode(String filename, RCode rCode) {
        if(parsed(filename)) {
            RCode rcode = getRCode(filename);
            data.remove(rcode);
            data.put(rCode, filename);
        }else {
            data.put(rCode, filename);
        }
    }

    /**
     * Add RCode to existing rcode in data. Note: This doesn't save on the hard file version.
     * @param rCode
     *  The RCode to add.
     * @param id
     *  The id to add rcode too.
     */
    public void addRCode(RCode rCode, String id) {
        if(!(parsed(id))) {
            data.put(rCode, id);
        }else {
            RCode rcode = getRCode(id);
            RCode rcode2 = rcode;
            rcode2.addRCode(rCode.toString());
            data.remove(rcode);
            data.put(rcode2, id);
        }
    }

    /**
     * Remove RCode from registered list.
     * @param id
     *  The id to remove.
     */
    public void remRCode(String id) {
        if(parsed(id)) {
            RCode rcode = getRCode(id);
            data.remove(rcode);
        }
    }

    /**
     * Runs a section of R-Script.
     * @param id
     *  The id of rcode to run.
     * @param toRun
     *  The specific part of the rcode to run.
     * @return
     *  The outputparser for parsing.
     */
    public ROutputParser runR(String id, String toRun) {
        caller.cleanRCode();
        caller.setRCode(getRCode(id));
        caller.runAndReturnResult(toRun);
        return caller.getParser();
    }
}