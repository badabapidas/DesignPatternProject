/**
 * In this example we have shown how mono static works.
 * 
 * So while creating instances it will use singleton static parameters. But this design will make user very confused
 * as there is no way to convey the inforamtion to the user that inside this is using singleton design
 * 
 * So this type of approach can be avoided if you wanna use proper singleton desing
 *  
 */
package com.bada.gof.creational.pattern.singleton;

class ChiefExecutiveOfficer {
	private static String name;
	private static int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		ChiefExecutiveOfficer.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		ChiefExecutiveOfficer.age = age;
	}

	@Override
	public String toString() {
		return "ChiefExecutiveOfficer [name=" + name + ", age=" + age + "]";
	}

}

public class MonostateDemo {
	public static void main(String[] args) {
		ChiefExecutiveOfficer ceo = new ChiefExecutiveOfficer();
		ceo.setName("Bapi Das");
		ceo.setAge(52);

		ChiefExecutiveOfficer ceo2 = new ChiefExecutiveOfficer();
		System.out.println(ceo2);
	}

}
