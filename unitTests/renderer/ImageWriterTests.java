package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * Testing ImageWriter Class
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
class ImageWriterTests {

    /**
     * Test method for {@link ImageWriter#writeToImage()}.
     * Creates an image and writes colors to it.
     */
    @Test
    void testWriteToImage() {
        int nX = 800; // Number of pixels along the X-axis
        int nY = 500; // Number of pixels along the Y-axis

        // Create a new ImageWriter with the given dimensions
        ImageWriter imageWriter = new ImageWriter("pinkgreen", nX, nY);

        // Define the colors
        Color pink = new Color(255d, 0d, 255d);
        Color green = new Color(109, 150, 46);

        // Iterate over each pixel in the image
        for (int j = 0; j < nX; j++) {
            for (int i = 0; i < nY; i++) {
                // Create a 16x10 grid pattern
                if (j % 50 == 0 || i % 50 == 0) {
                    // Set the pixel color to green if the coordinates are divisible by 50
                    imageWriter.writePixel(j, i, green);
                } else {
                    // Set the pixel color to pink for all other coordinates
                    imageWriter.writePixel(j, i, pink);
                }
            }
        }

        // Write the image to a file
        imageWriter.writeToImage();
    }
}