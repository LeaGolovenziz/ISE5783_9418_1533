package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import java.util.List;
import static java.lang.Math.*;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class represents a basic ray tracer that implements the ray tracing algorithm
 * It calculates the color of the intersection of a ray with the scene
 *
 * The class extends the RayTracerBase class
 *
 * @author Lea GOloventz and Naomi Reitzer
 */
public class RayTracerBasic extends RayTracerBase{
    private static final double DELTA = 0.1;  // Constant size for shadow rays
    private static final int MAX_CALC_COLOR_LEVEL = 10; // The max level of the recursion attending to reflection and transparency
    private static final double MIN_CALC_COLOR_K = 0.001; // The minimal effect of a color factor for transparency and reflection
    private static final double INITIAL_K = 1.0; // Initial value of the effect of a color factor for transparency and reflection

    /**
     * Constructs a RayTracerBasic object with the given scene
     *
     * @param scene The scene to be rendered
     */
    public RayTracerBasic(Scene scene)
    {
        super(scene);
    }

    /**
     * Find the intersections between the ray and the scene
     *
     * @param ray Ray to trace
     *
     * @return Color of intersection point
     */
    @Override
    public Color traceRay(Ray ray) {
        if (this.findClosestIntersection(ray) != null)
            return calcColor(this.findClosestIntersection(ray), ray);

        return scene.background;
    }


    /**
     * Calculates the color of the intersection point using a recursive function.
     *
     * @param intersection The intersection point.
     * @param ray The ray.
     * @param level The recursion level.
     * @param k The attenuation factor.
     *
     * @return The color of the intersection.
     */
    private Color calcColor(GeoPoint intersection, Ray ray,int level,Double3 k) {
        Color color = calcLocalEffect(intersection,ray.getDirection(),k);
        if(level == 1)
            return color;
        return color.add(calcGlobalEffects(intersection, ray.getDirection(), level, k));
    }

    /**
     * Calculates the color of the intersection point.
     *
     * @param geopoint The point of intersection.
     * @param ray The ray.
     *
     * @return The color of the intersection.
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL,new Double3(INITIAL_K))
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * Calculates the global effect of reflection and refraction on a point.
     *
     * @param ray The ray.
     * @param level The recursion level.
     * @param kx The attenuation factor.
     * @param kkx The reflection level affected by k.
     *
     * @return The color of the global effects.
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);

        if (gp == null) {
            return scene.background;
        }

        return calcColor(gp, ray, level - 1, kkx).scale(kx);
    }


    /**
     * Calculates the global effects of reflection and refraction on a point.
     *
     * @param intersection The intersection point.
     * @param inRay The direction of the incoming ray.
     * @param level The recursion level.
     * @param k The attenuation factor.
     *
     * @return The color of the global effects.
     */
    private Color calcGlobalEffects(GeoPoint intersection, Vector inRay, int level, Double3 k) {
        Color color = Color.BLACK; // The base color
        Vector n = intersection.geometry.getNormal(intersection.point); // The normal

        // Reflection attenuation of the material
        Double3 kr = intersection.geometry.getMaterial().kR;
        //reflection level as affected by k
        Double3 kkr = kr.product(k);

        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) { // If the reflection level is not lower than the minimum
            // Construct a reflection  ray from the point
            Ray reflectedRay = constructReflectedRay(n, intersection.point, inRay);

            // Add this color to the point by recursively calling calcGlobalEffect
            color = calcGlobalEffect(reflectedRay, level, kr, kkr);
        }


        // Transparency  attenuation factor of the material
        Double3 kt = intersection.geometry.getMaterial().kT;
        // Transparency level
        Double3 kkt = kt.product(k);

        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {// If the transparency level is not lower than the minimum
            // Construct a refracted ray from the point
            Ray refractedRay = constructRefractedRay(n, intersection.point, inRay);

            // Add to the color to the point by recursively calling calcGlobalEffect
            color = color.add(calcGlobalEffect(refractedRay, level, kt, kkt));
        }

