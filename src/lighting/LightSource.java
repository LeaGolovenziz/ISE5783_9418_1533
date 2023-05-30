package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * Interface for light sources to implement
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public interface LightSource {

    /**
     * Returns light intensity at given point
     * @param p the point
     * @return Color object
     */
    public Color getIntensity(Point p);

    /**
     * Returns the direction of the light at given point
     * @param p the point
     * @return Vector object
     */
    public Vector getL(Point p);
}
