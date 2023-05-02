package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/** Class Cylinder represents a cylinder in Cartesian 3-Dimensional coordinate system.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Cylinder extends Tube {

    private final double height;

    /**
     * Constructor that initializes the radius and the height
     *
     * @param radius
     * @param ray
     * @param height
     */
    public Cylinder(double radius, Ray ray, double height) {
        super(radius,ray);
        this.height = height;
    }

    /**
     * Getter of the height
     *
     * @return the height of the cylinder
     */
    public double GetHeight() {
        return height;
    }


    @Override
    public Vector getNormal(Point point) {

        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDirection();

        // The projection of p0p on the axis:
        double t;
        try {
            t = alignZero(point.subtract(p0).dotProduct(v));
        } catch (IllegalArgumentException e) { // In case P = p0
            return v;
        }

        // If the point is on one of the bases - return v
        if (t == 0 || isZero(height - t))
            return v;

        // return the normal (similar to tube function)
        return point.subtract(p0.add(v.scale(t))).normalize();
    }

    /**
     * Calculates and returns the intersection points between the ray and the Cylinder
     *
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
