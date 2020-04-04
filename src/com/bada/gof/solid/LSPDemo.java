/**
 * Liskov Substitution Principle: Substitutability is a principle in object-oriented programming stating 
 * that, in a computer program, if S is a subtype of T, then objects of type T may be replaced with objects of type S. 
 * All the subclass should be able to use any methods which is available in the base class
 */

package com.bada.gof.solid;

/**
 * Base class defining some properties and methods
 * 
 * @author bada
 *
 */
class Rectangle {
	protected int width, height;

	public Rectangle() {
	}

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getArea() {
		return width * height;
	}

	@Override
	public String toString() {
		return "Rectangle{" + "width=" + width + ", height=" + height + '}';
	}

	public boolean isSquare() {
		return width == height;
	}
}

/**
 * Subclass but this does not make any sense using setWidth() or setHeight()
 * separately as this is a square. So this breaks LSP
 * 
 * @author bada
 *
 */
class Square extends Rectangle {
	public Square() {
	}

	public Square(int size) {
		width = height = size;
	}

	@Override
	public void setWidth(int width) {
		super.setWidth(width);
		super.setHeight(width);
	}

	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		super.setWidth(height);
	}
}

/**
 * Solution can be a factory class which is responsible to give user the actual
 * instance
 * 
 * @author bada
 *
 */
class RectangleFactory {
	public static Rectangle newSquare(int side) {
		return new Rectangle(side, side);
	}

	public static Rectangle newRectangle(int width, int height) {
		return new Rectangle(width, height);
	}
}

class LSPDemo {
	// maybe conform to ++
	static void useIt(Rectangle r) {
		int width = r.getWidth();
		r.setHeight(10);
		System.out.println("Expected area of " + (width * 10) + ", got " + r.getArea());
	}

	public static void main(String[] args) {
		// old approach
		Rectangle rc = new Rectangle(2, 3);
		useIt(rc);

		Rectangle sq = new Square(10);
		sq.setWidth(5);
		useIt(sq);

		// new approach
		Rectangle newRectangle = RectangleFactory.newRectangle(2, 3);
		useIt(newRectangle);

		Rectangle newSquare = RectangleFactory.newSquare(10);
		useIt(newSquare);
	}
}