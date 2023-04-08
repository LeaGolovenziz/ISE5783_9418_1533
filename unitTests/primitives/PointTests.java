package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 * @author Naomi Reitzer and Leah Golovenziz
 */
class PointTests {

    private Point p1 = new Point(1, 2, 3);

    /**
     * Test method for {@link Point#subtract(Point)}.
     */
    @Test
    void testSubtract() {
        // TC01: Subtract two points
        assertEquals(new Point(1, 1, 1),
                 new Point(2, 3, 4).subtract(p1),
                "Error: Point - point does not work correctly");
    }

    /**
     * Test method for {@link Point#add(Vector)}.
     */
    @Test
    void testAdd() {
        Point p1 = new Point(1,2,3);
        // TC01: Add two points
        assertEquals(new Point(0,0,0),
                p1.add(new Vector(-1,-2,-3)),
                "Error: Point + Vector does not work correctly");
    }

    /**
     * Test method for {@link Point#distanceSquared(Point)}.
     */
    @Test
    void testDistanceSquared() {
        // TC01: Distance squared between two points
        Point point = new Point(0.5, 0, -100);
        assertEquals(0.25,point.distanceSquared(new Point(0,0,-100)),
                "Erorr! the distanceSquared is incorrect");
    }

    /**
     * Test method for {@link Point#distance(Point)}.
     */
    @Test
    void testDistance() {
        // TC01: Distance between two points
        Point point = new Point(0.5, 0, -100);
        assertEquals(0.5, point.distance(new Point(0,0,-100)),
                "Erorr! the distance is incorrect");
    }
}