package com.bada.gof.creational.pattern.singleton;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Test;

class SingletonTester {
	public static boolean isSingleton(Supplier<Object> func) {
		return func.get() == func.get();
	}
}

public class Evaluate {

	@Test
	public void test() {
		Object obj = new Object();
		assertTrue(SingletonTester.isSingleton(() -> obj));
		assertFalse(SingletonTester.isSingleton(Object::new));
	}

	public static void main(String[] args) {
		randomeGeneratorFunc();
	}

	private static void randomeGeneratorFunc() {
		Supplier<Integer> randomNumbersSupp = () -> new Random().nextInt(10);
		Stream.generate(randomNumbersSupp).limit(5).forEach(System.out::println);
	}
}
