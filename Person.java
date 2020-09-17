package com.mj;

public class Person implements Comparable<Person>{
	private int age;
	private String name;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(int age) {
		this.age = age;
	}
	
	public Person(int age,String name) {
		this.age = age;
		this.name=name;
	}

	@Override
	public int compareTo(Person e) {//e1.compareTo(e2)
		return age-e.age;
	}
	
	public String toString() {
		return age+"_"+name;//年龄+字符串就会转为字符串类型
	}
}
