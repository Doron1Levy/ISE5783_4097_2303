package geometries;

import primitives.*;

import java.util.Objects;

public class Cylinder extends Tube{

    final double height;

    /**
     * constructor
     * @param height
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    /**
     * simple getter of filed "height"
     * @return high of the cylinder
     */
    public double getHeight() {
        return height;
    }

    /**
     *calculate the cylinder normal
     * @param point
     * @return The normal of the cylinder at the point sent
     */
    @Override
    public Vector getNormal(Point point) {
        //the point in the other base
        Point baseHead = this.axisRay.getPoint().add(this.axisRay.getVector().scale(this.height));
        //checking if the point is on the base, if not it returns tube's normal. and if does
        //it returns the same vector like the vector of ray.
        //ofcourse we assume that the point on the cylinder.
        if (point.subtract(this.axisRay.getPoint()).dotProduct(this.axisRay.getVector()) != 0 ||
                point.subtract(baseHead).dotProduct(this.axisRay.getVector()) != 0) {
            return super.getNormal(point);
        }
        else {
            return this.axisRay.getVector();
        }

    }

    /**
     *check if 2 arguments are equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this == null) return false;
        if (!(o instanceof Cylinder)) return false;
        Cylinder cylinder = (Cylinder) o;
        if(this.height == cylinder.height)
            return super.equals(o);
        return false;
    }

    /**
     * print the cylinder object
     * @return
     */
    @Override
    public String toString() {
        return "Cylinder{" +
                super.toString() +
                "height=" + height +
                '}';
    }
}