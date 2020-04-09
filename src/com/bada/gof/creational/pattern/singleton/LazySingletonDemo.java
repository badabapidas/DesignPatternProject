/**
 * Example of Lazy Singleton. On demand only create or get the instance
 * 
 * Here we have given different variance like thread safe, double-checking, performance 
 */
package com.bada.gof.creational.pattern.singleton;

class LazySingleton {
	private static LazySingleton instance;

	public LazySingleton() {
		System.out.println("initializing  a lazy singleton");
	}

	// not tread safe
//	public static LazySingleton getInstance() {
//		if (instance == null) {
//			instance = new LazySingleton();
//		}
//		return instance;
//	}

	// thread safe but it will affect performance
//	public static synchronized LazySingleton getInstance() {
//		if (instance == null) {
//			instance = new LazySingleton();
//		}
//		return instance;
//	}

	// double-checking locking and it will not affect performance as well
	public static LazySingleton getInstance() {
		if (instance == null) {
			synchronized (LazySingleton.class) {
				if (instance == null) {
					instance = new LazySingleton();
				}
			}
		}
		return instance;
	}
}

public class LazySingletonDemo {

}
