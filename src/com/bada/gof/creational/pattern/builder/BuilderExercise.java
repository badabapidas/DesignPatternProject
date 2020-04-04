package com.bada.gof.creational.pattern.builder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class BuilderExercise {
	public static void main(String[] args) {
		CodeBuilder cb = new CodeBuilder("Person").addField("name", "String").addField("age", "int");
		System.out.println(cb);
	}
}

class CodeBuilder {
	private String className;
	private Map<String, String> fields = new HashMap<>();
	private final int indentSize = 1;
	private final String newLine = System.lineSeparator();
	private final String bracketStart = "{";
	private final String bracketEnd = "}";
	private final String publicField = "public";
	private final String semiColon = ";";

	public CodeBuilder(String className) {
		this.className = className;
	}

	public CodeBuilder addField(String name, String type) {
		fields.put(name, type);
		return this;
	}

	String toStringImpl(int indent) {
		StringBuilder sb = new StringBuilder();
		sb.append("public class " + className).append(newLine).append(bracketStart).append(newLine);
		String i = String.join("", Collections.nCopies(indent * indentSize, " "));
		for (Map.Entry<String, String> entry : fields.entrySet()) {
			sb.append(String.format("%s %s %s %s%s%s", i, publicField, entry.getValue(), entry.getKey(), semiColon,
					newLine));
		}
		sb.append(bracketEnd).append(newLine);
		return sb.toString();
	}

	@Override
	public String toString() {
		return toStringImpl(1);
	}
}
