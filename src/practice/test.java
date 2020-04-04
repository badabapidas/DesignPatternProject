package practice;

import java.util.Collections;
import java.util.List;

//public class test {
//	public static void main(String[] args) {
////		List<String> nCopies = Collections.nCopies(2, " ");
////		StringBuilder sb = new StringBuilder();
////		String i = String.join("", Collections.nCopies(0, " "));
////		sb.append(String.format("%s<%s>%s", i, "li", System.lineSeparator()));
////		sb.append(String.join("", Collections.nCopies(2 * (0 + 1), " "))).append("Hello")
////				.append(System.lineSeparator());
////		System.out.println(sb);
//	}
//}

class Person {
	protected String name, address;
	protected String company, position;
	protected int income;

	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address + ", company=" + company + ", position=" + position
				+ ", income=" + income + "]";
	}

}

class PersonBuilder<self extends PersonBuilder<self>> {
	protected Person person = new Person();

	protected self setName(String name) {
		person.name = name;
		return self();
	}

	protected self setAddress(String address) {
		person.address = address;
		return self();

	}

	public Person build() {
		return person;
	}

	public self self() {
		return (self) this;
	}
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

	public EmployeeBuilder setCompanyName(String companyName) {
		person.company = companyName;
		return self();
	}

	public EmployeeBuilder setIncome(int income) {
		person.income = income;
		return self();
	}

	@Override
	public EmployeeBuilder self() {
		return this;
	}
}

class test {
	public static void main(String[] args) {
		EmployeeBuilder pb = new EmployeeBuilder();
		Person person = pb.setCompanyName("SAG").setIncome(100000).setName("Bapi").setAddress("Ghy").build();
		System.out.println(person);
	}
}