package geometries;

public abstract class RadialGeometry implements Geometry {

	/**
	 * An abstract class from which all bodies that hold a radius field inherit
	 */
	protected final double radius;

	/**
	 * constructor (using super constructor)
	 * 
	 * @param radius
	 */
	public RadialGeometry(double radius) {
		this.radius = radius;
	}

}
