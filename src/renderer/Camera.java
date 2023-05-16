package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * implement the camera model
 * 
 * @author David
 *
 */
public class Camera {

	// camera location
	private Point location;

	// camera direction
	private Vector up;
	private Vector right;
	private Vector to;

	// view plane size
	double vpHigh;
	double vpWidth;
	double vpHeight;

	// distance of the view plan from the camera
	private double distance;

	/**
	 * A simple constructor that checks whether the vectors to and up are
	 * perpendicular to each other and produces the vector right. Throws an
	 * exception if the vectors are not perpendicular. keeps them normal.
	 * 
	 * @param location location of camera
	 * @param up       height direction of the camera
	 * @param to       direction of camera
	 */
	public Camera(Point location, Vector to, Vector up) {

		// check if the up vector and to vector are orthogonal each other
		if (!Util.isZero(up.dotProduct(to)))
			throw new IllegalArgumentException("the vector are not orthogonal to each other");

		this.location = location;
		this.up = up.normalize();
		this.to = to.normalize();

		// calculate the right vector
		this.right = to.crossProduct(up).normalize();

	}

	/******************** setters *********************/

	/**
	 * set width and height of the view plane return this camera
	 *
	 * @param vpWidth
	 * @param vpHeight
	 * @return this
	 */
	public Camera setVPSize(double vpWidth, double vpHeight) {

		if (vpHeight <= 0 || vpWidth <= 0)
			throw new IllegalArgumentException("ERROR negativ argument");

		this.vpWidth = vpWidth;
		this.vpHeight = vpHeight;
		return this;
	}

	/**
	 * set distance the camera from view plane
	 * 
	 * @param distance view plane destination from camera
	 * @return this
	 */
	public Camera setVPDistance(double distance) {
		if (distance <= 0)
			throw new IllegalArgumentException("ERROR negativ argument");
		this.distance = distance;
		return this;
	}

	/******************** getters ***************/

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @return the up
	 */
	public Vector getUp() {
		return up;
	}

	/**
	 * @return the right
	 */
	public Vector getRight() {
		return right;
	}

	/**
	 * @return the to
	 */
	public Vector getTo() {
		return to;
	}

	/**
	 * @return the high
	 */
	public double getVpHigh() {
		return vpHigh;
	}

	/**
	 * @return the width
	 */
	public double getVpWidth() {
		return vpWidth;
	}

	/**
	 * @return the distance
	 */
	public double getVpHeight() {
		return vpHeight;
	}

	// ***************** Operations ******************** //

	/**
	 * get the size of view plan by pixels and specific index on vp construct ray
	 * through this pixel
	 * 
	 * @param nX X size of view plan
	 * @param nY Y size of view plan
	 * @param j  X coordinate
	 * @param i  Y coordinate
	 * @return ray through the pixel
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {

		// calculate the meeting point of the object in the vector in front of the
		// camera
		Point pixelPoint = location.add(to.scale(distance));

		// calculate the high of pixel
		double y = vpHeight / nY;
		// calculate the width of pixel
		double x = vpWidth / nX;

		// calculate the distance from to the pixel
		double XJ = x * (j - ((nX - 1) / 2.0));
		double YI = -y * (i - ((nX - 1) / 2.0));
		// calculate the current pixel location
		if (!Util.isZero(XJ))
			pixelPoint = pixelPoint.add(right.scale(XJ));
		if (!Util.isZero(YI))
			pixelPoint = pixelPoint.add(up.scale(YI));

		return new Ray(location, pixelPoint.subtract(location));

	}

}
