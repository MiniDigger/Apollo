package io.lordlambda.apollo.world.types;

import io.lordlambda.apollo.world.Region;
import org.spongepowered.api.math.Vector2f;

import java.util.List;

/**
 * Creator: LordLambda
 * Date: 11/12/14.
 * Project: Apollo
 * Usage: A 2D-Region Interface
 */
public abstract interface Region2D extends Region {

    /**
     * Get the XY start point.
     * @return
     *  XY start point.
     * </p>
     * This only specifies the start of the region. The width/height/other needed data should be specified in tags.
     * </p>
     * This is done so the users can specify regions, and do their own sorts of calculations. This is also why SpawnListener
     * Calls the region to calculate if a point is in it. Giving the user more control.
     */
    Vector2f getXY();

    /**
     * @param point
     *  The point to check if it's in a region.
     * @return
     *  If the point is in a region.
     */
    boolean pointInRegion(Vector2f point);

    /**
     * @param points
     *   A list of points to check
     * @return
     *   If all points are in the region.
     */
    boolean allPointsInRegion(List<Vector2f> points);
}