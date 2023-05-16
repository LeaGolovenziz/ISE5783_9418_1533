package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * The RayTracerBase class provides an abstract framework for implementing ray tracing algorithms.
 *
 *  @author Naomi Reitzer and Leah Golovenziz
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Constructs a RayTracerBasic object with the specified scene.
     *
     * @param scene the scene to be rendered
     */
    public RayTracerBase(Scene scene){
        this.scene = scene;
    }

    /**
     * Traces a ray and determines the color of the intersected object.
     * This method should be implemented by subclasses.
     *
     * @param ray the ray to be traced
     * @return the color of the intersected object
     */
    public abstract Color traceRay(Ray ray);
}

