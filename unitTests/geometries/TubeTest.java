package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * Unit tests for geometries.Tube class
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
class TubeTest {

    /**
     * Test method for {@link Tube#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        Tube tube = new Tube(1.0, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));

        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here

        Vector normal = tube.getNormal(new Point(1, 0, 1)).normalize();

        assertEquals(normal, new Vector(1, 0, 0), "Normal is not orthogonal to the Tube");

        // =============== Boundary Values Tests ==================
        // TC02: Point The point is in front of the head of the Ray
        assertEquals(tube.getNormal(new Point(1, 0, 0)).normalize(), new Vector(1,0,0),
                "Point The point is in front of the head of the Ray");
    }
}