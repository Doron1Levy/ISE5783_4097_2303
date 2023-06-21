package unittests.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/** Produce a picture of a sphere lighted by a spot light */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(150, 150).setVPDistance(1000);

		scene.geometries.add( //
				new Sphere(50d, new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(RED)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
		scene.lightSources.add( //
				new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKl(0.0004).setKq(0.0000006));

		camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();
	}

	/** Produce a picture of a sphere lighted by a spot light */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(400d, new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100)) //
						.setMaterial(
								new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(new Double3(0.5, 0, 0))),
				new Sphere(200d, new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(1)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));

		scene.lightSources
				.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
						.setKl(0.00001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Sphere(30d, new Point(60, 50, -50)).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

		scene.lightSources.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();
	}

	/*
	 * @Test public void myShape3() { Camera camera = new Camera(new Point(-300, 0,
	 * 5), new Vector(1, 0, 0), new Vector(0, 0, 1)) .setVPSize(200d,
	 * 200).setVPDistance(1000); ;
	 * 
	 * scene.setAmbientLight(new AmbientLight(new Color(white).reduce(6), new
	 * Double3(0.15)));
	 * 
	 * double angle = 0; double heigh = 0.8;
	 * 
	 * scene.geometries.add( new Plane(new Point(1, 0, -0.5), new Point(0, 1, -0.5),
	 * new Point(0, 0, -0.5)) .setEmission(new Color(black).reduce(1.3))
	 * .setMaterial(new
	 * Material().setKd(0.2).setKs(0.2).setShininess(100).setKt(0).setKr(0.3))
	 * 
	 * 
	 * );
	 * 
	 * scene.geometries.add( new Sphere(3.4, new Point(0, 0, 4)) .setEmission(new
	 * Color(yellow).reduce(10)) .setMaterial(new
	 * Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7))
	 * 
	 * 
	 * ); scene.geometries.add( new Sphere(3.4, new Point(0, 0, 10.2))
	 * .setEmission(new Color(red).reduce(10)) .setMaterial(new
	 * Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7))
	 * 
	 * 
	 * ); scene.geometries.add( new Sphere(3.4, new Point(0, 0, 16.4))
	 * .setEmission(new Color(green).reduce(10)) .setMaterial(new
	 * Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7).setKr(0))
	 * 
	 * 
	 * ); scene.geometries.add( new Sphere(3.4, new Point(0, 0, 16.4 + 6.2))
	 * .setEmission(new Color(yellow).reduce(10)) .setMaterial(new
	 * Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7))
	 * 
	 * 
	 * ); scene.geometries.add( new Sphere(3.4, new Point(0, 0, 16.4 + 2 * 6.2))
	 * .setEmission(new Color(red).reduce(10)) .setMaterial(new
	 * Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7))
	 * 
	 * 
	 * ); scene.geometries.add( new Sphere(3.4, new Point(0, 0, 16.4 + 3 * 6.2))
	 * .setEmission(new Color(green).reduce(10)) .setMaterial(new
	 * Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7).setKr(0))
	 * 
	 * 
	 * );
	 * 
	 * 
	 * scene.geometries.add( new Plane(new Point(10, 1, -1), new Point(10, -1, -1),
	 * new Point(10, 0, 3)) .setEmission(new Color(darkGray).reduce(15))
	 * .setMaterial(new
	 * Material().setKd(0.2).setKs(0.2).setShininess(80).setKt(0).setKr(0)) );
	 * 
	 * 
	 * java.awt.Color[] colors = {new java.awt.Color(255, 0, 0), new
	 * java.awt.Color(255, 255 / 2, 0), new java.awt.Color(255, 255, 0), new
	 * java.awt.Color(0, 255, 0), new java.awt.Color(0, 255, 255 / 2), new
	 * java.awt.Color(0, 255, 255), new java.awt.Color(0, 0, 255), new
	 * java.awt.Color(255 / 2, 0, 255), new java.awt.Color(255, 0, 255)};
	 * 
	 * for (int i = 0; i < 200; ++i) { int colorIndex = i %
	 * colors.length;//generator.nextInt(colors.length);
	 * 
	 * scene.geometries.add( new Sphere(0.5, new Point(4 * Math.cos(angle), 4 *
	 * Math.sin(angle), heigh)) .setEmission(new
	 * Color(colors[colorIndex]).reduce(2.2)) .setMaterial(new
	 * Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.3))
	 * 
	 * 
	 * );
	 * 
	 * //scene.lights.add(new PointLight(new Color(
	 * colors[colorIndex]).reduce(2),new Point(3*Math.cos(angle), 3*Math.sin(angle),
	 * heigh) ));
	 * 
	 * angle += 3.14 / 13;
	 * 
	 * heigh += 0.13;
	 * 
	 * 
	 * }
	 * 
	 * //scene.lights.add(new PointLight(new Color(100,100,150),new Point(0,6,0) ));
	 * scene.lightSources.add(new DirectionalLight(new Color(white).scale(1.6), new
	 * Vector(1, -0.3, 0))); //scene.lights.add(new SpotLight(new
	 * Color(white).scale(40), new Point(-30,0, 5) ,new Vector(1,0,0)) //
	 * .setKc(0.5).setKq(0.1));
	 * 
	 * 
	 * //scene.lights.add(new SpotLight(new Color(white).reduce(2), new
	 * Point(20.43303,-7.37104,13.77329), new Vector(-20.43,7.37,-13.77)));
	 * ImageWriter imageWriter = new ImageWriter("cadur", 1000, 1000);
	 * camera.setImageWriter(imageWriter) // .setRayTracer(new
	 * RayTracerBasic(scene)) // .renderImage() // .writeToImage();
	 * 
	 * }
	 */

	@Test
	public void myShape4() {
		Camera camera = new Camera(new Point(-330, 0, 5), new Vector(1, 0, 0), new Vector(0, 0, 1)).setVPSize(200d, 200)
				.setVPDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255).reduce(6), new Double3(0.15))).lightSources
				.add(new SpotLight(new Color(RED), new Point(-300, 6, 10), new Vector(1, 0, 0)));

		double angle = 0;
		double height = 0;

		scene.geometries.add(new Plane(new Point(-4, 4, 0), new Vector(0, 0, 1))
				.setMaterial(new Material().setKd(0.8).setKs(0.6).setShininess(100).setKt(0.7).setKr(0.5)));

		java.awt.Color[] colors = { YELLOW, RED, ORANGE, BLUE };

		for (int i = 25; i < 200; ++i) {
			int colorIndex = i % colors.length;

			scene.geometries
					.add(new Sphere(0.5, new Point(i / 25.0 * Math.cos(angle), i / 25.0 * Math.sin(angle), height))
							.setEmission(new Color(colors[colorIndex]).reduce(2.2))
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(80).setKt(0.3)));

			angle += Math.PI / 15.0;
			height += 0.15;
		}

		java.awt.Color[] colors2 = { BLUE, GREEN, PINK, BLACK, RED, GRAY };

		height = 10;
		for (int i = 25; i < 100; ++i) {
			int colorIndex = i % colors2.length;

			scene.geometries.add(new Sphere(0.3, new Point(i / 25.0 * Math.cos(angle), i * Math.sin(angle), height))
					.setEmission(new Color(colors2[colorIndex]).reduce(2.2))
					.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(80).setKt(0.3)));

			angle += Math.PI / 30.0;
			height += 0.5;
		}

		height = 10;
		for (int i = 25; i < 300; ++i) {

			scene.geometries.add(new Sphere(0.05, new Point(i / 25.0 * Math.cos(angle), i * Math.sin(angle), height))
					.setEmission(new Color(WHITE))
					.setMaterial(new Material().setKd(1).setKs(1).setShininess(100).setKt(1)));

			angle += Math.PI / 60.0;
			height += 0.1 % 50;
		}

		scene.lightSources
				.add(new SpotLight(new Color(255, 255, 255).reduce(2), new Point(-150, 0, 5), new Vector(1, 0, 0)));

		scene.lightSources.add(new SpotLight(new Color(GREEN).reduce(2), new Point(50, 0, 5), new Vector(1, 0, 0)));

		scene.setBackground(new Color(BLUE).reduce(TRANSLUCENT));

		ImageWriter imageWriter = new ImageWriter("myShape4", 500, 500);
		camera.setImageWriter(imageWriter).setRayTracer(new RayTracerBasic(scene)).renderImage().writeToImage();
	}

	@Test
	public void myShape5() {
		Camera camera = new Camera(new Point(-350, 0, 5), new Vector(1, 0, 0), new Vector(0, 0, 1)).setVPSize(200d, 200)
				.setVPDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255).reduce(6), new Double3(0.15)));

		double angle = 0;
		double height = 0;

		scene.geometries.add(new Plane(new Point(-4, 4, 0), new Vector(0, 0, 1))
				.setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(80).setKt(0.6).setKr(0)));

		java.awt.Color[] colors = { new java.awt.Color(255, 0, 0), new java.awt.Color(255, 255 / 2, 0),
				new java.awt.Color(255, 255, 0), new java.awt.Color(0, 255, 0), new java.awt.Color(0, 255, 255 / 2),
				new java.awt.Color(0, 255, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(255 / 2, 0, 255),
				new java.awt.Color(255, 0, 255) };

		for (int i = 0; i < 200; ++i) {
			int colorIndex = i % colors.length;

			scene.geometries
					.add(new Sphere(0.5, new Point(i / 25.0 * Math.cos(angle), i / 25.0 * Math.sin(angle), height))
							.setEmission(new Color(colors[colorIndex]).reduce(2.2))
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(80).setKt(0.3)));

			angle += Math.PI / 15.0;
			height += 0.15;
		}

		scene.lightSources
				.add(new SpotLight(new Color(255, 255, 255).reduce(2), new Point(-150, 0, 5), new Vector(1, 0, 0)));

		scene.setBackground(new Color(128, 128, 128));

		ImageWriter imageWriter = new ImageWriter("myShape5", 500, 500);
		camera.setImageWriter(imageWriter).setRayTracer(new RayTracerBasic(scene)).renderImage().writeToImage();
	}

}
