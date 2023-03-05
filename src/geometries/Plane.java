package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class Plane represents a plane in Cartesian 3-Dimensional coordinate system.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Plane implements Geometry {
    final Point q0;
    final Vector normal;

    /**
     * Construct a Plane out of 3 points
     *
     * @param p1 point p1
     * @param p2 point p2
     * @param p3 point p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        normal = null;
        q0 = p1;
    }

    /**
     * Construct a plane by a point and normal
     *
     * @param p0 point op0 the middle of the camera
     * @param normal vector for the normal (will bwe normalized automatically)
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }


    /**
     * getter of the normal
     *
     * @return vector normal to the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * getter for _q0
     *
     * @return referenced point of the plane
     */
    public Point get_q0() {
        return q0;
    }

    @Override
    public String toString() {
        return "Plane: " +
                "q0=" + q0 +
                ", normal=" + normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }


}
