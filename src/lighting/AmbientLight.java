/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Double3;
import scene.Scene;

/**
 * the class implements ambient lighting in the image
 * 
 * @author David
 *
 */
public class AmbientLight {

	Color intensity;
	public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

	// ******************* constructors *****************

	/**
	 * constructor calculate the intensity (Double3 object)
	 * 
	 * @param light light intensity according to the RGB component
	 * @param Ka    attenuation coefficient of the light on the surface
	 */
	public AmbientLight(Color light, Double3 Ka) {
		this.intensity = light.scale(Ka);
	}

	/**
	 * constructor calculate the intensity (java.double object)
	 * 
	 * @param light light intensity according to the RGB component
	 * @param Ka    attenuation coefficient of the light on the surface
	 */
	public AmbientLight(Color light, double Ka) {
		this.intensity = light.scale(Ka);
	}

	/**
	 * setter for intensity
	 * 
	 * @return this intensity
	 */
	public Color setInsensity() {
		return this.intensity;
	}
}