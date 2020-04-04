/**
 * Factory pattern example to create any instance; Below you can see in how many ways we can create a factory instance
 */
package com.bada.gof.creational.pattern.factory;

enum CoordinateSystem {
	CARTESIAN, POLAR
}

class Point {
	private double x, y;

	// in case of inner factory class this constructor can be marked as private as
	// use restriction
	protected Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point(double a, double b, // names do not communicate intent
			CoordinateSystem cs) {
		switch (cs) {
		case CARTESIAN:
			this.x = a;
			this.y = b;
			break;
		case POLAR:
			this.x = a * Math.cos(b);
			this.y = a * Math.sin(b);
			break;
		}
	}

	// singleton field
	public static final Point ORIGIN = new Point(0, 0);

	// factory method as a static method
	public static Point newCartesianPoint(double x, double y) {
		return new Point(x, y);
	}

	// factory method as a static method
	public static Point newPolarPoint(double rho, double theta) {
		return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
	}

	// Factory method as an inner static class
	public static class Factory {
		public static Point newCartesianPoint(double x, double y) {
			return new Point(x, y);
		}
	}
}

/**
 * Factory method as an external class; in this case the constructor has to be
 * protected or public or else it can not be access from the external factory
 * class
 **/
class PointFactory {
	public static Point newCartesianPoint(double x, double y) {
		return new Point(x, y);
	}
}

/**
 * Below you can see in how many ways we can create a factory instance
 * 
 * @author bada
 *
 */
class FactoryDemo {
	public static void main(String[] args) {
		// this wont be visible from outside and this will force user to use the factory
		// method
		Point point = new Point(2, 3, CoordinateSystem.CARTESIAN);

		// singleton access
		Point origin = Point.ORIGIN;

		// Inner factory class
		Point innerFactoryClass = Point.Factory.newCartesianPoint(1, 2);

		// External factory class
		Point externalFactoryClass = PointFactory.newCartesianPoint(1, 2);

	}
}