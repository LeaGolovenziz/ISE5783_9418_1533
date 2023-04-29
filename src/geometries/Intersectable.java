package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * Interface for all graphic objects that intersect with a ray{@link Ray}
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public interface Intersectable {
    /**
     * Finds the intersection points between a ray and a graphic object
     * @param ray
     * @return list of the intersection points between the ray and the graphic object
     */
    List<Point> findIntersections(Ray ray);
}
