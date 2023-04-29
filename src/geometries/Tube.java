package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;
import static primitives.Util.alignZero;

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
        Point P0 = axisRay.getP0();
        Vector v = axisRay.getDirection();

        // The vector p0p
        Vector P0_P = point.subtract(P0);

        // t = the length of p0o
        double t = alignZero(v.dotProduct(P0_P));

        // if p0p is orthogonal to v p0p is the normal
        if (isZero(t)) {
            return P0_P.normalize();
        }

        Point o = P0.add(v.scale(t));

        return point.subtract(o).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
