package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * Class represents directional light in a scene
 * Extends abstract class Light and implements interface LightSource
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector direction; // The direction of the light

    /**
     * Constructor
     * @param intensity parameter for field intensity in super
     * @param direction parameter for field direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }

    @Override
    public double getDistance(Point p) {
        return Double.POSITIVE_INFINITY;
    }
}

