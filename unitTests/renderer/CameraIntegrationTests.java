package renderer;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Integration tests for the Camera class, testing its ability to create rays that intersect with different geometries.
 * Tests are done on a 3x3 view plane. The expected intersection points are calculated and compared to the expected values.
 * If the expected amount of intersections does not match the actual amount, the test will fail.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
class CameraIntegrationTests {

    /**
     * Calculates the amount of intersections between a given camera and a given geometry, and compares it to the expected amount.
     * Prints the actual intersection points if any exist.
     *
     * @param cam      - the camera being tested
     * @param geo      - the geometry being tested
     * @param expected - the expected amount of intersections
     */
    private void assertCountIntersections(Camera cam, Intersectable geo, int expected) {
        int count = 0;

        List<Point> allpoints = null;

        int nX = 3;
        int nY = 3;
        //view plane 3X3 (WxH 3X3 & nx,ny =3 => Rx,Ry =1)
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                var intersections = geo.findIntersections(cam.constructRay(nX, nY, j, i));
                if (intersections != null) {
                    if (allpoints == null) {
                        allpoints = new LinkedList<>();
                    }
                    allpoints.addAll(intersections);
                }
                count += intersections == null ? 0 : intersections.size();
            }
        }

        System.out.format("there is %d points:%n", count);
        if (allpoints != null) {
            for (var item : allpoints) {
                System.out.println(item);
            }
        }
        System.out.println();

        assertEquals(expected, count, "Wrong amount of intersections");
    }

    /**
     * Integration tests for Camera's ability to construct rays that intersect with a sphere.
     */
    @Test
    public void cameraRaySphereIntegration() {
        Camera cam1 = new Camera(
                new Point(0, 0, 0),
                new Vector(0, 0, -1),
                new Vector(0, -1, 0))
                .setVPSize(3, 3)
                .setVPDistance(1);

        Camera cam2 = new Camera(
                new Point(0, 0, 0.5),
                new Vector(0, 0, -1),
                new Vector(0, -1, 0))
                .setVPSize(3, 3)
                .setVPDistance(1);
        // TC01: Small Sphere 2 points
        assertCountIntersections(cam1, new Sphere(new Point(0, 0, -3), 1), 2);

        // TC02: Big Sphere 18 points
        assertCountIntersections(cam2, new Sphere(new Point(0, 0, -2.5), 2.5), 18);

        // TC03: Medium Sphere 10 points
        assertCountIntersections(cam2, new Sphere(new Point(0, 0, -2), 2), 10);

        // TC04: Inside Sphere 9 points
        assertCountIntersections(cam2, new Sphere(new Point(0, 0, -1), 4), 9);

        // TC05: Beyond Sphere 0 points
        assertCountIntersections(cam1, new Sphere(new Point(0, 0, 1), 0.5), 0);
    }

    /**
     * This test checks the intersection between camera rays and planes.
     * It asserts that the expected number of intersection points is returned.
     */
    @Test
    public void cameraRayPlaneIntegration() {
        // Set up the camera
        Camera cam = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Plane against camera 9 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 0, 1)), 9);

        // TC02: Plane with small angle 9 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 2)), 9);

        // TC03: Plane parallel to lower rays 6 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), 6);

        // TC04: Beyond Plane 0 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, -1, 0)), 0);
    }

    /**
     * This test checks the intersection between camera rays and triangles.
     * It asserts that the expected number of intersection points is returned.
     */
    @Test
    public void cameraRayTriangleIntegration() {
        // Set up the camera
        Camera cam = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small triangle 1 point
        assertCountIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -1, -2)), 1);

        // TC02: Medium triangle 2 points
        assertCountIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2)), 2);
    }

    /**
     * This test checks the intersection between camera rays and geometries (a collection of shapes).
     * It asserts that the expected number of intersection points is returned.
     */
    @Test
    public void cameraRayGeometriesIntegration() {
        // Set up the camera
        Camera cam = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // Create a collection of geometries to test
        Geometries geometries = new Geometries(
                new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2)),  // 2
                new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), // 6
                new Sphere(new Point(0, 0, 1), 0.5), // 0
                new Sphere(new Point(0, 0, -3), 1) // 2
        );

        // Assert that the expected number of intersection points is returned
        assertCountIntersections(cam, geometries, 10);
    }
}

