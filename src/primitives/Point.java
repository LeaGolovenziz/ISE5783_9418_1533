package primitives;

import java.util.Objects;

/**
 * Class Point is the class representing a point in Cartesian 3-Dimensional coordinate system.
 * This class provides methods to calculate vector subtraction, addition, and distance between two points.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Point {
    protected final Double3 xyz;

    /**
     * Constructor that initializes the xyz.
     *
     * @param xyz point xyz.
     */
    Point(Double3 xyz) {
        this(xyz.d1, xyz.d2, xyz.d3);
    }

    /**
     * Constructor that gets three doubles and initializes a new Double3.
     *
     * @param x value of X axis.
     * @param y value of y axis.
     * @param z value of z axis.
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     *
     * @return the value of x-axis
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     *
     * @return the value of y-axis
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     *
     * @return the value of z-axis
     */
    public double getZ() {
        return xyz.d3;
    }

    /**
     * Checks whether the current Point object is equal to the given object.
     *
     * @param o the object to compare to.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }

    /**
     * Returns the hash code of the Point object.
     *
     * @return the hash code of the Point object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    /**
     * Returns a string representation of the Point object.
     *
     * @return a string representation of the Point object.
     */
    @Override
    public String toString() {
        return "Point: " + xyz;
    }

    /**
     * Subtracts the given point from the current Point object and returns the result as a new Vector object.
     *
     * @param pt2 the point to subtract.
     * @return a new Vector object between the two points.
     * @throws IllegalArgumentException if pt2 is equal to the current Point object.
     */
    public Vector subtract(Point pt2) throws IllegalArgumentException {
        if (pt2.equals(this)) {
            throw new IllegalArgumentException("Cannot create zero vector");
        }
        return new Vector(this.xyz.subtract(pt2.xyz));
    }

    /**
     * Adds the given Vector object to the current Point object and returns the result as a new Point object.
     *
     * @param v the Vector object to add.
     * @return a new Point object.
     */
    public Point add(Vector v) {
        return new Point(this.xyz.add(v.xyz));
    }

    /**
     * Returns the squared distance between the current Point object and the given Point object.
     *
     * @param other the Point object to calculate the distance to.
     * @return the squared distance between the current Point object and the given Point object.
     */
    public double distanceSquared(Point other) {
        return ((other.xyz.d1 - xyz.d1) * (other.xyz.d1 - xyz.d1)) +
                ((other.xyz.d2 - xyz.d2) * (other.xyz.d2 - xyz.d2)) +
                ((other.xyz.d3 - xyz.d3) * (other.xyz.d3 - xyz.d3));
    }

    /**
     * Returns the distance between the current Point object and the given Point object.
     *
     * @param other the Point object to calculate the distance to.
     * @return the distance between the current Point object and the given Point object.
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }
}
