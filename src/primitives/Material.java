package primitives;

/**
 * Class represents the material of a Geometry
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Material {

    // The attenuation factors
    public Double3 KD = Double3.ZERO;
    public Double3 KS = Double3.ZERO;
    public int NShininess = 0;

    public Double3 KT=Double3.ZERO; //for transparency
    public Double3 KR=Double3.ZERO; //for reflection


    /**
     * Sets the KD attenuation factor
     *
     * @param KD parameter for KD
     * @return Material object
     */
    public Material setKD(Double3 KD) {
        this.KD = KD;
        return this;
    }

    /**
     * Sets the KD attenuation factor
     *
     * @param KD parameter for KD
     * @return Material object
     */
    public Material setKD(double KD) {
        this.KD = new Double3(KD);
        return this;
    }


    /**
     * Sets the KS attenuation factor
     *
     * @param KS parameter for KS
     * @return Material object
     */
    public Material setKS(Double3 KS) {
        this.KS = KS;
        return this;
    }

    /**
     * Sets the KS attenuation factor
     *
     * @param value parameter for KS constructor
     * @return Material object
     */
    public Material setKS(double value) {
        this.KS = new Double3(value);
        return this;
    }

    /**
     * Sets the field KT
     *
     * @param KT parameter for KT
     * @return Material object
     */
    public Material setKT(Double3 KT) {
        this.KT = KT;
        return this;
    }

    /**
     * Sets the field KT
     *
     * @param KT parameter for KT
     * @return Material object
     */
    public Material setKT(double KT) {
        this.KT =new Double3(KT);
        return this;
    }


    /**
     * Sets the field KR
     *
     * @param KR parameter for KR
     * @return Material object
     */
    public Material setKR(Double3 KR) {
        this.KR = KR;
        return this;
    }

    /**
     * Sets the field KR
     *
     * @param KR parameter for KR
     * @return Material object
     */
    public Material setKR(double KR) {
        this.KR = new Double3(KR);
        return this;
    }

    /**
     * Sets NShininess factor
     *
     * @param NShininess parameter for NShininess
     * @return Material object
     */
    public Material setNShininess(int NShininess) {
        this.NShininess = NShininess;
        return this;
    }
}