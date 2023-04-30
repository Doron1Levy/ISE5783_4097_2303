package geometries;

import java.util.List;
import primitives.Point;
import primitives.Ray;

/**
 * interface of Intersectable by ray
 */
public interface Intersectable {

	/**
	 * get ray for intersect
	 * 
	 * @param ray
	 * @return al
	 */
	List<Point> findIntsersections(Ray ray);

}
