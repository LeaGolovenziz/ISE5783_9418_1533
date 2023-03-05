package primitives;

import java.util.Objects;

/**
 * Class Vector is the class representing a vector in Cartesian 3-Dimensional coordinate system.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Vector extends Point {

    /**
     * Construct a vector by a Double3
     *
     * @param xyz double3 xyz
     */
    Vector(Double3 xyz) {
        this(xyz.d1, xyz.d2, xyz.d3);
    }

    /**
     * Construct a vector by three double coordinates
     *
     * @param x value for x axis
     * @param y value for y axis
     * @param z value for z axis
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Cannot create zero vector");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector vector)) return false;
        return xyz.equals(vector.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override
    public String toString() {
        return "Vector: " + xyz;
    }

    /**
     * Return the Addition between the received vector and the current one
     *
     * @param other Vector
     * @return new Vector (u+v)
     */
    public Vector add(Vector other) {
        return new Vector(this.xyz.add(other.xyz));
    }

    /**
     * Returns the current vector scaled by the number received
     *
     * @param scalar scalar factor
     * @return extended vector
     */
    public Vector scale(double scalar) {
        if (Util.isZero(scalar)) {
            throw new IllegalArgumentException("Cannot create zero vector");
        }
        return new Vector(this.xyz.scale(scalar));
    }

    /**
     * dot product between two vectors (scalar product)
     *
     * @param other the right vector of U.V
     * @return scalar value of dot product
     */
    public double dotProduct(Vector other) {
        return this.xyz.d1 * other.xyz.d1 + this.xyz.d2 * other.xyz.d2 + this.xyz.d3 * other.xyz.d3;
    }

    /**
     * Cross product (vectorial product)
     *
     * @param other second vector
     * @return new Vector resulting from cross product
     */
    public Vector crossProduct(Vector other) {
        return new Vector(new Double3(
                this.xyz.d2 * other.xyz.d3 - this.xyz.d3 * other.xyz.d2,
                this.xyz.d3 * other.xyz.d1 - this.xyz.d1 * other.xyz.d3,
                this.xyz.d1 * other.xyz.d2 - this.xyz.d2 * other.xyz.d1
        ));
    }

    /**
     * Return the squared length of the current vector
     *
     * @return euclidean length squared of the vector
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * Return the length of the current vector
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Return the current Vector normalized
     *
     * @return new Vector normalized
     */
    public Vector normalize() {
        double length = this.length();

        return new Vector(new Double3(this.xyz.d1 / length, this.xyz.d2 / length, this.xyz.d3 / length));
    }

}
