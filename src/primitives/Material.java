package primitives;

/**
 * Class represents the material of a Geometry
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Material {

    // The attenuation factors
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    public int nShininess = 0;


    /**
     * Sets the kD attenuation factor
     *
     * @param kD parameter for kD
     * @return Material object
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the kD attenuation factor
     *
     * @param kD parameter for kD
     * @return Material object
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }


    /**
     * Sets the kS attenuation factor
     *
     * @param kS parameter for kS
     * @return Material object
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the kS attenuation factor
     *
     * @param value parameter for kS constructor
     * @return Material object
     */
    public Material setKs(double value) {
        this.kS = new Double3(value);
        return this;
    }

    /**
     * Sets nShininess factor
     *
     * @param nShininess parameter for nShininess
     * @return Material object
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}