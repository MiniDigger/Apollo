package io.lordlambda.apollo.ai.behavior;

/**
 * Creator: LordLambda
 * Date: 11/14/14.
 * Project: Apollo
 * Usage: Behavior Manager
 */
public interface Behavior {

    /**
     * The const value for this behavior for calculations
     * @return
     *  The const
     */
    float getConst();

    /**
     * The name of the behavior
     * @return
     *  The user name to see.
     */
    String getName();

    /**
     * If the ai is netural
     * @return
     *  If the AI is.
     */
    boolean isNeutral();
}