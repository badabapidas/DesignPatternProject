/**
 * Copy objects using serialize/deserialize
 */

package com.bada.gof.creational.pattern.prototype;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

// some libraries use reflection (no need for Serializable)
class Foo implements Serializable {
	public int stuff;
	public String whatever;

	public Foo(int stuff, String whatever) {
		this.stuff = stuff;
		this.whatever = whatever;
	}

	@Override
	public String toString() {
		return "Foo{" + "stuff=" + stuff + ", whatever='" + whatever + '\'' + '}';
	}
}

class CopyThroughSerializationDemo {
	public static void main(String[] args) {
		Foo foo = new Foo(42, "life");
		// use apache commons!
//		Foo foo2 = SerializationUtils.roundtrip(foo);
//		byte[] serialize = SerializationUtils.serialize(foo);
//		Foo foo2 = (Foo) SerializationUtils.deserialize(serialize);

		// self implemented
		SerializeObjectInRoundtrip<Foo> s = new SerializeObjectInRoundtrip<Foo>();
		Foo foo2 = (Foo) s.roundtrip(foo);

		foo2.whatever = "xyz";

		System.out.println(foo);
		System.out.println(foo2);
	}
}

class SerializeObjectInRoundtrip<T extends Serializable> {

	public T roundtrip(T obj) {
		byte[] serialize = SerializationUtils.serialize(obj);
		return (T) SerializationUtils.deserialize(serialize);
	}
}