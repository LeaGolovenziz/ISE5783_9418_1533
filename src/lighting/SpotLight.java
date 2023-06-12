package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

/**
 * Class represents a light from a point with direction
 * Extends class PointLight
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class SpotLight extends PointLight {

    private Vector direction; // The direction of the light

    /**
     * Constructor
     *
     * @param intensity parameter for field intensity in super
     * @param position  parameter for field position in super
     * @param direction parameter for field direction
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double proj = direction.dotProduct(getL(p)); //direction*(psition-p) , projection of light on point
        //if the light source doesn't hit the point return color black
        if (Util.isZero(proj))
            return Color.BLACK;

        double factor = Math.max(0, proj);
        Color i0 = super.getIntensity(p);

        // i0*(max(0,direction*(position-p))/(kC+d*KL+ds*KQ)
        return i0.scale(factor);
    }
}