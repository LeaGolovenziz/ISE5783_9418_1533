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

    /**
     * Calculates and returns the normal of the sphere
     *
     * @param point {@link Point} external to the shape
     * @return the normal
     */
    @Override
    public Vector getNormal(Point point) {
        Vector N = point.subtract(center);
        return N.normalize();
    }

    /**
     * Calculates and returns the intersection points between the ray and the Sphere
     *
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDirection();

        // If P0 is the center of the sphere, return
        if (P0.equals(center)) {
            return List.of(new GeoPoint(this, center.add(v.scale(radius))));
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

        //th is half of the overgrowth that the ray creates
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        // t1 and t2 is the distances of the intersection points from p0
        double t1 = alignZero(tm - th); //t1 = tm-th
        double t2 = alignZero(tm + th); //t2 = tm+th

        // If t1 and t2 are both positive then there are 2 intersection points
        if (t1 > 0 && t2 > 0) {
            Point p1 = ray.getPoint(t1); //p1=p0+t1*v
            Point p2 = ray.getPoint(t2); //p2=p0+t2*v
            return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
        }

        // Else if only one is positive - there is one intersection point
        if (t1 > 0) {
            Point p1 = ray.getPoint(t1); //p1=p0+t1*v
            return List.of(new GeoPoint(this, p1));
        }
        if (t2 > 0) {
            Point p2 = ray.getPoint(t2); //p2=p0+t2*v
            return List.of(new GeoPoint(this, p2));
        }

        // Else if they are both negative then there are no intersections
        return null;
    }
}
