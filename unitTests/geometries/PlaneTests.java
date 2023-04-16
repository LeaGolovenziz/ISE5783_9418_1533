package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Plane class
 * @author Naomi Reitzer and Leah Golovenziz
 */
class PlaneTests {

    /**
    * Test method for {@link Plane#Plane(Point, Point, Point)}.
    */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test of constructing a plane
        try {
            new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Plane");
        }

        // =============== Boundary Values Tests ==================
        // TC02: Constructing a plane with two converging points
        assertThrows(IllegalArgumentException.class,
                () -> {new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));},
                "Constructed a plane with two converging points");
        // TC03: Constructing a plane with three points on the same line
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 0), new Point(1, 0, 0), new Point(2, 0, 0)), //
                "Constructed a plane with three points on the same line");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Single Simple test of Normal of the plane
        Plane pl = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertTrue((new Vector(sqrt3, sqrt3, sqrt3)).equals(pl.getNormal(new Point(0, 0, 1)))||
                        (new Vector(-sqrt3, -sqrt3, -sqrt3).equals(pl.getNormal(new Point(0, 0, 1)))),
                "Bad normal to plane");
    }
}