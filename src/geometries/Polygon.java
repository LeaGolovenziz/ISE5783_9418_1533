
package geometries;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon implements Geometry {
    /**
     * List of polygon's vertices
     */
    protected final List<Point> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected final Plane plane;
    private final int size;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by
     *                 edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        size = vertices.length;

        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (size == 3) return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();
        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal();
    }

    /**
     * Finds intersections between a ray and a polygon by checking if the ray intersects the plane containing the polygon
     * <p>
     * and then verifying if the intersection point is inside the polygon.
     *
     * @param ray the ray to check for intersection
     * @return a list of intersection points between the ray and the polygon, or null if there are no intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        // Find intersection points between the ray and the plane containing the polygon
        List<Point> planeIntersections = plane.findIntersections(ray);

        // If there are no intersection points, return null
        if (planeIntersections == null) {
            return null;
        }

        // Get the first three vertices of the polygon
        Point P0 = ray.getP0();
        Vector v = ray.getDirection();
        Point P1 = vertices.get(1);
        Point P2 = vertices.get(0);

        // Calculate the vectors v1 and v2 from P0 to P1 and P0 to P2, respectively
        Vector v1 = P0.subtract(P1);
        Vector v2 = P0.subtract(P2);

        // Calculate the sign of the dot product between v and the cross product of v1 and v2
        double sign1 = alignZero(v.dotProduct(v1.crossProduct(v2)));
        double sign2;

        // If the sign is zero, the ray does not intersect the polygon
        if (isZero(sign1)) {
            return null;
        }

        // Iterate through all vertices of the polygon
        for (int i = vertices.size() - 1; i > 0; --i) {
            v1 = v2;
            v2 = P0.subtract(vertices.get(i));

             // Calculate the sign of the dot product between v and the cross product of v1 and v2
             sign2 = alignZero(v.dotProduct(v1.crossProduct(v2)));

            // If the sign is zero, the ray does not intersect the polygon
            if (isZero(sign2)) {
                return null;
            }

            // Check if the signs of the dot products change between consecutive vertices of the polygon
            if ((sign1>0) != (sign2 > 0)) {
                return null;
            }
        }

        // If the intersection point is inside the polygon, return it
        Point point = planeIntersections.get(0);
        return List.of(point);
    }
}
