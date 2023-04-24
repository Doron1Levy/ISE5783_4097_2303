package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Plane implements Geometry {
	Point point;
	Vector normal;

	/**
	 * Constructs plane by three point on the plane
	 * 
	 * @param point1 1st point
	 * @param point2 2nd point
	 * @param point3 3rd point
	 */
	public Plane(Point point1, Point point2, Point point3) {
		point = point1;
		try { // try for case the consructor get all point on the same vector or at least two
				// point are the same
			normal = point1.subtract(point2).crossProduct(point1.subtract(point3)).normalize();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("your points are on the same vector");
		}
	}

	/**
	 * simple constructor
	 * 
	 * @param newPoint
	 * @param newVerticalVvector
	 */
	Plane(Point point, Vector normal) {
		this.point = point;
		this.normal = normal.normalize();
	}

	/**
	 * getter
	 * 
	 * @return point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * return normal vector
	 * 
	 * @return
	 */
	public Vector getNormal() {
		return normal;
	}

	/**
	 * getter
	 * 
	 * @param point
	 * @return normal
	 */
	@Override
	public Vector getNormal(Point point) {
		return normal;
	}

	/**
	 * print the Plan objects
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "Plane{" + "point=" + point + ", normal=" + normal + '}';
	}

	@Override
	public List<Point> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}