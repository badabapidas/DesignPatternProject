package com.bada.gof.creational.pattern.prototype;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

public class PrototypeExercise {
	public static void main(String[] args) {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(4, 5);
		Line l1 = new Line(p1, p2);
		Line l2 = l1.deepCopy();
		l2.start.x = 10;
		System.out.println(l1);
		System.out.println(l2);
	}
}

class Point implements Serializable {
	public int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}

class Line implements Serializable {
	public Point start, end;

	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	public Line deepCopy() {
		return (Line) SerializationUtils.deserialize(SerializationUtils.serialize(this));
	}

	public Line anotherDeepCopy() {
		Point newStart = new Point(start.x, start.y);
		Point newEnd = new Point(end.x, end.y);
		return new Line(newStart, newEnd);
	}

	@Override
	public String toString() {
		return "Line [start=" + start + ", end=" + end + "]";
	}

}
