package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * lass Sphere represents sphere in 3D space
 */
public class Sphere extends RadialGeometry {

	protected final Point center;

	/**
	 * constructor (using super constructor)
	 * 
	 * @param radius
	 * @param center
	 */
	public Sphere(double radius, Point center) {
		super(radius);
		this.center = center;
	}

	/**
	 * getter
	 * 
	 * @return Sphere center
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * getter
	 * 
	 * @return Sphere radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point point) {
		return point.subtract(center).normalize();
	}

	@Override
	public String toString() {
		return "Sphere{" + "center=" + center + ", radius=" + radius + '}';
	}

	@Override
	public List<Point> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
