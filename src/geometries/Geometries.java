package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class Geometries implements Intersectable {
    List<Intersectable> intersectableList;

    /**
     * Default constructor
     */
    public Geometries(){
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

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
