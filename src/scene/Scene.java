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

	/**
	 * The scene name
	 */
	public String name;
	/**
	 * The background color of the scene.
	 */
	public Color background;

	/**
	 * The ambient light of the scene.
	 */
	public AmbientLight ambientLight;

	/**
	 * The collection of geometries in the scene.
	 */
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
	 * background setter (builder pattern)
	 * 
	 * @param background The new background
	 * @return this scene (builder pattern)
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * ambientLight setter (builder pattern)
	 * 
	 * @param ambientLight  The scene ambition light for set
	 * @return this scene (builder pattern)
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * geometries setter (builder pattern)
	 * 
	 * @param geometries The scene geometries for set
	 * @return this scene (builder pattern)
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}

}
