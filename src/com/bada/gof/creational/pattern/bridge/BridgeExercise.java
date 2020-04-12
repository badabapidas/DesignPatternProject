/**
 * You are given an example of an inheritance hierarchy which results in Cartesian-product duplication.
 *  
 * The actual exercise can be found https://www.udemy.com/course/design-patterns-java/learn/quiz/4350296#questions
 * 
 */
package com.bada.gof.creational.pattern.bridge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BridgeExercise {
	@Test
	public void test() {
		assertEquals("Drawing Square as lines", new Square(new VectorRenderer1()).toString());
	}
}

interface Renderer1 {
	public String whatToRenderAs();
}

abstract class Shape1 {
	private Renderer1 renderer;
	public String name;

	public Shape1(Renderer1 renderer) {
		this.renderer = renderer;
	}

	@Override
	public String toString() {
		return String.format("Drawing %s as %s", name, renderer.whatToRenderAs());
	}
}

class Triangle extends Shape1 {
	public Triangle(Renderer1 renderer) {
		super(renderer);
		name = "Triangle";
	}
}

class Square extends Shape1 {
	public Square(Renderer1 renderer) {
		super(renderer);
		name = "Square";
	}
}

class RasterRenderer1 implements Renderer1 {

	@Override
	public String whatToRenderAs() {
		return "pixels";
	}
}

class VectorRenderer1 implements Renderer1 {
	@Override
	public String whatToRenderAs() {
		return "lines";
	}
}
