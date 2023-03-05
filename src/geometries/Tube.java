package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/** Class Tube represents a tube in Cartesian 3-Dimensional coordinate system.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Tube extends RadialGeometry {

    protected final Ray axisRay;

    /**
     * constructor that initializes the Tube
     *
     * @param axisRay axis ray
     * @param radius radius
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * Getter of the axis ray
     *
     * @return axis ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public String toString() {
        return "Tube: " +
                "axisRay = " + axisRay +
                ", radius = " + radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
