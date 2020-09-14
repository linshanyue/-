package com.mj;

public interface Comparable<E> {//约束进入二叉树的类满足此接口，即满足可比较性
	//缺点：将比较方式约束在类之中，即一个类只能有一种比较方式来判断大小，不够灵活
	int compareTo(E e);
}
