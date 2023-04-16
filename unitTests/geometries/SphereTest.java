package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Vector;

/**
 * Unit tests for geometries.Sphere class
 * @author Naomi Reitzer and Leah Golovenziz
 */
class SphereTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: simple single test of normal of sphere
        Sphere sph = new Sphere( new Point(0, 0, 0),1.0);
        assertEquals(new Vector(0, 0, 1), sph.getNormal(new Point(0, 0, 1)), "Bad normal to sphere");
    }
}