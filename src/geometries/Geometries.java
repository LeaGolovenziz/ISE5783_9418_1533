package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class Geometries extends Intersectable {
    List<Intersectable> intersectableList;

    /**
     * Default constructor
     */
    public Geometries() {
        intersectableList = new LinkedList<>();
    }

    /**
     * Constructor
     *
     * @param geometries - group of geometries
     */
    public Geometries(Intersectable... geometries) {
        this();
        Collections.addAll(intersectableList, geometries);
    }

    /**
     * Adds geometries to the list
     *
     * @param geometries - group of geometries
     */
    public void add(Intersectable... geometries) {

        Collections.addAll(intersectableList, geometries);
    }

    /**
     * Calculates and returns the intersection points between the ray and the geometries
     *
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = null;

        // For each geometry add its intersections to the list
        for (var item : intersectableList) {
            List<GeoPoint> itemLst = item.findGeoIntersectionsHelper(ray);
            if (itemLst != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itemLst);
            }
        }
        return result;
    }
}
