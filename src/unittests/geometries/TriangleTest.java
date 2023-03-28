package unittests.geometries;

import org.junit.jupiter.api.Test;

import geometries.Triangle;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

	@Test
	void getNormal() {
		// =============== Equivalence Partitions Tests ==============
		// TC01: constructor acting well
		try {
			new Triangle(new Point(0, 1, 0), new Point(1, 0, 0), new Point(1, 1, 0));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct triangle");
		}

		// TC02: simple check
		Triangle t = new Triangle(new Point(0, 1, 0), new Point(1, 0, 0), new Point(1, 1, 0));
		assertEquals(new Vector(-0, -0, -1), t.getNormal(new Point(0, 1, 0)), "the normal not correct!");
	}
}