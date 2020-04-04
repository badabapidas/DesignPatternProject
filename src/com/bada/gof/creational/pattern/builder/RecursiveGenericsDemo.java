/**
 * Fluent Builder Inheritance with Recursive Generics
 * 
 * self-types-with-javas-generics
 */
package com.bada.gof.creational.pattern.builder;

/**
 * Person class with name and positions
 * 
 * @author bada
 *
 */
class Person {
	public String name;
	public String position;

	@Override
	public String toString() {
		return "Person{" + "name='" + name + '\'' + ", position='" + position + '\'' + '}';
	}
}

/**
 * Person builder class which extend PersonBuilder<SELF> which is nothing but
 * inheritance with recursive generics; which we have done here to get the
 * correct builder object
 * 
 * for more details visit:
 * https://www.sitepoint.com/self-types-with-javas-generics/
 * 
 * @author bada
 *
 * @param <SELF>
 */
class PersonBuilder<SELF extends PersonBuilder<SELF>> {
	protected Person person = new Person();

// critical to return SELF here
	public SELF withName(String name) {
		person.name = name;
		return self();
	}

	protected SELF self() {
		// unchecked cast, but actually safe
		// proof: try sticking a non-PersonBuilder
		// as SELF parameter; it won't work!
		return (SELF) this;
	}

	public Person build() {
		return person;
	}
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {
	public EmployeeBuilder worksAs(String position) {
		person.position = position;
		return self();
	}

	@Override
	protected EmployeeBuilder self() {
		return this;
	}
}

class RecursiveGenericsDemo {
	public static void main(String[] args) {
		EmployeeBuilder eb = new EmployeeBuilder().withName("Dmitri").worksAs("Quantitative Analyst");
		System.out.println(eb.build());
		Person pb = new PersonBuilder().withName("Bapi").build();
		System.out.println(pb);

	}
}