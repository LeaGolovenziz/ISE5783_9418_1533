package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * Abstract class RadialGeometry represents a radial geometry in Cartesian 3-Dimensional coordinate system
 */
public abstract class RadialGeometry implements Geometry {

    protected final double radius;

    /**
     * Construct that initializes the radius
     *
     * @param radius
     */
    public RadialGeometry(double radius){
        this.radius = radius;
    }
}
