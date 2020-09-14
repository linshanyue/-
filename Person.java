package com.mj;

public class Person implements Comparable<Person>{
	private int age;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(Person e) {//e1.compareTo(e2)
		return age-e.age;
	}
	
}
