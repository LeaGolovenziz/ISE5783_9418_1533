package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Testing Ray class
 *  
 *  @author Naomi Reitzer and Leah Golovenziz
 */
class RayTest {

    /**
     * Test method for {@link Ray#findClosestPoint(List)}
     */
    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point(0, 0, 10), new Vector(1, 10, -100));
        List<Point> list = new LinkedList<>();

        // ============ Equivalence Partitions Tests ==============
        // TC01: The closest point is in the middle of the list
        list.add(new Point(1, 1, -100));
        list.add(new Point(-1, 1, -99));
        list.add(new Point(0, 2, -10));
        list.add(new Point(0.5, 0, -100));

        assertEquals(list.get(2), ray.findClosestPoint(list));

        // ============ Boundary Partitions Tests ==============
        list=null;

        // TC02: empty list
        assertNull(ray.findClosestPoint(list), "try again");

        // TC03: The closest point is in the beginning of the list
        list = new LinkedList<>();
        list.add(new Point(0, 2, -10));
        list.add(new Point(1, 1, -100));
        list.add(new Point(-1, 1, -99));
        list.add(new Point(0.5, 0, -100));
        assertEquals(list.get(0), ray.findClosestPoint(list));

        // TC04: The closest point is the last one in the list
        list = new LinkedList<>();
        list.add(new Point(1, 1, -100));
        list.add(new Point(-1, 1, -99));
        list.add(new Point(0.5, 0, -100));
        list.add(new Point(0, 2, -10));
        assertEquals(list.get(3), ray.findClosestPoint(list));
    }
}