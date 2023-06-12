package primitives;

import java.util.Objects;

/**
 * The Vector class represents a vector in a Cartesian 3-dimensional coordinate system.
 * It extends the Point class and provides methods for vector addition, scaling, dot product,
 * cross product, length, normalization, and more.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Vector extends Point {

    /**
     * Constructs a vector by a Double3.
     *
     * @param xyz the Double3 representing the vector's coordinates.
     */
    Vector(Double3 xyz) {
        this(xyz.d1, xyz.d2, xyz.d3);
    }

    /**
     * Constructs a vector by three double coordinates.
     *
     * @param x the value for the x-axis.
     * @param y the value for the y-axis.
     * @param z the value for the z-axis.
     * @throws IllegalArgumentException if a zero vector is attempted to be created.
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Cannot create zero vector");
        }
    }

    /**
     * ChecKS if two Vector objects are equal.
     *
     * @param o the object to compare with this vector.
     * @return true if the two Vector objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector vector)) return false;
        return xyz.equals(vector.xyz);
    }

    /**
     * Generates a hash code for the Vector object.
     *
     * @return a hash code for the Vector object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    /**
     * Generates a string representation of the Vector object.
     *
     * @return a string representation of the Vector object.
     */
    @Override
    public String toString() {
        return "Vector: " + xyz;
    }

    /**
     * Returns the vector resulting from the addition of the current vector and a received vector.
     *
     * @param other the vector to add to the current vector.
     * @return the resulting vector.
     */
    public Vector add(Vector other) {
        return new Vector(this.xyz.add(other.xyz));
    }

    /**
     * Returns the vector resulting from scaling the current vector by a scalar factor.
     *
     * @param scalar the scalar factor to scale the current vector by.
     * @return the resulting vector.
     * @throws IllegalArgumentException if a zero vector is attempted to be created.
     */
    public Vector scale(double scalar) {
        if (Util.isZero(scalar)) {
            throw new IllegalArgumentException("Cannot create zero vector");
        }
        return new Vector(this.xyz.scale(scalar));
    }

    /**
     * Returns the scalar resulting from the dot product between the current vector and a received vector.
     *
     * @param other the vector to calculate the dot product with.
     * @return the scalar resulting from the dot product.
     */
    public double dotProduct(Vector other) {
        return this.xyz.d1 * other.xyz.d1 + this.xyz.d2 * other.xyz.d2 + this.xyz.d3 * other.xyz.d3;
    }

    /**
     * Returns the vector resulting from the cross product between the current vector and a received vector.
     *
     * @param other the vector to calculate the cross product with.
     * @return the resulting vector.
     */
    public Vector crossProduct(Vector other) {
        return new Vector(new Double3(
                this.xyz.d2 * other.xyz.d3 - this.xyz.d3 * other.xyz.d2, // Calculate x component of resulting vector
                this.xyz.d3 * other.xyz.d1 - this.xyz.d1 * other.xyz.d3, // Calculate y component of resulting vector
                this.xyz.d1 * other.xyz.d2 - this.xyz.d2 * other.xyz.d1  // Calculate z component of resulting vector
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

        return new Vector(new Double3(
                this.xyz.d1 / length, // Calculate x component of normalized vector
                this.xyz.d2 / length, // Calculate y component of normalized vector
                this.xyz.d3 / length  // Calculate z component of normalized vector
        ));
    }
}