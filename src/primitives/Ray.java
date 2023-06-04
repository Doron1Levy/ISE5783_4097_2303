package primitives;

import java.util.List;

/**
 * Ray class represents 3D ray
 */
public class Ray {
	private final Point p0;
	private final Vector direction;

	/**
	 * simple constructor
	 * 
	 * @param p0        start point
	 * @param direction direction vector
	 */
	public Ray(Point p0, Vector direction) {
		this.p0 = p0;
		this.direction = direction.normalize();
	}

	/**
	 * return the p0 point
	 * 
	 * @return p0
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * getter
	 * 
	 * @return direction vector
	 */
	public Vector getDirection() {
		return direction;
	}

	/**
	 * get point on the ray
	 * 
	 * @param length distance from the start of the ray
	 * @return new Point3D
	 */
	public Point getPoint(double length) {
		return Util.isZero(length) ? p0 : p0.add(direction.scale(length));
	}

	@Override
	public String toString() {
		return p0 + "->" + direction;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Ray ray) && p0.equals(ray.p0) && direction.equals(ray.direction);
	}

	/**
	 * this function get list of points and return the closest point to p0 of the
	 * ray
	 * 
	 * @param points list of point for search
	 * @return closestPoint closest point to p0      
	 */
	public Point findClosestPoint(List<Point> points) {
		if (points.isEmpty() || points == null)
			return null;

		double minDistance = Double.POSITIVE_INFINITY;
		Point closestPoint = null;
		for (var p : points) {
			double distance = p.distanceSquared(p0);
			if (distance < minDistance) {
				minDistance = distance;
				closestPoint = p;
			}
		}
		return closestPoint;
	}

}