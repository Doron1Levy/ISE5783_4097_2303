package unittests.geometries;

import org.junit.jupiter.api.Test;
import java.util.List;
import geometries.Triangle;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class to check all function of triangle class
 * 
 * @author David
 *
 */
class TriangleTest {

	/**
	 * get normal
	 */
	@Test
	void getNormal() {
		// =============== Equivalence Partitions Tests ==============
		// TC01: constructor acting well
		assertThrows(IllegalArgumentException.class,
				() -> new Triangle(new Point(0, 1, 0), new Point(0, 1, 0), new Point(1, 1, 0)), "ERROR: TC01)");

		// TC02: simple check
		Triangle t = new Triangle(new Point(0, 1, 0), new Point(1, 0, 0), new Point(1, 1, 0));

		boolean bool = new Vector(0, 0, -1).equals(t.getNormal(new Point(0, 1, 0)))
				|| new Vector(0, 0, 1).equals(t.getNormal(new Point(0, 1, 0)));
		assertTrue(bool, "ERROR: TC02");

	}

	/*
	 * Test method for {@link
	 * geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntersections() {

		Triangle triangle = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(-1, 0, 0));
		List<Point> points;
		// ============ Equivalence Partitions Tests ==============

		// TC01: Inside triangle
		points = triangle.findIntersections((new Ray(new Point(0, 2, 0.5), new Vector(0, -1, 0))));

		assertEquals(List.of(new Point(0, 0, 0.5)), points, "ERROR: Ray Inside when the triangle");

		// TC02: Outside against edge
		assertNull(triangle.findIntersections((new Ray(new Point(0.5, -2, -1), new Vector(0, 1, 0)))),
				"Ray starts outside against edge");

		// TC03: Outside against vertex
		assertNull(triangle.findIntersections((new Ray(new Point(1.5, -2, -0.2), new Vector(0, 1, 0)))),
				"Ray starts outside against vertex");

		// =============== Boundary Values Tests ==================

		// TC11: the point is on edge
		assertNull(triangle.findIntersections((new Ray(new Point(0.5, -2, 0), new Vector(0, 1, 0)))),
				"Ray's point is on the edge");

		// TC12: the point is in vertex
		assertNull(triangle.findIntersections((new Ray(new Point(1, -1, 0), new Vector(0, 1, 0)))),
				"Ray's point is in vertex");

		// TC13: the point is On edge's continuation
		assertNull(triangle.findIntersections((new Ray(new Point(2, -2, 0), new Vector(0, 1, 0)))),
				"Ray's point is On edge's continuation");
	}
}