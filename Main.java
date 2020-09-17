package com.mj;

import java.util.Comparator;

import com.mj.BinarySearchTree.Visitor;
import com.mj.file.Files;
import com.mj.printer.BinaryTrees;
import com.mj.printer.BinaryTrees.PrintStyle;

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

	static void test1() {
		Integer data[]=new Integer[] {
			7,4,9,2,5,8,11,3,12,1	
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();//内置类型不需要自己写比较器，内部已经实现了
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		BinaryTrees.println(bst);
	}
	
	static void test2() {
		Integer data[]=new Integer[] {
				7,4,9,2,5,8,11,3,12,1	
			};
		
		BinarySearchTree<Person> bst1 = new BinarySearchTree<>();//用内部的比较逻辑
		for (int i = 0; i < data.length; i++) {
			bst1.add(new Person(data[i]));
		}
		
		BinaryTrees.println(bst1);
		
		BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new Comparator<Person>() {
			public int compare(Person o1,Person o2) {
				return o2.getAge()-o1.getAge();
			}
		});
		for (int i = 0; i < data.length; i++) {
			bst2.add(new Person(data[i]));
		}
		
		BinaryTrees.println(bst2);
	}
	
	static void test3() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < 30; i++) {
			bst.add((int)(Math.random()*100));
		}
		String str = BinaryTrees.printString(bst);
		str+="/n";
		Files.writeToFile("D:/1.txt", str);
	}
	
	static void test4() {
		BinarySearchTree<Person> bst = new BinarySearchTree<>();
		bst.add(new Person(10, "jack"));
		bst.add(new Person(12, "rose"));
		bst.add(new Person(6, "jim"));
		bst.add(new Person(10, "michael"));
		BinaryTrees.println(bst);
	}
	
	static void test5() {
		Integer data[]=new Integer[] {
				7,4,9,2,5,8,11,3,12,1,10	
			};
			BinarySearchTree<Integer> bst = new BinarySearchTree<>();//内置类型不需要自己写比较器，内部已经实现了
			for (int i = 0; i < data.length; i++) {
				bst.add(data[i]);
			}
			BinaryTrees.println(bst);
			//bst.levelOrderTraversal();
			bst.levelOrder(new Visitor<Integer>() {//外界来实现遍历逻辑
				public void visit(Integer element) {
					System.out.print("_"+element+"_");	
				}
			});
	}
	
	static void test6() {
		Integer data[]=new Integer[] {
			7,4,9,2,5,8,11,3,12,1	
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();//内置类型不需要自己写比较器，内部已经实现了
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		//BinaryTrees.println(bst);
		System.out.println(bst);//用自己重写的函数打印
		System.out.println(bst.height1());
	}
	public static void main(String[] args) {
		test6();
		
		/*BinarySearchTree<Person> bst1 = new BinarySearchTree<>(new Comparator<Person>() {//匿名类自定义比较逻辑
			public int compare(Person e1, Person e2) {
				return 0;
			}
		});//自定义比较逻辑
		bst1.add(new Person(12));
		bst1.add(new Person(15));
		
		BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator1());//自定义比较逻辑
		bst2.add(new Person(12));
		bst2.add(new Person(15));*/
		
		
	}

}
