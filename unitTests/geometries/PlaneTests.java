package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import java.util.List;

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

    @Test
    void findIntersectionsTest(){
        Plane pl = new Plane(new Point(0, 0, 1), new Vector(1, 1, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the plane
        assertEquals(
                List.of(new Point(1, 0, 0)),
                pl.findIntersections(
                        new Ray(new Point(0.5, 0, 0),
                                new Vector(1, 0, 0))),
                "Bad plane intersection");

        // TC02: Ray doesn't intersect the plane
        assertNull(pl.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))),
                "Must not be plane intersection");

        // =============== Boundary Values Tests ==================
        // TC11: Ray is parallel and not included in the plane
        assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(0, 1, -1))),
                "Must not be plane intersection");

        // TC12: Ray is parallel and included in the plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, .5), new Vector(0, 1, -1))),
                "Must not be plane intersection");


        // TC13: Ray is orthogonal and starts before the plane
        assertEquals(List.of(new Point(1d / 3, 1d / 3, 1d / 3)),
                pl.findIntersections(
                        new Ray(new Point(1, 1, 1),
                                new Vector(-1, -1, -1))),
                "Bad plane intersection");

        // TC14: Ray is orthogonal and starts after the plane
        assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC15: Ray is orthogonal and starts in the plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC16: Ray is not orthogonal or parallel and starts in the plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 0))),
                "Must not be plane intersection");

        // TC17: Ray is not orthogonal or parallel and starts in the Q point of the plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 1, 0))),
                "Must not be plane intersection");
    }
}