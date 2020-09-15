package com.mj;

import java.util.Comparator;

import com.mj.printer.BinaryTreeInfo;

public class BinarySearchTree<E> implements BinaryTreeInfo {
	private int size;
	private Node<E> root;
	private Comparator<E> comparator;
	
	public BinarySearchTree() {//不需要传入一个比较器
		this(null);
	}
	
	public BinarySearchTree(Comparator<E> comparator) {//传入一个比较器
		this.comparator=comparator;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public void clear() {
		
	}
	
	public void add(E element) {
		elementNotNullCheck(element);
		//用循环的方法做
		if (root==null) {//添加第一个节点（根节点）
			root=new Node<>(element,null);
			size++;
			return;
		}
		//添加的不是第一个节点：
		//找到父节点
		Node<E> parent = root;
		Node<E> node =root;
		int cmp = 0;
		while (node!=null) {
			cmp = compare(element, node.element);
			parent=node;
			if (cmp>0) {
				node=node.right;
			}else if (cmp<0) {
				node=node.left;
			}else {//相等
				return;
			}
		}
		//看看插入到父节点的哪个位置
		Node<E> newNode = new Node<>(element,parent);
		if (cmp>0) {
			parent.right=newNode;
		}else if (cmp<0) {
			parent.left=newNode;
		}
		size++;
	}
	
	public void remove(E element) {
		//remove(node(element));
	}
	
	public boolean cointains(E element) {
		return false;//node(element) != null;
	}
	
	@SuppressWarnings("unchecked")
	private int compare(E e1,E e2) {//注意不同类的比较如何考虑--添加Comparab接口，让每个类实现一个compareTo方法
	//返回值=0，代表e1=e2，返回值>0，代表e1>e2，返回值<0，代表e1<e2	
	//比较器可以实现外部自定义或者内部进行规定
		if (comparator != null) {
			return comparator.compare(e1, e2);//从外面的Main中的比较器传入
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	private void elementNotNullCheck(E element) {
		if (element==null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
	
	private static class Node<E>{//内部需要维护一个节点类
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		public Node(E element,Node<E> parent) {
			this.element=element;
			this.parent=parent;
		}
	}

	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		Node<E> myNode = (Node<E>)node;
		String parentString=null;
		if (myNode.parent!=null) {
			parentString=myNode.parent.element.toString();
		}
		return myNode.element+"_"+"p("+parentString+")";
	}
}
