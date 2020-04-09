/**
 * Singleton design pattern; This example is just to give a demo of singleton and showing the problem with this pattern 
 * reflection, serialization 
 *  
 */
package com.bada.gof.creational.pattern.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class BasicSingleton implements Serializable {
	private static final long serialVersionUID = 1L;

	// cannot new this class, however
	// * instance can be created deliberately (reflection)
	// * instance can be created accidentally (serialization)
	private BasicSingleton() {
		System.out.println("Singleton is initializing");
	}

	private int value = 0;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private static final BasicSingleton INSTANCE = new BasicSingleton();

	// generated getter
	public static BasicSingleton getInstance() {
		return INSTANCE;
	}

	// required for correct serialization
	// readResolve is used for _replacing_ the object read from the stream
	protected Object readResolve() {
		return INSTANCE;
	}

}

class BasicSingletonDemo {
	public static void main(String[] args) throws Exception {
		BasicSingleton singleton = BasicSingleton.getInstance();
		singleton.setValue(111);

		String filename = "singleton.bin";
		saveToFile(singleton, filename);
		singleton.setValue(222);

		BasicSingleton singleton2 = readFromFile(filename);

		System.out.println(singleton == singleton2);
		System.out.println(singleton.getValue());
		System.out.println(singleton2.getValue());
	}

	static void saveToFile(BasicSingleton singleton, String filename) throws Exception {
		try (FileOutputStream fileOut = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(singleton);
		}
	}

	static BasicSingleton readFromFile(String filename) throws Exception {
		try (FileInputStream fileIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			return (BasicSingleton) in.readObject();
		}
	}

}