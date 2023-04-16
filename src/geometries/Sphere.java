package geometries;

import primitives.Point;
import primitives.Vector;

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
}
