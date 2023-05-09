/**
 * 
 */
package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import primitives.Point;
import primitives.Ray;

/**
 * @author David
 *
 */
public class Geometries implements Intersectable {

	private List<Intersectable> geometList;

	public Geometries() {
		geometList = new LinkedList<Intersectable>();
	}

	public Geometries(Intersectable... geometries) {
		this();
		add(geometries);
	}

	/**
	 * add object to list
	 * 
	 * @param geometries objects to add
	 */
	public void add(Intersectable... geometries) {
		for (Intersectable geo : geometries) {
			this.geometList.add(geo);
		}
	}

	public List<Point> findIntersections(Ray ray) {

		List<Point> intersactionList = new ArrayList<Point>();

		for (Intersectable geometry : this.geometList) {
			List<Point> tempIntersactions = geometry.findIntersections(ray);

			if (geometry.findIntersections(ray) != null) {

				intersactionList.addAll(tempIntersactions);
			}
		}
		return intersactionList.isEmpty() ? null : intersactionList;
	}
}