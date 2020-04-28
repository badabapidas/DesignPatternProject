/**
 * Facade design pattern example
 * 
 * Exposing several components through a single interface. Provides a simple, easy to understand/user interface over a large 
 * and sophisticated body of code. 
 * 
 * If you go through the code, with out facade pattern user might have to deal with all the low level details like creating a buffer
 * then viewport and then create a console and add every view port in that console followed by render.
 * 
 * A user might not interested to go through all the unwanted details until he needs more control on everything. So with facade pattern 
 * we hide all those low level impl and give user a simple api which they can use to done their job.
 * 
 */
package com.bada.gof.creational.pattern.facade;

import java.util.ArrayList;
import java.util.List;

class Buffer {
	private char[] characters;
	private int lineWidth;

	public Buffer(int lineHeight, int lineWidth) {
		this.lineWidth = lineHeight;
		characters = new char[lineWidth * lineHeight];
	}

	public char charAt(int x, int y) {
		return characters[y * lineWidth + x];
	}
}

class ViewPort {
	private final Buffer buffer;
	private final int width;
	private final int height;
	private final int offsetX;
	private final int offsetY;

	public ViewPort(Buffer buffer, int width, int height, int offsetX, int offsetY) {
		this.buffer = buffer;
		this.width = width;
		this.height = height;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public char charAt(int x, int y) {
		return buffer.charAt(x + offsetX, y + offsetY);
	}
}

class Console {
	private List<ViewPort> viewports = new ArrayList<>();
	int width, height;

	public Console(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void addViewports(ViewPort viewports) {
		this.viewports.add(viewports);
	}

	public void render() {
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				for (ViewPort vp : viewports) {
					System.out.print(vp.charAt(x, y));
				}
			}
			System.out.println();
		}
	}

	public static Console newConsole(int height, int width) {
		Buffer buffer = new Buffer(height, width);
		ViewPort vp = new ViewPort(buffer, height, width, 0, 0);
		Console console = new Console(height, width);
		console.addViewports(vp);
		return console;
	}
}

public class FacadeDemo {
	public static void main(String[] args) {
		Buffer buffer = new Buffer(30, 20);
		ViewPort vp = new ViewPort(buffer, 30, 20, 0, 0);
		Console console = new Console(30, 20);
		console.addViewports(vp);
		console.render();

		// Facade pattern
		Console console2 = Console.newConsole(30, 20);
		console2.render();

	}
}
