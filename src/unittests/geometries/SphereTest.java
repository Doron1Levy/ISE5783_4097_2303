package unittests.geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import geometries.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
	Sphere sphere1 = new Sphere(10, new Point(1, 2, 3));// positive coordinate
	Sphere sphere2 = new Sphere(10, new Point(-1, -2, -3));// negative coordinate
	Sphere sphere3 = new Sphere(10, new Point(-1, 2, -3));// positive and negative coordinate
	Point p1 = new Point(1, 5, 8);// positive coordinate
	Point p2 = new Point(-1, -2, -4);// negative coordinate
	Point p3 = new Point(1, 2, -7);// positive and negative coordinate

	/***
	 * The function checks the integrity of the getNormal function
	 */
	@Test
	void getNormal() {
		// positive coordinate sphere and point
		// assertEquals(new
		// Vector(0,3/Math.sqrt(36),5/Math.sqrt(36)),sphere1.getNormal(p1), " ");
		// positive coordinate of sphere and negative coordinate point
		assertEquals(new Vector(-2 / Math.sqrt(69), -4 / Math.sqrt(69), -7 / Math.sqrt(69)), sphere1.getNormal(p2),
				"ERROR:normal test of sphere (positive coordinate of sphere and negative coordinate point) ");
		// negative coordinate sphere and point
		assertEquals(new Vector(0, 0, -1), sphere2.getNormal(p2),
				"ERROR:normal test of sphere (negative coordinate sphere and point) ");
		// negative coordinate of sphere and positive coordinate point
		assertEquals(new Vector(2 / Math.sqrt(174), 7 / Math.sqrt(174), 11 / Math.sqrt(174)), sphere2.getNormal(p1),
				"ERROR:normal test of sphere (negative coordinate of sphere and positive coordinate point) ");
		// positive and negative coordinate sphere and point
		assertEquals(new Vector(2 / Math.sqrt(20), 0 / Math.sqrt(20), -4 / Math.sqrt(20)), sphere3.getNormal(p3),
				"ERROR:normal test of sphere (positive and negative coordinate sphere and point)");
	}
	
	/**
	 * 
	 */
	@Test
	void TestFindIntersections() {
		
	}

}