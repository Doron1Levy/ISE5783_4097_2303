package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.Ray;

/**
 * The department implements operations for several geometric bodies
 * 
 * @author Doron and Aharon
 *
 */
public class Geometries extends Intersectable {

	private List<Intersectable> geometList = new LinkedList<>();

	/**
	 * The default constructor (the list will be empty)
	 */
	public Geometries() {
	}

	/**
	 * a constructor that receives a list of geometric objects and adds them to the
	 * list
	 * 
	 * @param geometries geometries Intersectable
	 */
	public Geometries(Intersectable... geometries) {
		if (geometries != null)
			add(geometries);
	}

	/**
	 * add object to list
	 * 
	 * @param geometries objects to add
	 */
	public void add(Intersectable... geometries) {
		this.geometList.addAll(List.of(geometries));
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double distance) {
		List<GeoPoint> intersectionList = null;

		// find all intersection between all geometries list and ray
		for (Intersectable geometry : this.geometList) {
			var tempIntersections = geometry.findGeoIntersections(ray, distance);

			if (tempIntersections != null) {
				if (intersectionList == null)
					intersectionList = new LinkedList<>(tempIntersections);
				else
					intersectionList.addAll(tempIntersections);
			}
		}
		return intersectionList;
	}
}