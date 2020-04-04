/**
 * First builder pattern example. Her first we shown if we wanna write a simple html code using String we can do that. But if
 * the code is little complex, using String concatenation is difficult to handle so in that case to ease we used an inbuilt
 * builder pattern name StringBuilder. We have seen the benefits of using this builder. 
 * 
 * The we created a html builder itself to handle this job which internally using the same StringBUilder only. WE have created 
 * both fluent and non-fluent HtmlBuilder which help users to create a simple html script
 * 
 * 
 */
package com.bada.gof.creational.pattern.builder;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Helper Class to create html element; which will take one name and one text
 * like ("li", "hello")
 * 
 * @author bada
 *
 */
class HtmlElement {
	public String name, text;
	public ArrayList<HtmlElement> elements = new ArrayList<HtmlElement>();
	private final int indentSize = 2;
	private final String newLine = System.lineSeparator();

	public HtmlElement() {
	}

	public HtmlElement(String name, String text) {
		this.name = name;
		this.text = text;
	}

	private String toStringImpl(int indent) {
		StringBuilder sb = new StringBuilder();
		String i = String.join("", Collections.nCopies(indent * indentSize, " "));
		sb.append(String.format("%s<%s>%s", i, name, newLine));
		if (text != null && !text.isEmpty()) {
			sb.append(String.join("", Collections.nCopies(indentSize * (indent + 1), " "))).append(text)
					.append(newLine);
		}

		for (HtmlElement e : elements)
			sb.append(e.toStringImpl(indent + 1));

		sb.append(String.format("%s</%s>%s", i, name, newLine));
		return sb.toString();
	}

	@Override
	public String toString() {
		return toStringImpl(0);
	}
}

/**
 * Builder class which will help to create html element
 * 
 * @author bada
 *
 */
class HtmlBuilder {
	private String rootName;
	private HtmlElement root = new HtmlElement();

	public HtmlBuilder(String rootName) {
		this.rootName = rootName;
		root.name = rootName;
	}

	// not fluent
	public void addChild(String childName, String childText) {
		HtmlElement e = new HtmlElement(childName, childText);
		root.elements.add(e);
	}

	// fluent
	public HtmlBuilder addChildFluent(String childName, String childText) {
		HtmlElement e = new HtmlElement(childName, childText);
		root.elements.add(e);
		return this;
	}

	public void clear() {
		root = new HtmlElement();
		root.name = rootName;
	}

	// delegating
	@Override
	public String toString() {
		return root.toString();
	}
}

class BuilderDemo {
	public static void main(String[] args) {

		// we want to build a simple HTML paragraph
		System.out.println("Testing");
		String hello = "hello";
		System.out.println("<p>" + hello + "</p>");

		// now we want to build a list with 2 words; if we wd have go with the same
		// String + pattern it would have been very complex to handle the below
		// scenario. So to simplify the approach we used StringBuilder; this is the
		// advantage of any pattern over normal code

		String[] words = { "hello", "world" };
		StringBuilder sb = new StringBuilder(); // inbuilt builder class
		sb.append("<ul>\n");
		for (String word : words) {
			// indentation management, line breaks and other evils
			sb.append(String.format("  <li>%s</li>\n", word));
		}
		sb.append("</ul>");
		System.out.println(sb);

		// Created our first ordinary non-fluent builder
		HtmlBuilder builder = new HtmlBuilder("ul");
		builder.addChild("li", "hello");
		builder.addChild("li", "world");
		System.out.println(builder);

		// fluent builder; fluent means you can use the methods in a series rather then
		// builder. every time as addChilFluent() will return the builder object itself
		builder.clear();
		builder.addChildFluent("li", "hello").addChildFluent("li", "world");
		System.out.println(builder);
	}
}