        return color;
    }


    /**
     * Calculates the local effects of reflection and refraction on a point.
     *
     * @param intersection The intersection point.
     * @param v The direction of the ray.
     * @param k The attenuation factor.
     *
     * @return The color of the local effect.
     */
    private Color calcLocalEffect(GeoPoint intersection, Vector v, Double3 k) {
        Vector n = intersection.geometry.getNormal(intersection.point);

        double nv = alignZero(n.dotProduct(v));
        if (isZero(nv)) {
            return Color.BLACK;
        }

        int nShininess = intersection.geometry.getMaterial().nShininess;
        Double3 kd = intersection.geometry.getMaterial().kD;
        Double3 ks = intersection.geometry.getMaterial().kS;

        Color color = intersection.geometry.getEmission(); // Base color

        // For each light source in the scene
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point); // The direction from the light source to the point
            double nl = alignZero(n.dotProduct(l));
            // If sign(nl) == sign(nv) (if the light hits the point add it, otherwise don't add this light)
            if (nl * nv > 0) {
                // Ktr is the level of shade on the point (according to transparency of material)
                Double3 ktr = transparency(intersection, l, n,lightSource );
                if (!(ktr.product(k)).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * Checks whether a point is shaded by other objects
     *
     * @param gp          the point
     * @param l           the direction of the light
     * @param n           normal to the point
     * @param lightSource the light source
     * @param nv          the dot product between the normal and the view direction
     *
     * @return true if the point is shaded
     */
    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource lightSource, double nv) {
        Vector lightDirection = l.scale(-1); // Vector from the point to the light source

        Ray lightRay = new Ray(gp.point, lightDirection,n);

        double lightDistance = lightSource.getDistance(gp.point);
        // Finding only points that are closer to the point than the light
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        // If there are no intersections return true (there is no shadow)
        if (intersections == null) {
            return true;
        }

        // For each intersection point
        for (GeoPoint intersection : intersections) {
            // If the material is not transparent return false
            if (intersection.geometry.getMaterial().kT == Double3.ZERO) {
                return false;
            }

        }
        return true;
    }


    /**
     * Calculates the specular light effect according to Phong's model
     *
     * @param ks specular attenuation factor
     * @param l the direction of the light
     * @param n normal from the point
     * @param v direction of the viewer
     * @param nShininess the exponent
     * @param lightIntensity the intensity of the light source at the point
     *
     * @return the color of the point
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        double ln = alignZero(l.dotProduct(n));
        Vector r = l.subtract(n.scale(2 * ln)).normalize(); // r=l-2*(l*n)*n
        double vr = alignZero(v.dotProduct(r));
        double vrnsh = pow(max(0, -vr), nShininess); // vrnsh=max(0,-vr)^nshininess
        return lightIntensity.scale(ks.scale(vrnsh)); // Ks * (max(0, - v * r) ^ Nsh) * Il
    }

    /**
     * Calculates the diffusive light effect according to Phong's model
     *
     * @param kd the coefficient for diffusive
     * @param l the vector from the light source
     * @param n the normal to the point
     * @param lightIntensity  the light intensity
     *
     * @return the diffusive light
     */
    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double ln = alignZero(abs(l.dotProduct(n))); // ln=|l*n|
        return lightIntensity.scale(kd.scale(ln)); // Kd * |l * n| * Il
    }

    /**
     * Constructs the ray refracted by an intersection with the geometry
     *
     * @param n        the normal to the geometry at the intersection
     * @param point    the intersection point
     * @param innerVec the ray entering
     *
     * @return the refracted ray (in this implementation, a new ray with the same characteristics)
     */
    private Ray constructRefractedRay(Vector n, Point point, Vector innerVec) {
        return new Ray(point,n,innerVec);
    }

    /**
     * Construct the ray getting reflected on an intersection point
     *
     * @param n normal to the point
     * @param point the intersection point
     * @param innerVec the ray entering at the intersection
     *
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Vector n, Point point, Vector innerVec) {
        // r = v - 2 * (v*n) * n
        // r is the reflected ray
        Vector r = null;
        try {
            r = innerVec.subtract(n.scale(innerVec.dotProduct(n)).scale(2)).normalize();
        } catch (Exception e) {
            return null;
        }
        return new Ray(point, n,r);
    }

    /**
     * Find the closest intersection point between a ray base and the scene's geometries
     *
     * @param ray the ray
     *
     * @return the closest point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> geoPoints = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(geoPoints);
    }

    /**
     * Calculates the transparency of a point, like unshaded but returns amount of shading
     *
     * @param gp the point on the geometry that we're currently shading
     * @param l the vector from the point to the light source
     * @param n the normal vector of the point
     * @param ls the light source
     *
     * @return the transparency of the point
     */
    private Double3 transparency(GeoPoint gp, Vector l, Vector n, LightSource ls) {
        Vector lightDir = l.scale(-1);
        Vector epsVector = n.scale(n.dotProduct(lightDir) > 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, n, lightDir);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        Double3 ktr = new Double3(1);
        if (intersections != null) {
            for (GeoPoint gp2 : intersections) {
                if (point.distance(gp2.point) < ls.getDistance(point)) {
                    ktr=gp2.geometry.getMaterial().kT.product(ktr);
                }
            }
        }
        return ktr;
    }
}