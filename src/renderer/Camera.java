package renderer;

import primitives.Ray;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a virtual camera in a 3D environment.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Camera {
    private Point p0; // Camera view
    private Vector vRight; // vector pointing right from the camera's perspective
    private Vector vTo; // vector pointing towards the camera's view direction
    private Vector vUp; // vector pointing upwards from the camera's perspective

    private double height; // physical height of the view plane
    private double width; // physical width of the view plane
    private double distance; // distance between the camera and the view plane

    /**
     * Constructor for Camera class.
     *
     * @param p0  the origin point in 3D space
     * @param vTo the vector representing the camera's view direction
     * @param vUp the vector representing the camera's up direction
     *
     * @throws IllegalArgumentException if vTo and vUp are not orthogonal
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo))) {
            throw new IllegalArgumentException("vTo and vUp should be orthogonal");
        }

        this.p0 = p0;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = this.vTo.crossProduct(this.vUp);
    }

    /**
     * Getter method for vRight vector.
     *
     * @return the vector pointing right from the camera's perspective
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * Getter method for vTo vector.
     *
     * @return the vector pointing towards the camera's view direction
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * Getter method for vUp vector.
     *
     * @return the vector pointing upwards from the camera's perspective
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * Setter method for the physical width and height of the view plane.
     *
     * @param width  the physical width of the view plane
     * @param height the physical height of the view plane
     *
     * @return the instance of Camera for method chaining
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Setter method for the distance between the camera and the view plane.
     *
     * @param distance the distance between the camera and the view plane
     *
     * @return the instance of Camera for method chaining
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Constructs a ray that passes through a specific pixel on the camera's view plane.
     *
     * @param nX the number of columns in the view plane
     * @param nY the number of rows in the view plane
     * @param j  the index of the row of the desired pixel on the view plane
     * @param i  the index of the column of the desired pixel on the view plane
     * @return the ray from the camera to the specified pixel
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



