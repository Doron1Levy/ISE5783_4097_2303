package unittests.primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;

class VectorTest {

	Vector vec1 = new Vector(1, 2, 3);
	Vector vec2 = new Vector(2, 4, 6);
	Vector vec3 = new Vector(1, 2, 4);
	Vector vec4 = new Vector(1, 4, -3);
	Vector vec5 = new Vector(-1, -2, -3);

	/**
	 * The function checks the integrity of the constructor if a test fails it will
	 * print a message
	 */
	@Test
	public void testConstructor() {
		assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0),
				"ERROR: the constructor test is failed");
	}

	/**
	 * The function checks the integrity of the dotProduct function if a test fails
	 * it will print a message
	 */
	@Test
	void dotProduct() {
		// =======Equivalence Partitions Tests=======
		// in the same direction
		assertEquals(28, vec1.dotProduct(vec2),
				"ERROR: the dotProduct test is failed (Two vectors with the same direction)");
		// sharp angle
		assertEquals(17, vec1.dotProduct(vec3), "ERROR: the dotProduct test is failed (Two vectors with sharp angle)");
		// Orthogonal angle
		assertEquals(0, vec1.dotProduct(vec4),
				"ERROR: the dotProduct test is failed (Two vectors with Orthogonal angle)");
		// Obtuse angle
		assertEquals(-3, vec3.dotProduct(vec4), "ERROR: the dotProduct test is failed (Two vectors with Obtuse angle)");
		// Inverted vector
		assertEquals(-14, vec1.dotProduct(vec5),
				"ERROR: the dotProduct test is failed (Two vectors with Inverted direction)");
	}

	/**
	 * The function checks the integrity of the crossProduct function if a test
	 * fails it will print a message
	 */
	@Test
	void crossProduct() {
		assertThrows(IllegalArgumentException.class, () -> vec1.crossProduct(vec2),
				"ERROR: the crossProduct test is failed (Two vectors with the same direction)");
		// sharp angle
		assertEquals(new Vector(-2, 1, 0), vec1.crossProduct(vec3),
				"ERROR: the crossProduct test is failed (Two vectors with sharp angle)");
		// Orthogonal angle
		assertEquals(new Vector(18, -6, -2), vec1.crossProduct(vec4),
				"ERROR: the crossProduct test is failed (Two vectors with Orthogonal angle)");
		// Obtuse angle
		assertEquals(new Vector(22, -7, -2), vec3.crossProduct(vec4),
				"ERROR: the crossProduct test is failed (Two vectors with Obtuse angle)");
		// Inverted vector
		assertThrows(IllegalArgumentException.class, () -> vec1.crossProduct(vec5),
				"ERROR: the crossProduct test is failed (Two vectors with Inverted direction)");
	}

	/**
	 * The function checks the integrity of the lengthSquared function if a test
	 * fails it will print a message
	 */
	@Test
	void lengthSquared() {
		// negative coordinate
		assertEquals(14, vec5.lengthSquared(), "ERROR: the lengthSquared test is failed (negative coordinate)");
		// positive coordinate
		assertEquals(14, vec1.lengthSquared(), "ERROR: the lengthSquared test is failed (positive coordinate)");
		// positive and negative coordinate
		assertEquals(26, vec4.lengthSquared(),
				"ERROR: the lengthSquared test is failed (negative and positive coordinate)");
	}

	/**
	 * The function checks the integrity of the length function if a test fails it
	 * will print a message
	 */
	@Test
	void length() {
		// Negative coordinate
		assertEquals(Math.sqrt(14), vec5.length(), "ERROR: the length test is failed (negative coordinate)");
		// positive coordinate
		assertEquals(Math.sqrt(14), vec1.length(), "ERROR: the length test is failed (positive coordinate)");
		// positive and negative coordinate
		assertEquals(Math.sqrt(26), vec4.length(),
				"ERROR: the length test is failed (negative and positive coordinate)");
	}

	/**
	 * The function checks the integrity of the normalize function if a test fails
	 * it will print a message
	 */
	@Test
	void normalize() {
		// negative coordinate
		assertEquals(new Vector(-1 / Math.sqrt(14), -2 / Math.sqrt(14), -3 / Math.sqrt(14)), vec5.normalize(),
				"ERROR: the normalize test is failed (negative coordinate)");
		// positive coordinate
		assertEquals(new Vector(1 / Math.sqrt(14), 2 / Math.sqrt(14), 3 / Math.sqrt(14)), vec1.normalize(),
				"ERROR: the normalize test is failed (positive coordinate)");
		// positive and negative coordinate
		assertEquals(new Vector(1 / Math.sqrt(26), 4 / Math.sqrt(26), -3 / Math.sqrt(26)), vec4.normalize(),
				"ERROR: the normalize test is failed (negative and positive coordinate)");
	}
}