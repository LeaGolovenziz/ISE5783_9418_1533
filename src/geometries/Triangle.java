package geometries;

import primitives.Point;

/** Class Triangle represents a triangle in Cartesian 3-Dimensional coordinate system.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Triangle extends Polygon {

    /**
     * Constructor that initialize the triangle's point
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     */
    public Triangle(Point p1,Point p2,Point p3){
        super(p1,p2,p3);
    }
}
