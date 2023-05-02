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
    private final Point q0;
    private final Vector normal;

    /**
     * Construct a Plane out of 3 points
     *
     * @param p1 point p1
     * @param p2 point p2
     * @param p3 point p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.q0=p1;

        Vector U =p1.subtract(p2);
        Vector V =p1.subtract(p3);

        Vector N=U.crossProduct(V);

        this.normal=N.normalize();
    }

    /**
     * Construct a plane by a point and normal
     *
     * @param q0 point op0 the middle of the camera
     * @param normal vector for the normal (will bwe normalized automatically)
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        if(normal.length()!=1)
           this.normal = normal.normalize();
        else
           this.normal=normal;
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

    /**
     * Calculates and returns the normal of the Plane
     *
     * @param point {@link Point} external to the shape
     * @return the normal
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    /**
     * Calculates and returns the intersection points between the ray and the Plane
     *
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        Point P0 = ray.getP0();
        Vector v = ray.getDirection();
        Vector n = normal;

        double nv = n.dotProduct((v));

        // If the ray parallel to the plane - there are no intersections
        if (isZero(nv)) {
            return null;
        }

        // If the p0 is the reference point - there are no intersections
        if (q0.equals(P0)) {
            return null;
        }

        double numerator = n.dotProduct(q0.subtract(P0)); // numerator = n*Q0P0

        // In this case P0 is on the plane - there are no intersections
        if (isZero(numerator)) {
            return null;
        }
        double t = alignZero(numerator / nv); // t = numerator/nv

        // If t>0 the ray intersects the plane
        if (t > 0) {
            return List.of(P0.add(v.scale(t)));
        }

        // Else - there are no intersections
        return null;
    }
}
