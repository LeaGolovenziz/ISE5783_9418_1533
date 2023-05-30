package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * Interface for all graphic objects that intersect with a ray{@link Ray}
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public abstract class Intersectable {
    /**
     * Finds the intersection points between a ray and a graphic object
     *
     * @param ray
     * @return list of the intersection points between the ray and the graphic object
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Class that represents point of geometry
     */
    public static class GeoPoint {

        public Geometry geometry; // The point's geometry
        public Point point; // The intersection point

        /**
         * Constructor
         *
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint: " +
                    "geometry = " + geometry +
                    ", point = " + point;
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }
    }

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

}
