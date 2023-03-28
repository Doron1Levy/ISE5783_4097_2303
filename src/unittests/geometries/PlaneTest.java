package unittests.geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

	@Test
	public void testConstructor() {

		// =============== Equivalence Partitions Tests ==============

		// TC01: constructor acting well
		try {
			new Plane(new Point(0, 1, 0), new Point(1, 0, 0), new Point(1, 1, 0));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct plane");
		}

		// =============== Boundary Values Tests ==================

		// TC11: three points in one ray
		assertThrows(IllegalArgumentException.class, //
				() -> new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(4, 8, 12)), //
				"Constructed a Plane with tree points in one ray");

		// TC12: two points unit
		assertThrows(IllegalArgumentException.class, //
				() -> new Plane(new Point(1, 2, 3), new Point(1, 2, 3), new Point(1, 1, 1)), //
				"Constructed a Plane with two same points");

	}

	@Test
	void getNormal() {
		Plane pl = new Plane(new Point(0, 1, 0), new Point(1, 0, 0), new Point(1, 1, 0));

		// =============== Equivalence Partitions Tests ==============
		// TC01:
		assertEquals(new Vector(-0, -0, -1), pl.getNormal(new Point(0, 1, 0)), "Bad normal to plane");

		// TC02: normal length (1)
		assertEquals(1d, pl.getNormal().length(), "ERROR: Normal diffrent than 1");
	}
}