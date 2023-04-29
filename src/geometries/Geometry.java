package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Interface Geometry represents a geometry shape in Cartesian 3-Dimensional coordinate system
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public interface Geometry extends Intersectable {
    /**
     * Returns the normal vector of the shape
     * @param point {@link Point} external to the shape
     * @return the normal vector{@link Vector} of the geometry
     */
    public abstract Vector getNormal(Point point);
}
