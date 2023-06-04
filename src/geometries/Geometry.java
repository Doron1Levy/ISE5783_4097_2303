package geometries;

import primitives.*;

/**
 * this class contains list of geometries
 */
public abstract class Geometry extends Intersectable {

	/**
	 * emission of the geometry
	 */
	protected Color emission = Color.BLACK;

	// ***************** Getters ********************** //

	/**
	 * Returns the emission.
	 * 
	 * @return emission emission
	 */
	public Color getEmission() {
		return emission;
	}

	// ***************** Setters (builder pattern) ********************** //

	/**
	 * setter for emission
	 * 
	 * @param emission new value for emission object
	 * @return this Geometry (builder pattern)
	 */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}

	/**
	 * calculate the object normal vector at a point on the surfaces of the
	 * geometric body
	 * 
	 * @param point at the surface of the body
	 * @return normal vector
	 */
	public abstract Vector getNormal(Point point);

}