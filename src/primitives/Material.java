package primitives;

/**
 * The Material class represents the material properties of an object in a
 * scene. It includes the diffuse reflection coefficient (kD), the specular
 * reflection coefficient (kS), and the shininess (nShininess) of the material.
 */
public class Material {

	/**
	 * The diffuse reflection coefficient.
	 */
	public Double3 kD = Double3.ZERO;

	/**
	 * The specular reflection coefficient.
	 */
	public Double3 kS = Double3.ZERO;

	/**
	 * refracted index
	 */
	public Double3 kt = Double3.ZERO;

	/**
	 * reflected index
	 */
	public Double3 kr = Double3.ZERO;

	/**
	 * The shininess of the material.
	 */
	public int nShininess = 1;

	/**
	 * refracted index
	 */
	public Double3 krDouble3 = Double3.ZERO;

	// blur glass
	// all parameters for the beam
	public int numOfRays = 1;
	public double blurGlassDistance = 1, blurGlassRadius = 1;

	// ***************** setters builder pattern ********************** //

	/**
	 * Sets the diffuse reflection coefficient (kD) to the specified value.
	 *
	 * @param kD The value of the diffuse reflection coefficient.
	 * @return This Material object.
	 */
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}

	/**
	 * Sets the specular reflection coefficient (kS) to the specified value.
	 *
	 * @param kS The value of the specular reflection coefficient.
	 * @return This Material object.
	 */
	public Material setKs(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * Sets the diffuse reflection coefficient (kD) to the specified Double3 object.
	 *
	 * @param kD The Double3 object representing the diffuse reflection coefficient.
	 * @return This Material object.
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Sets the transmission coefficient (kt) of the material.
	 *
	 * @param kt The transmission coefficient to set.
	 * @return The updated Material object.
	 */
	public Material setKt(Double3 kt) {
		this.kt = kt;
		return this;
	}

	/**
	 * Sets the specular reflection coefficient (kS) to the specified Double3
	 * object.
	 *
	 * @param kS The Double3 object representing the specular reflection
	 *           coefficient.
	 * @return This Material object.
	 */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * Sets the shininess of the material.
	 *
	 * @param nShininess The shininess value.
	 * @return This Material object.
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}

	public Material setNumOfRays(int numOfRays) {
		if (numOfRays < 1)
			throw new IllegalArgumentException("illegal argument in setNumOfRay ");
		this.numOfRays = numOfRays;
		return this;
	}

	public Material setBlurGlassDistance(double blurGlassDistance) {
		if (blurGlassDistance <= 0)
			throw new IllegalArgumentException("illegal argument in setBlurGlassDistance ");
		this.blurGlassDistance = blurGlassDistance;
		return this;
	}

	public Material setBlurGlassRadius(double blurGlassRadius) {
		if (blurGlassRadius <= 0)
			throw new IllegalArgumentException("illegal argument in setBlurGlassRadius ");
		this.blurGlassRadius = blurGlassRadius;
		return this;
	}

	public Material setBlurGlass(int numOfRays, double distance, double radius) {

		if (numOfRays < 1 || distance <= 0 || radius <= 0)
			throw new IllegalArgumentException("illegal argument in setBlurGlass ");

		this.numOfRays = numOfRays;
		this.blurGlassDistance = distance;
		this.blurGlassRadius = radius;

		return this;
	}
}
