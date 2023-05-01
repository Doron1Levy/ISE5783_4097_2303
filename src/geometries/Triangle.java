package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * class Triangle represents two-dimensional triangle in 3D space
 */
public class Triangle extends Polygon {
	/**
	 * constructor using at father constructor
	 * 
	 * @param point1
	 * @param point2
	 * @param point3
	 */
	public Triangle(Point point1, Point point2, Point point3) {
		super(point1, point2, point3);
	}

	@Override
	public List<Point> findIntersections(Ray ray) {

		Vector rayDirection = ray.getDirection();

		// point of ray p0
		Point p0 = ray.getP0();

		// 3 points of 3 triangle vertex
		Point p1 = vertices.get(0);
		Point p2 = vertices.get(1);
		Point p3 = vertices.get(2);

		// calculate the direction from any vertex to ray p0
		Vector vector1 = p1.subtract(p0);
		Vector vector2 = p2.subtract(p0);
		Vector vector3 = p3.subtract(p0);

		// calculate the cross product between 3 vectors
		Vector crossProduct1 = vector1.crossProduct(vector2);
		Vector crossProduct2 = vector2.crossProduct(vector3);
		Vector crossProduct3 = vector3.crossProduct(vector1);

		// calculate if the dot product between ray direction and vectors are positive
		// or negative
		double dotProduct1 = rayDirection.dotProduct(crossProduct1);
		double dotProduct2 = rayDirection.dotProduct(crossProduct2);
		double dotProduct3 = rayDirection.dotProduct(crossProduct3);

		// check if all dot product result is with same sign
		if ((dotProduct1 > 0 && dotProduct2 > 0 && dotProduct3 > 0)
				|| (dotProduct1 < 0 && dotProduct2 < 0 && dotProduct3 < 0)) {
			return plane.findIntersections(ray);
		}

		return null;
	}
}