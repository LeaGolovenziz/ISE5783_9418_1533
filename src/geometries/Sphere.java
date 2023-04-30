package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import static primitives.Util.isZero;
import static primitives.Util.alignZero;

/** Class Sphere represents a sphere in Cartesian 3-Dimensional coordinate system.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Sphere extends RadialGeometry {

    private final Point center;

    /**
     * Constructor That initializes the sphere
     *
     * @param center
     * @param radius
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * Getter of the center point
     *
     * @return the center of the sphere
     */
    public Point getCenter() {
        return center;
    }

    @Override
    public String toString() {
        return "Sphere: " +
                "center = " + center +
                ", radius = " + radius;
    }

    @Override
    public Vector getNormal(Point point) {
        Vector N = point.subtract(center);
        return N.normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDirection();

        // If P0 is the center of the sphere, return
        if (P0.equals(center)) {
            return List.of(center.add(v.scale(radius)));
        }

        // u = center-p0
        Vector u = center.subtract(P0);
        // tm = v*u
        double tm = alignZero(v.dotProduct(u));
        // d is the distance from the center to tm: squert(|u|^2-tm^2)
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

        //if d is larger are equals radius, then there are no intersections
        if (d >= radius) {
            return null;
        }

        //th is the last part of the triangle between radius and d, calculate according to pythagoras
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm - th); //t1=tm-th
        double t2 = alignZero(tm + th); //t2=tm+th

        if (t1 > 0 && t2 > 0) { //if they are both positive then there are 2 intersections
            Point P1 = ray.getPoint(t1); //p1=p0+t1*v
            Point P2 = ray.getPoint(t2); //p2=p0+t2*v
            return List.of(new GeoPoint(this, P1), new GeoPoint(this, P2));
        }
        //otherwise if only one is positive so there is one intersection
        if (t1 > 0) {
            Point P1 = ray.getPoint(t1); //p1=p0+t1*v
            return List.of(new GeoPoint(this, P1));
        }
        if (t2 > 0) {
            Point P2 = ray.getPoint(t2); //p2=p0+t2*v
            return List.of(new GeoPoint(this, P2));
        }

        //if they are both negative then there are no intersections
        return null;
    }
}
