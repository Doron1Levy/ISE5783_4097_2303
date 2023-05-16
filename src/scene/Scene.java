package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * The class implements a model of a visual scene The class is a PDS (Plain Data
 * Structure)
 * 
 * @author David
 *
 */
public class Scene {
	public String name;
	public Color background;
	public AmbientLight ambientLight;
	public Geometries geometries;

	/**
	 * simple constructor enter the name end for others enters default values
	 * 
	 * @param name the scene name
	 */
	public Scene(String name) {
		this.name = name;
		this.background = Color.BLACK;
		this.ambientLight = AmbientLight.NONE;
		this.geometries = new Geometries();
	}

	/******************** setters (builder pattern) *******************/

	/**
	 * background setter
	 * 
	 * @param background
	 * @return this background object that we set now
	 */
	public Color setBackground(Color background) {
		this.background = background;
		return this.background;
	}

	/**
	 * ambientLight setter
	 * 
	 * @param ambientLight
	 * @return this ambientLight object that we set now
	 */
	public AmbientLight setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this.ambientLight;
	}

	/**
	 * geometries setter
	 * 
	 * @param geometries
	 * @return this geometries object that we set now
	 */
	public Geometries setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this.geometries;
	}

}
