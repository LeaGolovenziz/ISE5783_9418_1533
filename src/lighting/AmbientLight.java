package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * The AmbientLight class represents ambient light in a scene.
 * It determines the overall light intensity and color that is present
 * everywhere in the scene, regardless of position or direction.
 *
 * This class provides methods to set and retrieve the intensity of the ambient light.
 *
 * Ambient light is defined by an intensity value, which is multiplied by a coefficient
 * to determine the final ambient light color.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class AmbientLight {

    private final Color intensity;
    public static AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructs an AmbientLight object with the given intensity and coefficient.
     * The intensity is multiplied by the coefficient to determine the final ambient light color.
     *
     * @param Ia The intensity of the ambient light
     * @param Ka The coefficient of the ambient light
     */
    public AmbientLight(Color Ia, Double3 Ka){
        intensity = Ia.scale(Ka);
    }

    /**
     * Constructs an AmbientLight object with the given intensity and coefficient.
     * The intensity is multiplied by the coefficient to determine the final ambient light color.
     *
     * @param Ia The intensity of the ambient light
     * @param Ka The coefficient of the ambient light
     */
    public AmbientLight(Color Ia, double Ka){
        intensity = Ia.scale(Ka);
    }

    /**
     * Retrieves the intensity of the ambient light.
     *
     * @return The intensity of the ambient light
     */
    public Color getIntensity(){
        return intensity;
    }
}
