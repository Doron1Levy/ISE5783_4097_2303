package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * TODO
 * @author David
 *
 */
public abstract class RayTracerBase {

	protected Scene scene;

	/**
	 * simple constructor set scene
	 * @param scene
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}

	/**
	 * TODO 
	 * @param ray
	 * @return
	 */
	public abstract Color tracRay(Ray ray);
}
