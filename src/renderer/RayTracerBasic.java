package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import static primitives.Util.*;

import java.util.List;

/**
 * The RayTracerBasic class implements a basic ray tracing algorithm. It traces
 * rays through a scene and calculates the color of each ray intersection point.
 */
public class RayTracerBasic extends RayTracerBase {

	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;

	/**
	 * Constructor for RayTracerBasic class.
	 *
	 * @param scene The scene to trace rays in.
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		// Get all intersection points
		var intersectionsLst = ray
				.findClosestGeoPoint(scene.geometries.findGeoIntersectionsHelper(ray, Double.POSITIVE_INFINITY));

		// No intersection points
		return intersectionsLst == null ? scene.background
				// Return the color of the closest intersection point
				: calcColor(intersectionsLst, ray);
	}

	/**
	 * get point in scene and calculate its color
	 * 
	 * @param geoPoint
	 * @return
	 */
	private Color calcColor(GeoPoint geoPoint, Ray ray) {

		return scene.ambientLight.getIntensity().add(calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, new Double3(1)));
	}

	/**
	 * get point in scene and calculate its color
	 * 
	 * @param gp
	 * @param ray
	 * @param level
	 * @param k
	 * @return
	 */
	private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
		Color color = calcLocalEffects(gp, ray, k).add(gp.geometry.getEmission());

		return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
	}

	/**
	 * Calculate global effect reflected and refracted
	 * 
	 * @param gp
	 * @param inRay
	 * @param level
	 * @param k
	 * @return
	 */
	private Color calcGlobalEffects(GeoPoint gp, Ray inRay, int level, Double3 k) {
		Color color = Color.BLACK;
		Vector n = gp.geometry.getNormal(gp.point);

		Material gpMaterial = gp.geometry.getMaterial();
		Double3 kr = gpMaterial.kr, kkr = kr.product(k);

		if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
			Ray reflectedRay = constructReflectedRay(gp.point, inRay, n);

			var rays = reflectedRay.generateBeam(n, gpMaterial.blurGlassRadius, gpMaterial.blurGlassDistance,
					gpMaterial.numOfRays);
			color = color.add(calcAverageColor(rays, level - 1, kkr).scale(kr));
		}

		Double3 kt = gpMaterial.kt, kkt = kt.product(k);
		if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
			Ray refractedRay = constructRefractedRay(gp.point, inRay, n);

			var rays = refractedRay.generateBeam(n, gpMaterial.blurGlassRadius, gpMaterial.blurGlassDistance,
					gpMaterial.numOfRays);
			color = color.add(calcAverageColor(rays, level - 1, kkt).scale(kt));
		}
		return color;
	}

	/**
	 * Calculate the effect of different light sources on a point in the scene
	 * according to the Phong model.
	 *
	 * @param gp  Point on geometry in the scene.
	 * @param ray Ray from the camera to the `gp`.
	 * @param k   The transparency coefficient.
	 * @return The color contribution from the local light effects.
	 */
	private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
		Vector v = ray.getDirection();
		Vector n = gp.geometry.getNormal(gp.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;

		int nShininess = gp.geometry.getMaterial().nShininess;
		Double3 kd = gp.geometry.getMaterial().kD;
		Double3 ks = gp.geometry.getMaterial().kS;

		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lightSources) {
			Vector l = lightSource.getL(gp.point);
			double nl = alignZero(n.dotProduct(l));

			if (nl * nv > 0) { // sign(nl) == sign(nv)
				Double3 ktr = transparency(gp, lightSource, l, n);
				if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
					if (unshaded(gp, lightSource, l, n)) {
						Color lightIntensity = lightSource.getIntensity(gp.point).scale(ktr);
						color = color.add(calcDiffuse(kd, nl, lightIntensity),
								calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
					}
				}
			}
		}

		return color;
	}

	/**
	 * Calculates the diffuse component of light reflection.
	 *
	 * @param kd             The diffuse reflection coefficient.
	 * @param nl             The dot product between the normal vector and the light
	 *                       vector.
	 * @param lightIntensity The intensity of the light source.
	 * @return The color contribution from the diffuse reflection.
	 */
	private Color calcDiffuse(Double3 kd, double nl, Color lightIntensity) {
		return lightIntensity.scale(kd.scale(nl < 0 ? -nl : nl));
	}

	/**
	 * Calculates the specular component of light reflection.
	 *
	 * @param ks             The specular reflection coefficient.
	 * @param l              The light vector.
	 * @param n              The normal vector.
	 * @param nl             The dot product between the normal vector and the light
	 *                       vector.
	 * @param v              The view vector.
	 * @param nShininess     The shininess coefficient.
	 * @param lightIntensity The intensity of the light source.
	 * @return The color contribution from the specular reflection.
	 */
	private Color calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v, int nShininess,
			Color lightIntensity) {
		Vector r = l.add(n.scale(-2 * nl)); // nl must not be zero!
		double minusVR = -alignZero(r.dotProduct(v));
		return minusVR <= 0 ? Color.BLACK : lightIntensity.scale(ks.scale(Math.pow(minusVR, nShininess)));
	}

	/**
	 * Checks if a given point is unshaded by a light source.
	 *
	 * @param geoPoint The geometric point to be checked.
	 * @param light    The light source.
	 * @param toLight  The vector representing the direction from the point to the
	 *                 light source.
	 * @param normal   The surface normal at the point.
	 * @return {@code true} if the point is unshaded by the light source,
	 *         {@code false} otherwise.
	 */
	private boolean unshaded(GeoPoint geoPoint, LightSource light, Vector toLight, Vector normal) {
		Vector lightDirection = toLight.scale(-1); // from point to light source

		Ray lightRay = new Ray(geoPoint.point, lightDirection, normal);

		List<GeoPoint> intersections = scene.geometries.findGeoIntersectionsHelper(lightRay,
				light.getDistance(geoPoint.point));
		if (intersections == null) {
			return true;
		} else {
			for (GeoPoint g : intersections) {
				if (g.geometry.getMaterial().kt == Double3.ZERO) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * get light and gp and move ao all the objects between them and calculate the
	 * transparency
	 * 
	 * @param gp
	 * @param light
	 * @param l
	 * @param n
	 * @return
	 */
	private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {

		Vector lightDirection = l.scale(-1); // from point to light source

		Ray lightRay = new Ray(gp.point, lightDirection, n);

		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));
		Double3 ktr = new Double3(1d);
		if (intersections == null)
			return ktr;

		for (GeoPoint p : intersections)
			ktr = ktr.product(p.geometry.getMaterial().kt);
		return ktr;
	}

	/**
	 * Calculate refracted ray
	 * 
	 * @param pointGeo
	 * @param inRay
	 * @param n
	 * @return
	 */
	private Ray constructRefractedRay(Point pointGeo, Ray inRay, Vector n) {
		return new Ray(pointGeo, inRay.getDirection(), n);
	}

	/**
	 * Calculate Reflected ray
	 * 
	 * @param pointGeo
	 * @param inRay
	 * @param n
	 * @return
	 */
	private Ray constructReflectedRay(Point pointGeo, Ray inRay, Vector n) {
		// 𝒓=𝒗 −𝟐∙(𝒗∙𝒏)∙𝒏
		Vector v = inRay.getDirection();
		double vn = v.dotProduct(n);

		if (vn == 0) {
			return null;
		}

		Vector r = v.subtract(n.scale(2 * vn));
		return new Ray(pointGeo, r, n);
	}

	/**
	 * get ray and return the closet intersection geoPoint
	 * 
	 * @param ray
	 * @return
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
	}

	/**
	 * get list of ray
	 * 
	 * @param rays
	 * @param level
	 * @param kkt
	 * @return average color of the intersection of the rays
	 */
	Color calcAverageColor(List<Ray> rays, int level, Double3 kkt) {
		Color color = Color.BLACK;

		for (Ray ray : rays) {

			GeoPoint intersction = findClosestIntersection(ray);

			if (intersction != null)
				color = color.add(calcColor(intersction, ray, level - 1, kkt));
		}
		return color.reduce(rays.size());
	}

}
