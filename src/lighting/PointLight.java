package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.*;

import static primitives.Util.isZero;

/**
 * Class represents a light source from a point
 * Extends abstract class Light
 * Implements interface LightSource
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class PointLight extends Light implements LightSource {

    private Point position; // The position point in which the light lies

    // The attenuation factors
    private double kC = 1;
    private double KL = 0;
    private double KQ = 0;

    /**
     * Constructor
     *
     * @param intensity parameter for Field intensity in super
     * @param position  parameter for field position
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Builder pattern setter for field kC
     *
     * @param kC parameter for field kC
     * @return PointLight object
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Builder pattern setter for field KL
     *
     * @param KL parameter for field KL
     * @return PointLight object
     */
    public PointLight setKL(double KL) {
        this.KL = KL;
        return this;
    }

    /**
     * Builder pattern setter for field KQ
     *
     * @param KQ parameter for field KQ
     * @return PointLight object
     */
    public PointLight setKQ(double KQ) {
        this.KQ = KQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        double ds = p.distanceSquared(position);
        Color i0 = super.getIntensity();

        //intensity reduced by attenuation factors and distance according to formula:
        //i0/(kc+d*KL+d^2*KQ)
        return i0.reduce(kC + KL * d + KQ * ds);
    }

    @Override
    public Vector getL(Point p) {
        // If the point is the position point - return null
        if (p.equals(position)) {
            return null;
        }

        // (p - position)
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point p) {
        return p.distance(this.position);
    }
}