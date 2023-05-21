package geometries;

/**
 * An abstract class from which all bodies that hold a radius field inherit
 */
public abstract class RadialGeometry implements Geometry {

	/** the radius of a rounded part of the geometry body */
	protected final double radius;
	/** the squared radius of a rounded part of the geometry body */
	protected final double radiusSquared;

	/**
	 * constructor (using super constructor)
	 * 
	 * @param radius
	 */
	public RadialGeometry(double radius) {
		this.radius = radius;
		this.radiusSquared = radius * radius;
	}

	/**
	 * getter
	 * 
	 * @return rounded object radius
	 */
	public double getRadius() {
		return radius;
	}
}
