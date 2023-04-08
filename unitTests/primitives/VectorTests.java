package primitives;

import geometries.Cylinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 * @author Naomi Reitzer and Leah Golovenziz
 */
class VectorTests {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    /***
     *  test constructor {@link Vector#Vector(double, double, double)}
     */
    @Test
    void testConstructor() {
        // TC01: Create zero vector
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Vector(0, 0, 0.00000000000000000000034);
                },
                "Vector(0,0,0) should have thrown Exception");
    }

    /**
     * Test method for {@link Vector#add(Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Add two vectors
        assertEquals(new Vector(1, 5, 1),
                v1.add(v3),
                "Wrong vector add");

        // =============== Boundary Values Tests ==================
        // TC02: Add two vectors resulting zero vector
        assertThrows( IllegalArgumentException.class,
                () -> new Vector(1, 2, 3).add(new Vector(-1, -2, -3)),
                "Add v plus -v must throw exception");
    }

    /**
     * Test method for {@link Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Scaling a vector
        assertEquals(new Vector(2, 4, 6),
                v1.scale(2d),
                "Wrong vector scale");

        // =============== Boundary Values Tests ==================
        // TC02: Scaling a vector by 0
        assertThrows(IllegalArgumentException.class,
                () -> v1.scale(0d),
                "Scale by 0 must throw exception");
    }

    /**
     * Test method for {@link Vector#dotProduct(Vector)}.
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Dot product of two vectors
        assertEquals(-28d, v1.dotProduct(v2), 0.00001, "dotProduct() wrong value");

        // =============== Boundary Values Tests ==================
        // TC02: Dot Product of orthogonal vectors
        assertEquals(0d, v1.dotProduct(v3), 0.00001, "dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * Test method for {@link Vector#crossProduct(Vector)}.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);

        // TC01: Length of cross-product between two vectors
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "ERROR: crossProduct() wrong result length");

        // TC02: Cross-product between two orthogonal vectors
        assertTrue(isZero(vr.dotProduct(v1))&&isZero(vr.dotProduct(v3))
                ,"ERROR: crossProduct() result is not orthogonal to one of the operands");

        // =============== Boundary Values Tests ==================
        // TC03: Cross-product between two parallel vectors
        assertThrows(
                IllegalArgumentException.class,
                () -> v1.crossProduct(v2),
                "ERROR: crossProduct() for parallel vectors does not throw an exception"
        );
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // TC01: Length squared of a vector
        assertEquals(14d,v1.lengthSquared(),0.000001,"ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void testLength() {
        // TC01: Length of a vector
        assertEquals(5d,new Vector(0, 3, 4).length(),"ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Length of normalized vector equals 1
        assertEquals(1d, n.length(), 0.00001, "ERROR: the normalized vector is not a unit vector");
        // TC02: Normalized vector is parallel to original vector
        assertThrows(IllegalArgumentException.class,
                () -> v.crossProduct(n),
                "ERROR: the normalized vector is not parallel to the original one");
        // TC03: Normalized vector is in the same direction as the original vector
        assertFalse(v.dotProduct(n)<0,"ERROR: the normalized vector is opposite to the original one");
    }
}