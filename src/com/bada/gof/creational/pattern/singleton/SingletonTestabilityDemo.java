/**
 * Class to demo singleton testability
 */
package com.bada.gof.creational.pattern.singleton;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import com.google.common.collect.Iterables;

interface Database {
	int getPopulation(String name);
}

class SingletonDatabase {

	private Dictionary<String, Integer> capitals = new Hashtable<>();
	private static int instanceCount = 0;

	public static int getCount() {
		return instanceCount;
	}

	private SingletonDatabase() {
		instanceCount++;
		System.out.println("Initializing database");

		try {
//			 File f = new
//			 File(Testability.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			File f = new File(
					"C:\\Bapi\\Projects\\GIT\\DesignPatternProject\\src\\com\\bada\\gof\\creational\\pattern\\singleton\\");
			Path fullPath = Paths.get(f.getPath(), "capitals.txt");
			List<String> lines = Files.readAllLines(fullPath);
			Iterables.partition(lines, 2).forEach(kv -> capitals.put(kv.get(0).trim(), Integer.parseInt(kv.get(1))));
		} catch (Exception e) {
			// handle it!
			System.err.println(e);
		}
	}

	public int getPopulation(String name) {
		return capitals.get(name);
	}

	private static final SingletonDatabase INSTANCE = new SingletonDatabase();

	public static SingletonDatabase getInstance() {
		return INSTANCE;
	}
}

class SingletonRecordFinder {
	public int getTotalPopulation(List<String> names) {
		int result = 0;
		for (String name : names)
			result += SingletonDatabase.getInstance().getPopulation(name);
		return result;
	}
}

/**
 * Helper class to decouple the hard links bet higher class and low level impl
 * 
 * This is a proper design which help user to hook any database; even for
 * testing now u dont have to deal with real data you can feed any dummy data as
 * we have done
 * 
 * @author bada
 *
 */
class ConfigurableRecordFinder {
	private Database database;

	public ConfigurableRecordFinder(Database database) {
		this.database = database;
	}

	public int getTotalPopulation(List<String> names) {
		int result = 0;
		for (String name : names)
			result += database.getPopulation(name);
		return result;
	}
}

class SingletonTestabilityDemo {
	public static void main(String[] args) {
		SingletonDatabase db = SingletonDatabase.getInstance();

		String city = "Tokyo";
		int pop = db.getPopulation(city);
		System.out.println(String.format("%s has population %d", city, pop));
	}
}

class DummyDatabase implements Database {
	private Dictionary<String, Integer> data = new Hashtable<>();

	public DummyDatabase() {
		data.put("alpha", 1);
		data.put("beta", 2);
		data.put("gamma", 3);
	}

	@Override
	public int getPopulation(String name) {
		return data.get(name);
	}
}
