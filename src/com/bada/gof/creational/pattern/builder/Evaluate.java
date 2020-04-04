package com.bada.gof.creational.pattern.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Evaluate {
	private String preprocess(String text) {
		return text.replace("\r\n", "\n").trim();
	}

	@Test
	public void emptyTest() {
		CodeBuilder cb = new CodeBuilder("Foo");
		assertEquals("public class Foo\n{\n}", preprocess(cb.toString()));
	}

	@Test
	public void personTest() {
		CodeBuilder cb = new CodeBuilder("Person").addField("name", "String").addField("age", "int");
		String a = "public class Person\n{\n" + "  public String name;\n" + "  public int age;\n}";
		String b = preprocess(cb.toString());
		System.out.println(a);
		System.out.println(b);
		assertEquals("public class Person\n{\n" + "  public String name;\n" + "  public int age;\n}",
				preprocess(cb.toString()));
	}
}