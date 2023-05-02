package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import static primitives.Util.isZero;
import static primitives.Util.alignZero;

import java.util.List;

/** Class Triangle represents a triangle in Cartesian 3-Dimensional coordinate system.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Triangle extends Polygon {

    /**
     * Constructor that initialize the triangle's point
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     */
    public Triangle(Point p1,Point p2,Point p3){

        super(p1,p2,p3);
    }

    /**
     * Calculates and returns the intersection points between the ray and the Triangle
     *
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        // First find the intersections with the plane in which the triangle lays
        List<Point> intersections = plane.findIntersections(ray);

        // If there are no intersections with the plane - return null
        if (intersections == null)
            return null;

        Point p0 = ray.getP0();
        Vector v = ray.getDirection();

        // Vectors from the ray start point to the polygon vertices
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        double vn1 = v.dotProduct(v1.crossProduct(v2)); //s1 = v * (v1 X v2)
        double vn2 = v.dotProduct(v2.crossProduct(v3)); //s2 = v * (v2 X v3)
        double vn3 = v.dotProduct(v3.crossProduct(v1)); //s3 = v * (v3 X v1)

        // If the point is on the triangle - there are no intersection point
        if (isZero(vn1) || isZero(vn2) || isZero(vn3))
            return null;

        // IF they all have the same sign (-/+) the point is in the triangle so return the intersections , else return null
        return ((vn1 > 0 && vn2 > 0 && vn3 > 0) || (vn1 < 0 && vn2 < 0 && vn3 < 0)) ? intersections : null;
    }
}
