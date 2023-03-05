package primitives;

import java.util.Objects;

/**
 * Class Point is the class representing a point in Cartesian 3-Dimensional coordinate system.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Point {
    protected final Double3 xyz;

    /**
     * Constructor that initializes the xyz
     *
     * @param xyz point xyz
     */
    Point(Double3 xyz) {
        this(xyz.d1, xyz.d2, xyz.d3);
    }

    /**
     * Constructor that get 3 doubles and initialize new Double3
     *
     * @param x value of X axis
     * @param y value of y axis
     * @param z value of z axis
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override
    public String toString() {
        return "Point: " + xyz;
    }

    /**
     * Vector subtraction
     *
     * @param pt2 point
     * @return new vector between the two points
     */
    public Vector subtract(Point pt2) {
        if (pt2.equals(this)) {
            throw new IllegalArgumentException("Cannot create zero vector");
        }
        return new Vector(this.xyz.subtract(pt2.xyz));
    }

    /**
     * Add the vector to the point
     *
     * @param v vector
     * @return new point
     */
    public Point add(Vector v) {
        return new Point(this.xyz.add(v.xyz));
    }

    /**
     * Returns the squared distance between the current point and the received point
     *
     * @param other point
     * @return (x2 - x1)^2 + (y2-y1)^2 + (z2-z1)^2
     */
    public double distanceSquared(Point other) {
        return ((other.xyz.d1 - xyz.d1) * (other.xyz.d1 - xyz.d1)) +
                ((other.xyz.d2 - xyz.d2) * (other.xyz.d2 - xyz.d2)) +
                ((other.xyz.d3 - xyz.d3) * (other.xyz.d3 - xyz.d3));
    }

    /**
     * Returns the distance between the current point and the received point
     *
     * @param other point
     * @return ((x2 - x1)^2 + (y2-y1)^2 + (z2-z1)^2)^0.5
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }
}
