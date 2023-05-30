package renderer;

import primitives.Color;
import primitives.Ray;
import primitives.Point;
import primitives.Vector;

import java.util.MissingResourceException;

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

    private ImageWriter imageWriter = null;
    private RayTracerBase rayTracerBase = null;

    /**
     * Constructor for Camera class.
     *
     * @param p0  the origin point in 3D space
     * @param vTo the vector representing the camera's view direction
     * @param vUp the vector representing the camera's up direction
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
     * @return the instance of Camera for method chaining
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * setter
     *
     * @param imageWriter {@link ImageWriter}
     * @return the camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * setter
     *
     * @param rayTracerBase {@link RayTracerBase}
     * @return this camera
     */
    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
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

    /**
     * Color all the pixels of the image
     */
    public Camera renderImage() {
        if (p0 == null || vRight == null
                || vUp == null || vTo == null || distance == 0
                || width == 0 || height == 0 || p0 == null
                || imageWriter == null || rayTracerBase == null) {
            throw new MissingResourceException("Missing camera data", Camera.class.getName(), null);
        }

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        // GO over all the pixels of the view plane
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                // Construct a ray through the current pixel
                Ray ray = this.constructRay(nX, nY, j, i);
                // Color the pixel
                imageWriter.writePixel(j, i, rayTracerBase.traceRay(ray));
            }
        }
        return this;
    }

    /**
     * Print the grid
     *
     * @param interval between grid rows and columns
     * @param color    of the grid
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("Missing image writer", ImageWriter.class.getName(), null);

        // Go over all pixels and color the grid between the intervals
        for (int j = 0; j < imageWriter.getNy(); j++) {
            for (int i = 0; i < imageWriter.getNy(); i++) {
                if (j % interval == 0 || i % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    /**
     * Create a new image
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("missing image writer", ImageWriter.class.getName(), null);

        imageWriter.writeToImage();
    }


}



