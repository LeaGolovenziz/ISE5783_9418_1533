package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Triangle class
 * @author Naomi Reitzer and Leah Golovenziz
 */

class TriangleTests {

    /**
     * Test method for {@link Triangle#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test of Normal of the triangle
        Triangle pl = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertTrue((new Vector(sqrt3, sqrt3, sqrt3)).equals(pl.getNormal(new Point(0, 0, 1)))||
                        (new Vector(-sqrt3, -sqrt3, -sqrt3).equals(pl.getNormal(new Point(0, 0, 1)))),
                "Bad normal to triangle");
    }
}