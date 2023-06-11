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

    public Double3 kT=Double3.ZERO; //for transparency
    public Double3 kR=Double3.ZERO; //for reflection


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
     * Sets the field kT
     *
     * @param kt parameter for kt
     * @return Material object
     */
    public Material setKT(Double3 kt) {
        this.kT = kt;
        return this;
    }

    /**
     * Sets the field kT
     *
     * @param kt parameter for kt
     * @return Material object
     */
    public Material setKT(double kt) {
        this.kT =new Double3(kt);
        return this;
    }


    /**
     * Sets the field kR
     *
     * @param kR parameter for kR
     * @return Material object
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Sets the field kR
     *
     * @param kR parameter for kR
     * @return Material object
     */
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
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