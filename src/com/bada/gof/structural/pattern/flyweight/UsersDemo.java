/**
 * Space Optimization; A optimization technique that lets us use less memory by storing externally the data
 *  associated with similar objects.
 *  
 *  this example is showing how we can use flyweight technique to reuse the same user names to save space 
 */
package com.bada.gof.structural.pattern.flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Function;

/*
 * In the User class there might be a chance of duplicates in names so we are not using any special handling to make it more efficient 
 * and more space saver
 */
class User {
	private String fullName;

	public User(String fullName) {
		this.fullName = fullName;
	}
}

/**
 * Flyweight technique to restore the duplicate values to save more space
 * 
 * @author bada
 *
 */
class User2 {
	static List<String> strings = new ArrayList<>();
	private int[] names;

	public User2(String fullName) {
		Function<String, Integer> getOrAdd = (String s) -> {
			int idx = strings.indexOf(s);
			if (idx != -1)
				return idx;
			else {
				strings.add(s);
				return strings.size() - 1;
			}
		};

		names = Arrays.stream(fullName.split(" ")).mapToInt(s -> getOrAdd.apply(s)).toArray();
	}

	public String getFullName() {
		return Arrays.stream(names).mapToObj(i -> strings.get(i)).collect(Collectors.joining(","));
	}
}

class UsersDemo {
	public static void main(String[] args) {
		User2 user = new User2("John Smith");
		User2 user1 = new User2("Jane Smith");

		// have "Smith" in common
		System.out.println(user1.getFullName());
	}
}