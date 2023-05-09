package renderer;

import primitives.Ray;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Camera {
    private Point p0; // Camera view
    private Vector vRight;
    private Vector vTo;
    private Vector vUp;

    private double height;
    private double width;
    private double distance;


    /**
     * @param p0  origin point in 3D space
     * @param vUp vector go upwards
     * @param vTo vector vTo
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo))) {
            throw new IllegalArgumentException("vTo and vUp should be orthogonal");
        }

        this.p0 = p0;
        // normalizing the positional vectors
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = this.vTo.crossProduct(this.vUp);
    }

    public Vector getvRight() {
        return vRight;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvUp() {
        return vUp;
    }

    /**
     * setting view plane size
     *
     * @param width  "physical" width
     * @param height "physical" height
     * @return instance of camera for chaining
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * set distance between the camera and its view plane
     *
     * @param distance the distance for the view plane
     * @return instance of camera for chaining
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * constructing a ray through a pixel
     *
     * @param nX represent number of columns in the view plane
     * @param nY represent number of rows in the view plane
     * @param j  represent the index of the row in the view plane
     * @param i  represent the index of the column in the view plane
     * @return ray from the camera to pixel[i,j]
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        //image center
        Point Pc = p0.add(vTo.scale(distance));

        //ratio (pixel width & height)
        double Ry = height / nY;
        double Rx = width / nX;

        //Pixel[i,j] center
        Point Pij = Pc;

        //delta values for going to Pixel[i,j] from Pc
        double yI = -(i - (nY - 1) / 2d) * Ry;
        double xJ = (j - (nX - 1) / 2d) * Rx;

        //if xJ is not zero
        if (!isZero(xJ)) {
            Pij = Pij.add(vRight.scale(xJ));
        }

        if (!isZero(yI)) {
            Pij = Pij.add(vUp.scale(yI));
        }
        return new Ray(p0, Pij.subtract(p0));    //return ray from the camera to pixel[i,j]
    }
}



