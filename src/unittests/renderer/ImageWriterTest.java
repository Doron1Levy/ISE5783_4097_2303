package unittests.renderer;

import renderer.ImageWriter;
import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * test class to check all function of Image Writer class
 * 
 * @author David
 *
 */
class ImageWriterTest {

	@Test
	void testImageWriter() {

		// crate grid for test
		ImageWriter picture = new ImageWriter("grid", 800, 500);

		for (int i = 0; i < 800; i++) {
			for (int j = 0; j < 500; j++) {
				if (i % 50 == 0 || j % 50 == 0)
					picture.writePixel(i, j, new Color(255, 255, 255));

			}
		}
		picture.writeToImage();
	}

}
