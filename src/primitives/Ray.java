package primitives;

import java.util.List;

/**
 * Ray class represents 3D ray
 */
public class Ray {

	final Point p0;
	final Vector direction;

	/**
	 * simple constructor
	 * 
	 * @param start     point
	 * @param direction vector
	 */
	public Ray(Point point, Vector vector) {
		this.p0 = point;
		this.direction = vector.normalize();
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
		return p0.toString() + direction.toString(); // is that correct?
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
	 * @param list of point
	 * @return closest point to p0      
	 */
	public Point findClosestPoint(List<Point> points) {
		if (points.isEmpty() || points == null)
			return null;

		return points.stream().min((p1, p2) -> Double.compare(p0.distanceSquared(p1), p0.distanceSquared(p2))).get();
	}

}