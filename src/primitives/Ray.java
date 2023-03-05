package primitives;

import java.util.Objects;

/**
 * Class Ray is the class representing a ray in Cartesian 3-Dimensional coordinate system.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Ray {
    final Point p0;
    final Vector dir;

    /**
     * constructor to initialize the point and the direction vector-normalized
     *
     * @param p0  the point
     * @param dir the vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * getter of the point
     *
     * @return the point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter of the vector
     *
     * @return the direction
     */
    public Vector getDirection() {
        return new Vector(dir.xyz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray: " +
                "p0 = " + p0 +
                ", direction = " + dir;
    }
}