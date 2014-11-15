package io.lordlambda.apollo;

import io.lordlambda.apollo.io.XMLConfiguration;

/**
 * Creator: LordLambda
 * Date: 11/12/14.
 * Project: Apollo
 * Usage: I'm assuming things will break on updates, so this determines if the update is safe.
 */
public class VersionHandler {

    /**
     * Determines if a Sponge API version is supported
     * @param apiVersion
     *  The game.getApiVersion() string.
     * @return
     *  If the version is supported.
     * I have the feeling this will become similar to a giant switch statement. Right now an equals is enough
     */
    public static boolean versionSupported(String apiVersion) {
        return apiVersion.equalsIgnoreCase("SNAPSHOT-1.0.0");
    }

    /**
     * Parses the current apollo version from configuration.xml
     * @return
     *  The current Apollo version
     */
    public static String getApolloVersion() {
        return XMLConfiguration.getSelf().parseValue("configuration.xml", "configuration", "apolloVersion");
    }
}