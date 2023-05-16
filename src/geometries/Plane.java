package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

/**
 * Plane class represents two-dimensional plane in 3D Cartesian coordinate
 * system
 */
public class Plane implements Geometry {

	private final Point p0;
	private final Vector normal;

	/**
	 * Constructs plane by three point on the plane
	 * 
	 * @param point1 1st point
	 * @param point2 2nd point
	 * @param point3 3rd point
	 */
	public Plane(Point point1, Point point2, Point point3) {
		p0 = point1;
		try { // try for case the constructor get all point on the same vector or at least two
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
	 * @param new Vertical vector
	 */
	public Plane(Point point, Vector normal) {
		this.p0 = point;
		this.normal = normal.normalize();
	}

	/**
	 * getter return the center of plane
	 * 
	 * @return return the point that declared the plane
	 */
	public Point getPoint() {
		return p0;
	}

	/**
	 * return normal vector
	 * 
	 * @return the normal of plane
	 */
	public Vector getNormal() {
		return normal;
	}

	/**
	 * getter
	 * 
	 * @param The point where we are looking for normal
	 * @return normal at point
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
		return "Plane{" + "point=" + p0 + ", normal=" + normal + '}';
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		Point rayP0 = ray.getP0();
		Vector rayDirection = ray.getDirection();

		// Ray starts begins in the same point which appears as reference point in the
		// plane (0 points)
		if (rayP0 == this.p0)
			return null;

		/**
		 * calculate the dotProduct between the ray direction and normal plane
		 */
		double dotProduct = this.normal.dotProduct(rayDirection);

		// Checking whether the plane and the ray intersect each other or are parallel
		// to each other
		if (Util.isZero(dotProduct)) {
			return null;
		}

		// direction to plane p0 from ray p0
		Vector p0direction = p0.subtract(rayP0);

		/**
		 * checking if direction of ray is to plane if directionRayScale < 0 the ray
		 * direction of the beam is not to the surface of plane if directionRayScale = 0
		 * the ray is on surface of plane if directionRayScale > 0 the ray direction of
		 * the beam is cut the surface of plane
		 */
		double directionRayScale = Util.alignZero(this.normal.dotProduct(p0direction) / dotProduct);

		if (directionRayScale > 0) {
			// find the intersection by dot product between the direction to plane from the
			// po ray and
			// directionRayScale (which calculates the distance between the point and the
			// surface in the given direction)
			return List.of(rayP0.add(rayDirection.scale(directionRayScale)));
		}

		return null;
	}
}