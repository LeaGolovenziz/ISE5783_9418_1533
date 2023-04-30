package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Unit tests for geometries.Geometries class
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
class GeometriesTests {
    /**
     * test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}
     */
    @Test
    void testFindIntersections() {
        Geometries geometries = new Geometries(
                new Sphere(new Point(0, 0, 2), 0.5),

                new Polygon(
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(-1, 0, 0),
                        new Point(0, -1, 0)
                ),

                new Triangle(
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(0, 0, 1)
                )
        );

        List<Point> result;

        // =============== Boundary Values Tests ==================
        //TC01: No geometries intersects
        assertNull(geometries.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))),
                "No geometries intersects");
        //TC02: Empty list of geometries
        assertNull(new Geometries().findIntersections(new Ray(new Point(1, 2, 3), new Vector(2, 2, 2))),
                "Empty list of geometries");
        //TC03: Only 1 geometry intersect
        result = geometries.findIntersections(new Ray(new Point(0.2, 0.2, 0.2), new Vector(1, 1, 1)));
        assertEquals(1, result.size(), "Only 1 geometry intersect");
        //TC04: All geometries intersects
        result = geometries.findIntersections(new Ray(new Point(0.2, 0.2, -0.6), new Vector(0, 0, 1)));
        assertEquals(4, result.size(), "All geometries intersects");

        // ============ Equivalence Partitions Tests ==============
        //TC05: A few geometries intersects
        result = geometries.findIntersections(new Ray(new Point(-1, -1, -1), new Vector(2, 2, 2)));
        assertEquals(2, result.size(), "A few geometries intersects");
    }
}