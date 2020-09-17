package com.mj;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

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
			}else {//相等--建议覆盖
				node.element=element;
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
	/*
	public void preorderTraversal() {//前序遍历
		preorderTraversal(root);
	}
	
	private void preorderTraversal(Node<E> node) {
		if(node==null) return;
		System.out.println(node.element);
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}
	
	public void inorderTraversal() {//中序遍历
		inorderTraversal(root);
	}
	
	private void inorderTraversal(Node<E> node) {
		if(node==null) return;
		inorderTraversal(node.left);
		System.out.println(node.element);
		inorderTraversal(node.right);
	}
	
	public void postorderTraversal() {//后序遍历
		postorderTraversal(root);
	}
	
	private void postorderTraversal(Node<E> node) {
		if(node==null) return;
		postorderTraversal(node.left);
		postorderTraversal(node.right);
		System.out.println(node.element);
	}
	
	public void levelOrderTraversal() {//层序遍历
		if(root==null) return;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);//入队
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();//出队
			System.out.println(node.element);
			if(node.left!=null) {
				queue.offer(node.left);
			}
			if(node.right!=null) {
				queue.offer(node.right);
			}
		}
	}
	*/
	public void preorder(Visitor<E> visitor) {
		if(root==null||visitor==null) return;
		preorder(root,visitor);
	}
	
	private void preorder(Node<E> node,Visitor<E> visitor) {
		if(root==null||visitor==null) return;
		visitor.visit(node.element);
		preorder(node.left,visitor);
		preorder(node.right,visitor);
	}
	
	public void inorder(Visitor<E> visitor) {
		if(root==null||visitor==null) return;
		inorder(root,visitor);
	}
	
	private void inorder(Node<E> node,Visitor<E> visitor) {
		if(root==null||visitor==null) return;
		inorder(node.left,visitor);
		visitor.visit(node.element);
		inorder(node.right,visitor);
	}
	
	public void postorder(Visitor<E> visitor) {
		if(root==null||visitor==null) return;
		postorder(root,visitor);
	}
	
	private void postorder(Node<E> node,Visitor<E> visitor) {
		if(root==null||visitor==null) return;
		postorder(node.left,visitor);
		postorder(node.right,visitor);
		visitor.visit(node.element);
	}
	public void levelOrder(Visitor<E> visitor) {
		if(root==null||visitor==null) return;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);//入队
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();//出队
			visitor.visit(node.element);
			if(node.left!=null) {
				queue.offer(node.left);
			}
			if(node.right!=null) {
				queue.offer(node.right);
			}
		}
	}
	
	public static interface Visitor<E>{
		void visit(E element);
	}
	
	public int height1() {//迭代
		if(root==null) return 0;
		int height=0;
		int levelsize=1;//存储每一层元素的数量
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);//入队
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();//出队
			levelsize--;
			if(node.left!=null) {
				queue.offer(node.left);
			}
			if(node.right!=null) {
				queue.offer(node.right);
			}
			if(levelsize==0) {//意味着即将访问下一层
				levelsize=queue.size();
				height++;
			}
		}
		return height;
	}
	
	public int height2() {
		return height(root);
	}
	private int height(Node<E> node) {//递归获取某一个节点的高度
		if(node==null) return 0;
		return 1+Math.max(height(node.left), height(node.right));
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root,sb," ");
		return sb.toString();
	}
	
	private void toString(Node<E> node, StringBuilder sb,String prefix) {//来完成递归
		if(node==null) return;
		sb.append(prefix).append(node.element).append("\n");
		toString(node.left,sb,prefix+"L---");
		toString(node.right,sb,prefix+"R---"); 
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
