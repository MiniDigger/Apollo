package io.lordlambda.apollo.world.types;

import io.lordlambda.apollo.world.Region;
import org.spongepowered.api.math.Vector3f;

import java.util.List;

/**
 * Creator: LordLambda
 * Date: 11/12/14.
 * Project: Apollo
 * Usage: A 3D-Region Interface
 */
public abstract interface Region3D extends Region {

    /**
     * Get the XYZ start point.
     * @return
     *  XYZ start point.
     * </p>
     * This only specifies the start of the region. The width/height/other needed data should be specified in tags.
     * </p>
     * This is done so the users can specify regions, and do their own sorts of calculations. This is also why SpawnListener
     * Calls the region to calculate if a point is in it. Giving the user more control.
     */
    Vector3f getXYZ();

    /**
     * Returns if a point is in a region.
     * @param point
     *  The point to check.
     * @return
     *  If the point is in the region.
     */
    boolean pointInRegion(Vector3f point);

    /**
     * If a list of points are all in a region.
     * @param points
     *  The points to check.
     * @return
     *  True only if all points are in the region if one isn't it returns false.
     */
    boolean allPointsInRegion(List<Vector3f> points);
}