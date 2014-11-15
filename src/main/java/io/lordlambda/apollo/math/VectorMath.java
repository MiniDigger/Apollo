package io.lordlambda.apollo.math;

import io.lordlambda.apollo.world.types.threeD.CuboidRegion;
import io.lordlambda.apollo.world.Region;
import io.lordlambda.apollo.world.types.threeD.SphericalRegion;
import io.lordlambda.apollo.world.types.twoD.SquareRegion;
import org.spongepowered.api.math.Vector2f;
import org.spongepowered.api.math.Vector3f;
import org.spongepowered.api.math.Vectors;

import java.util.List;

/**
 * Creator: LordLambda
 * Date: 11/14/14.
 * Project: Apollo
 * Usage: Vector Math
 */
public class VectorMath {

    /**
     * Checks if a 3d point is in a region
     * @param r
     *  The region to check
     * @param point
     *  The point to check
     * @return
     *  If the 3d point is in the region
     */
    public static boolean vector3fInBounds(Region r, Vector3f point) {
        if(r instanceof CuboidRegion) {
            CuboidRegion cr = (CuboidRegion) r;
            Vector3f vecs = cr.getXYZ();
            List<String> width = cr.getTagData("Width");
            assert width.size() == 1;
            float widthh = Float.parseFloat((String) width.toArray()[0]);
            List<String> height = cr.getTagData("Height");
            assert height.size() == 1;
            float heightt = Float.parseFloat((String) height.toArray()[0]);

            float minX = vecs.getX();
            float minY = vecs.getY();
            float minZ = vecs.getZ();
            float maxX = vecs.getX() + widthh;
            float maxY = vecs.getY() + heightt;
            float maxZ = vecs.getZ() + widthh;

            float queryX = point.getX();
            float queryY = point.getY();
            float queryZ = point.getZ();

            return ((queryX >= minX) && (queryX <= maxX) && (queryY >= minY) && (queryY <= maxY) && (queryZ >= minZ) && (queryZ <= maxZ));
        }else if(r instanceof SphericalRegion) {

        }
        return false;
    }

    /**
     * Checks if a 2d point is in a region
     * @param r
     *  The region to check
     * @param point
     *  The point to check
     * @return
     *  If the 2d point is in the region
     */
    public static boolean vector2fInBounds(Region r, Vector2f point) {
        if(r instanceof SquareRegion) {
            SquareRegion sr = (SquareRegion) r;

            float minX = sr.getXY().getX();
            float minY = sr.getXY().getY();

            float maxX = Integer.parseInt((String) sr.getTagData("WIDTH").toArray()[0]);
            float maxY = Integer.parseInt((String) sr.getTagData("HEIGHT").toArray()[0]);

            float queryX = point.getX();
            float queryY = point.getY();

            return (queryX >= minX && queryX <= maxX && queryY <= minY && queryY >= maxY);
        }
        return false;
    }

    /**
     * Change a 3d point to a 2d point
     * @param vec
     *  The vector 3f
     * @return
     *  A 2d point
     *  </p>
     *  Woah! You just projected a 3d point to a 2d point without using any sort of projecting!
     *  How did you do that?
     *  Well since this point isn't being projected we don't need to do any sort of fancy projecting we can get by just
     *  fine by merging the two axises together. Using the pythagorean theorem! Never thought you would use that again huh?
     */
    public static Vector2f vector3ToVector2(Vector3f vec) {
        double y = vec.getY();

        double xDif = (vec.getX() - vec.getFloorX());
        double zDif = (vec.getZ() - vec.getFloorZ());

        /**
         * Pythagorean theorem to merge x/z
         * Never thought you would use this again huh!
         *
         *           /|
         *          / |
         * xCoord  /  | zDif
         *        /   |
         *       /    |
         *      /_____|
         *        xDif
         */
        double xCoord = Math.sqrt((xDif * xDif) + (zDif * zDif));

        return Vectors.create2f((float) xCoord, (float) y);
    }

}