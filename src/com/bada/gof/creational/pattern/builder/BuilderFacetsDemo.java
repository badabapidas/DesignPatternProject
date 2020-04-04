/**
 * Builder facade demo. Facade is a simplified high-level interface that you can use to access a set of sub-interfaces.
 */
package com.bada.gof.creational.pattern.builder;

class NewPerson {
	// address
	public String streetAddress, postcode, city;

	// employment
	public String companyName, position;
	public int annualIncome;

	@Override
	public String toString() {
		return "NewPerson{" + "streetAddress='" + streetAddress + '\'' + ", postcode='" + postcode + '\'' + ", city='"
				+ city + '\'' + ", companyName='" + companyName + '\'' + ", position='" + position + '\''
				+ ", annualIncome=" + annualIncome + '}';
	}
}

// builder facade; in side this builder we can have different builders to delicate its jobs
class NewPersonBuilder {
	// the object we're going to build
	protected NewPerson person = new NewPerson(); // reference!

	public PersonJobBuilder works() {
		return new PersonJobBuilder(person);
	}

	public PersonAddressBuilder lives() {
		return new PersonAddressBuilder(person);
	}

	public NewPerson build() {
		return person;
	}
}

/**
 * Separate subclass builder which will take care of address part
 * 
 * @author bada
 *
 */
class PersonAddressBuilder extends NewPersonBuilder {
	public PersonAddressBuilder(NewPerson person) {
		this.person = person;
	}

	public PersonAddressBuilder at(String streetAddress) {
		person.streetAddress = streetAddress;
		return this;
	}

	public PersonAddressBuilder withPostcode(String postcode) {
		person.postcode = postcode;
		return this;
	}

	public PersonAddressBuilder in(String city) {
		person.city = city;
		return this;
	}
}

/**
 * Separate subclass builder which will take care of Job part
 * 
 * @author bada
 *
 */
class PersonJobBuilder extends NewPersonBuilder {
	public PersonJobBuilder(NewPerson person) {
		this.person = person;
	}

	public PersonJobBuilder at(String companyName) {
		person.companyName = companyName;
		return this;
	}

	public PersonJobBuilder asA(String position) {
		person.position = position;
		return this;
	}

	public PersonJobBuilder earning(int annualIncome) {
		person.annualIncome = annualIncome;
		return this;
	}
}

class BuilderFacetsDemo {
	public static void main(String[] args) {
		NewPersonBuilder pb = new NewPersonBuilder();
		NewPerson person = pb.lives().at("123 London Road").in("London").withPostcode("SW12BC").works().at("Fabrikam")
				.asA("Engineer").earning(123000).build();
		System.out.println(person);
	}
}