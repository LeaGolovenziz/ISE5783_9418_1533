package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
        return null;
    }
}
