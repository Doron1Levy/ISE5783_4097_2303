package primitives;

/**
 * Ray class represents 3D ray
 */
public class Ray {

	final Point point;
	final Vector vector;

	/**
	 * simple constructor
	 * 
	 * @param start point
	 * @param direction vector
	 */
	public Ray(Point point, Vector vector) {
		this.point = point;
		this.vector = vector.normalize();
	}

	/**
	 * getter
	 * 
	 * @return p0
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * getter 
	 * 
	 * @return direction vector
	 */
	public Vector getVector() {
		return vector;
	}

	@Override
	public String toString() {
		return point.toString() + vector.toString(); // is that correct?
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Ray ray) && point.equals(ray.point) && vector.equals(ray.vector);
	}
}