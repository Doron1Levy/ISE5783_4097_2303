package geometries;

import primitives.*;

/**
 * Cylinder class represents cylinder in 3D Cartesian coordinate system
 */
public class Cylinder extends Tube {

	private final double height;

	// ***************** Constructors ********************** //
	/**
	 * constructor get axis ray and positive radius
	 *
	 * @param axisRay
	 * @param radius
	 * @param height
	 */
	public Cylinder(Ray axisRay, double radius, double height) {
		super(radius, axisRay);
		this.height = height;
	}

	/**
	 * simple getter of filed "height"
	 * 
	 * @return high of the cylinder
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * calculate the cylinder normal
	 * 
	 * @param The point where we are looking for normal
	 * @return The normal of the cylinder at the point sent
	 */
	@Override
	public Vector getNormal(Point point) {

		Point p0 = getAxisRay().getP0();
		Vector dir = getAxisRay().getDirection();
		Point pTop = p0.add(dir.scale(getHeight()));

		// if the point is at the top of the cylinder
		if (point.equals(pTop) || Util.isZero(dir.dotProduct(point.subtract(pTop))))
			return dir;

		// if the point is at the base of the cylinder
		if (point.equals(p0) || Util.isZero(dir.dotProduct(point.subtract(p0))))
			return dir.scale(-1);

		return super.getNormal(point);
	}

	@Override
	public String toString() {
		return "Cylinder{" + super.toString() + "height=" + height + '}';
	}
}