package geometries;

import java.util.List;
import primitives.Point;
import primitives.Ray;

/**
 * interface of Intersectable by ray
 */
public interface Intersectable {

	/**
	 * Find intersections between the geometry object and a ray
	 * 
	 * @param ray the ray to intersect
	 * @return the intersections' list
	 */
	List<Point> findIntersections(Ray ray);

}
