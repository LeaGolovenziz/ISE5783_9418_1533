package renderer;

import geometries.Intersectable;
import lighting.AmbientLight;
import lighting.LightSource;
import lighting.PointLight;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.pow;
import static primitives.Util.alignZero;

/**
 * The RayTracerBasic class represents a basic ray tracer.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class RayTracerBasic extends RayTracerBase {

    private static final double DELTA = 0.1; // Movement of the shadow rays

    /**
     * Constructor
     *
     * @param scene
     */
    public RayTracerBasic(Scene scene) {

        super(scene);
    }

    /**
     * Calculate the specular factor and change the color by it, Light created by a special break of light
     *
     * @param material specular attenuation factor
     * @param n        normal from the point
     * @param l        the direction of the light
     * @param nl       the dot product between the light and the normal
     * @param v        direction of the viewer
     * @return the intensity of the specular
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl)); // r=l-2*(l*n)*n
        double vr = alignZero(v.dotProduct(r)); // vr=v*r
        double vrnsh = pow(max(0, -vr), material.nShininess); // vrnsh=max(0,-vr)^nshininess
        return material.kS.scale(vrnsh); //Ks * (max(0, - v * r) ^ Nsh)
    }

    /**
     * Calculate the diffuse light effect on the point
     *
     * @param material diffuse attenuation factor
     * @param nl       the dot product between the light and the normal
     * @return the intensity of the diffusive
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl)); // Kd * |nl|
    }

    /**
     * Calculate the local effect of light sources on a point
     *
     * @param intersection is the point
     * @param ray          is the ray from the viewer
     * @return the color of the local effect
     */
    private Color calcLocalEffect(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDirection();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v)); //nv=n*v

        if (nv == 0) {
            return Color.BLACK;
        }

        Material material = intersection.geometry.getMaterial();
        Color color = intersection.geometry.
                getEmission(); // Base color

        // For each light source in the scene
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point); //the direction from the light source to the point
            double nl = alignZero(n.dotProduct(l)); //nl=n*l

            // If sign(nl) == sing(nv) (if the light hits the point add it, otherwise don't add this light)
            if (nl * nv > 0) {
                if(unshaded(intersection, lightSource, l, n, nv)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(lightIntensity.scale(calcDiffusive(material, nl)),
                            lightIntensity.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }


    /**
     * Calc the color of the ambient light of the scene
     *
     * @param intersection
     * @param ray
     * @return the calculated color
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity().add(intersection.geometry.getEmission()).add(calcLocalEffect(intersection, ray));
    }

    /**
     * Calc the color of the pixel that the ray intersects
     *
     * @param ray to trace
     * @return the calculated color
     */
    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.findGeoIntersectionsHelper(ray);

        // If the ray doesn't intersect any geometry the color of the pixel is the background color
        if (intersections == null)
            return scene.background;

        // Calculate the color of the closes point between the points that the ray intersects
        GeoPoint closestGeoPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestGeoPoint, ray);
    }

    private boolean unshaded(GeoPoint geopoint, LightSource lightSource, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector deltaVector = n.scale(nv < 0 ? DELTA : DELTA * -1);
        Point point = geopoint.point.add(deltaVector);
        Ray lightRay = new Ray(point, lightDirection);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return true;

        double lightDistance = lightSource.getDistance(geopoint.point);

        // go over the intersection points and check if someone intersects a geometry
        for (GeoPoint gp : intersections) {
            // If there are geometries between the point to the light - calculate the transparency
            // In order to know how much shadow there is on the point
            if (Util.alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0)
                return false;
        }
        return true;
    }
}
