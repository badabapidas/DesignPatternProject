package com.bada.gof.creational.pattern.singleton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Test;

public class Testability {
	@Test
	public void isSingletonTest() {
		SingletonDatabase db = SingletonDatabase.getInstance();
		SingletonDatabase db2 = SingletonDatabase.getInstance();
		assertSame(db, db2);
		assertEquals(1, SingletonDatabase.getCount());
	}

	@Test
	public void singletonTotalPopulationTest() {
		// testing on a live database
		SingletonRecordFinder rf = new SingletonRecordFinder();
		List<String> names = List.of("Seoul", "Mexico City");
		int tp = rf.getTotalPopulation(names);
		assertEquals(17500000 + 17400000, tp);
	}

	@Test
	public void dependentPopulationTest() {
		DummyDatabase db = new DummyDatabase();
		ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
		assertEquals(4, rf.getTotalPopulation(List.of("alpha", "gamma")));
	}
}