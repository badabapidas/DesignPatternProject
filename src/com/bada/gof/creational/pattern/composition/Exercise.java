package com.bada.gof.creational.pattern.composition;

import java.util.*;

interface ValueContainer extends Iterable<Integer> {
}

class SingleValue implements ValueContainer {
	public int value;

	public SingleValue(int value) {
		this.value = value;
	}

	@Override
	public Iterator<Integer> iterator() {
		return Collections.singleton(value).iterator();
	}
}

class ManyValues extends ArrayList<Integer> implements ValueContainer {
}

class MyList extends ArrayList<ValueContainer> {
	public MyList(Collection<? extends ValueContainer> c) {
		super(c);
	}

	public int sum() {
		int result = 0;
		for (ValueContainer c : this) {
			for (int i : c) {
				result += i;
			}
		}
		System.out.println(result);
		return result;
	}
}

public class Exercise {
	public static void main(String[] args) {
		SingleValue num1= new SingleValue(20);
		SingleValue num2= new SingleValue(20);
		ManyValues m= new ManyValues();
		m.add(12);
		m.add(12);
		MyList list= new MyList(List.of(num1,num2));
		list.sum();
		MyList list2= new MyList(List.of(m));
		list2.sum();
	}

}
