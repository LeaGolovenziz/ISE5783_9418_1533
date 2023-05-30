package primitives;

import geometries.Intersectable;

import java.util.Objects;
import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * The {@code Ray} class represents a ray in a Cartesian 3-dimensional coordinate system.
 * A ray is defined by a starting point {@code p0} and a normalized direction vector {@code dir}.
 *
 * <p>
 *     This class provides methods to retrieve the starting point and direction vector of the ray,
 *     as well as methods to check for equality and generate a hash code for the object.
 * </p>
 *
 * <p>
 *     The {@code dir} field is declared as package-private (default access),
 *     meaning that only classes in the same package as {@code Ray} can access it directly.
 * </p>
 *
 * <p>
 *     This class is immutable, meaning that its fields cannot be modified once the object is created.
 * </p>
 *
 * <p>
 *     The {@code equals()}, {@code hashCode()}, and {@code toString()} methods are implemented according to
 *     the general contract for overriding these methods in Java classes.
 * </p>
 *
 * @author
 *     Naomi Reitzer and Leah Golovenziz
 */
public class Ray {
    /**
     * The starting point of the ray.
     */
    private final Point p0;

    /**
     * The normalized direction vector of the ray.
     * This field is declared as package-private, meaning that only classes in the same package as
     * {@code Ray} can access it directly.
     */
    final Vector dir;

    /**
     * Constructs a new {@code Ray} object with the given starting point and direction vector.
     *
     * @param p0
     *     The starting point of the ray.
     * @param dir
     *     The direction vector of the ray. The vector is normalized during construction.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Returns the starting point of the ray.
     *
     * @return the starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns a new {@code Vector} object representing the direction vector of the ray.
     *
     * @return a new {@code Vector} object representing the direction vector of the ray.
     */
    public Vector getDirection() {
        return new Vector(dir.xyz);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o
     *     The object to compare this {@code Ray} against.
     * @return {@code true} if this {@code Ray} and the specified object are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Ray: " +
                "p0 = " + p0 +
                ", direction = " + dir;
    }

    public Point getPoint(double t){
        return p0.add(dir.scale(t));
    }

    /**
     * The function returns the closest point to P0 of the current ray
     *
     * @param points - list of the points
     * @return The closest point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * The function returns the closest GeoPoint to P0 of the current ray
     *
     * @param geoPointList - list of the points
     * @return The closest geoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {

        // If there are no intersection points
        if (geoPointList == null) {
            return null;
        }

        double minDistance = Double.MAX_VALUE;
        double geoPointDistance;
        GeoPoint closestGeoPoint = null;

        // Go over the points and find the one with the minimum distance from p0
        for (GeoPoint geoPoint : geoPointList) {
            geoPointDistance = geoPoint.point.distanceSquared(p0);
            if (geoPointDistance < minDistance) {
                minDistance = geoPointDistance;
                closestGeoPoint = geoPoint;
            }
        }
        return closestGeoPoint;
    }
}