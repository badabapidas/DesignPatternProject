/**
 * Adapter pattern with cache; we have used hashing and equals method to verify if the lines and points are
 * already present in the cache
 * 
 */
package com.bada.gof.structural.pattern.adapter;

import java.util.*;
import java.util.function.Consumer;

class Point1 {
	public int x, y;

	public Point1(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Point1 point = (Point1) o;

		if (x != point.x)
			return false;
		return y == point.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}

	@Override
	public String toString() {
		return "Point1{" + "first=" + x + ", second=" + y + '}';
	}
}

class Line1 {
	public Point1 start, end;

	public Line1(Point1 start, Point1 end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Line1 line = (Line1) o;

		if (!start.equals(line.start))
			return false;
		return end.equals(line.end);
	}

	@Override
	public int hashCode() {
		int result = start.hashCode();
		result = 31 * result + end.hashCode();
		return result;
	}
}

class VectorObject1 extends ArrayList<Line1> {
}

class VectorRectangle1 extends VectorObject1 {
	public VectorRectangle1(int x, int y, int width, int height) {
		add(new Line1(new Point1(x, y), new Point1(x + width, y)));
		add(new Line1(new Point1(x + width, y), new Point1(x + width, y + height)));
		add(new Line1(new Point1(x, y), new Point1(x, y + height)));
		add(new Line1(new Point1(x, y + height), new Point1(x + width, y + height)));
	}
}

/**
 * This adapter is implementing Iterable so that the foreach impl will take the
 * values from the cache as we are proving the impl
 * 
 * @author bada
 *
 */
class LineToPointAdapter1 implements Iterable<Point1> {
	private static int count = 0;
	private static Map<Integer, List<Point1>> cache = new HashMap<>(); // cache
	private int hash;

	public LineToPointAdapter1(Line1 line) {
		hash = line.hashCode();
		if (cache.get(hash) != null)
			return; // we already have it

		System.out.println(String.format("%d: Generating points for line [%d,%d]-[%d,%d] (with caching)", ++count,
				line.start.x, line.start.y, line.end.x, line.end.y));

		ArrayList<Point1> points = new ArrayList<>();

		int left = Math.min(line.start.x, line.end.x);
		int right = Math.max(line.start.x, line.end.x);
		int top = Math.min(line.start.y, line.end.y);
		int bottom = Math.max(line.start.y, line.end.y);
		int dx = right - left;
		int dy = line.end.y - line.start.y;

		if (dx == 0) {
			for (int y = top; y <= bottom; ++y) {
				points.add(new Point1(left, y));
			}
		} else if (dy == 0) {
			for (int x = left; x <= right; ++x) {
				points.add(new Point1(x, top));
			}
		}

		cache.put(hash, points); // add into the cache
	}

	@Override
	public Iterator<Point1> iterator() {
		return cache.get(hash).iterator();
	}

	@Override
	public void forEach(Consumer<? super Point1> action) {
		cache.get(hash).forEach(action);
	}

	@Override
	public Spliterator<Point1> spliterator() {
		return cache.get(hash).spliterator();
	}
}

class AdapterDemoWitCaching {
	private static final List<VectorObject1> vectorObjects = new ArrayList<>(
			Arrays.asList(new VectorRectangle1(1, 1, 10, 10), new VectorRectangle1(3, 3, 6, 6)));

	public static void drawPoint(Point1 p) {
		System.out.print(".");
	}

	private static void draw() {
		for (VectorObject1 vo : vectorObjects) {
			for (Line1 line : vo) {
				LineToPointAdapter1 adapter = new LineToPointAdapter1(line);
				adapter.forEach(point -> drawPoint(point));
			}
		}
	}

	public static void main(String[] args) {
		draw();
		draw();
	}
}