package renderer;

import lighting.AmbientLight;
import primitives.Color;
import primitives.Ray;
import primitives.Point;
import scene.Scene;

/**
 * The RayTracerBasic class represents a basic ray tracer.
 *
 *  @author Naomi Reitzer and Leah Golovenziz
 */
public class RayTracerBasic extends RayTracerBase{

    /**
     *  Constructor
     * @param scene
     */
    public RayTracerBasic(Scene scene){

        super(scene);
    }

    /**
     * Calc the color of the ambient light of the scene
     *
     * @param point
     * @return the calculated color
     */
    private Color calcColor(Point point) {

        return scene.ambientLight.getIntensity();
    }

    /**
     * Calc the color of the pixel that the ray intersects
     *
     * @param ray to trace
     * @return the calculated color
     */
    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.findIntersections(ray);

        // If the ray doesn't intersect any geometry the color of the pixel is the background color
        if (intersections == null)
            return scene.background;

        //  Calculates the color of the closes point between the points that the ray intersects
        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }
}
