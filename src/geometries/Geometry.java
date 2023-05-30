package geometries;

import primitives.Material;
import primitives.Point;
import primitives.Vector;

import primitives.Color;

/**
 * Interface Geometry represents a geometry shape in Cartesian 3-Dimensional coordinate system
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK; // The emission color of geometry

    private Material material = new Material(); // The material of geometry

    /**
     * Getter of the geometry's emission color
     *
     * @return the emission color
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Getter of the geometry's material
     *
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Setter of the geometry's emission color
     *
     * @param emission color of geometry
     * @return the geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Setter of the geometry's material
     *
     * @param material of geometry
     * @return the geometry
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Returns the normal vector of the shape
     * @param point {@link Point} external to the shape
     * @return the normal vector{@link Vector} of the geometry
     */
    public abstract Vector getNormal(Point point);
}
