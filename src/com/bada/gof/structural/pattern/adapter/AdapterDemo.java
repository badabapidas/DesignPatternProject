/**
 * Adapter Design patter example;
 * 
 * Getting the interface you want from the interface you have.
 * 
 * In this example we have created a Point classes. Then Line Class. We have created VectorObject which is nothing but bunch of lines
 * Then we have created VectorRectangle which contains four lines to represent a rectangle(which extends VectorObjects). 
 * 
 * In the main class now we are creating 2 vector objects mean 2 vector rectangles and we have an api to draw a point but there 
 * is no way we can draw a vector object into a point.
 * 
 * So we have created an adapter class LineToPointAdapter which will help us derive points from a line so that we can reuse
 * draw point api.
 * 
 * No in this approach we do have one problem. If we run the draw method twice you can see it will generate points for 16 lines which 
 * is wrong as the given rectangle is still 2.
 * 
 */
package com.bada.gof.structural.pattern.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Point {
	public int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point{" + "first=" + x + ", second=" + y + '}';
	}
}

class Line {
	public Point start, end;

	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
}

class VectorObject extends ArrayList<Line> {
}

class VectorRectangle extends VectorObject {
	public VectorRectangle(int x, int y, int width, int height) {
		add(new Line(new Point(x, y), new Point(x + width, y)));
		add(new Line(new Point(x + width, y), new Point(x + width, y + height)));
		add(new Line(new Point(x, y), new Point(x, y + height)));
		add(new Line(new Point(x, y + height), new Point(x + width, y + height)));
	}
}

/**
 * Adapter class which will derive two points from a given line.
 * 
 * @author bada
 *
 */
class LineToPointAdapter extends ArrayList<Point> {
	private static int count = 0;

	public LineToPointAdapter(Line line) {
		System.out.print(String.format("%s%d: Generating points for line [%d,%d]-[%d,%d] (no caching)", "\n",++count,
				line.start.x, line.start.y, line.end.x, line.end.y));

		int left = Math.min(line.start.x, line.end.x);
		int right = Math.max(line.start.x, line.end.x);
		int top = Math.min(line.start.y, line.end.y);
		int bottom = Math.max(line.start.y, line.end.y);
		int dx = right - left;
		int dy = line.end.y - line.start.y;

		if (dx == 0) {
			for (int y = top; y <= bottom; ++y) {
				add(new Point(left, y));
			}
		} else if (dy == 0) {
			for (int x = left; x <= right; ++x) {
				add(new Point(x, top));
			}
		}
	}
}

class AdapterDemo {
	private static final List<VectorObject> vectorObjects = new ArrayList<>(
			Arrays.asList(new VectorRectangle(1, 1, 10, 10), new VectorRectangle(3, 3, 6, 6)));

	public static void drawPoint(Point p) {
		System.out.print(".");
	}

	/**
	 * This method will take all the vector objects, which are bunch of lines then
	 * using the adapter class we converts all the lines to a different points and
	 * using drawPoint we will printing all the points
	 */
	private static void draw() {
		for (VectorObject vo : vectorObjects) {
			for (Line line : vo) {
				LineToPointAdapter adapter = new LineToPointAdapter(line);
				adapter.forEach(AdapterDemo::drawPoint);
			}
		}
	}

	public static void main(String[] args) {
		draw();
		draw(); // here is the problem
	}
}