/**
 * Example of Inner static single example
 */
package com.bada.gof.creational.pattern.singleton;

class InnerStaticSingleton {
	private InnerStaticSingleton() {
	}

	private static class Impl {
		private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
	}

	public InnerStaticSingleton getInstances() {
		return Impl.INSTANCE;
	}
}

public class InnerStaticSingletonDemo {

}
