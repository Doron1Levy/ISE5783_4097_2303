package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * TODO
 * 
 * @author David
 *
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * Constructor use super class constructor
	 * 
	 * @param scene
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
		return;
	}

	@Override
	public Color traceRay(Ray ray) {
		// get all intersection points
		var IntersectionsLst = scene.geometries.findIntersections(ray);

		// no intersection points
		if (IntersectionsLst == null)
			return scene.background;

		// return the color of the point
		return calcColor(ray.findClosestPoint(IntersectionsLst));
	}

	/**
	 * get point in scene and calculate its color
	 * 
	 * @param point the point we want to get the color of
	 * @return the color of the point
	 */
	private Color calcColor(Point point) {
		return scene.ambientLight.setInsensity();
	}

}