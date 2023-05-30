package lighting;

import primitives.Color;

/**
 * Abstract class represents light
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
abstract class Light {
    private Color intensity; // Intensity of the light

    /**
     * Constructor
     * @param intensity parameter for field intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Getter of intensity
     * @return intensity field
     */
    public Color getIntensity() {
        return intensity;
    }
}