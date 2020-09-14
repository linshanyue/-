package com.mj;

public class Main {
	//提供两种比较方式
	private static class PersonComparator1 implements Comparator<Person> {
		public int compare(Person e1, Person e2) {
			return e1.getAge()-e2.getAge();
		}
	}
	
	private static class PersonComparator2 implements Comparator<Person> {
		public int compare(Person e1, Person e2) {
			return e2.getAge()-e1.getAge();
		}
	}

	public static void main(String[] args) {
		/*Integer data[]=new Integer[] {
			7,4,9,2,5,8,11,3	
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();//内置类型不需要自己写比较器，内部已经实现了
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}*/
		
		BinarySearchTree<Person> bst1 = new BinarySearchTree<>(new Comparator<Person>() {//匿名类自定义比较逻辑
			public int compare(Person e1, Person e2) {
				return 0;
			}
		});//自定义比较逻辑
		bst1.add(new Person(12));
		bst1.add(new Person(15));
		
		BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator1());//自定义比较逻辑
		bst2.add(new Person(12));
		bst2.add(new Person(15));
		
		BinarySearchTree<Person> bst3 = new BinarySearchTree<>();//用内部的比较逻辑
		bst3.add(new Person(12));
		bst3.add(new Person(15));
	}

}
