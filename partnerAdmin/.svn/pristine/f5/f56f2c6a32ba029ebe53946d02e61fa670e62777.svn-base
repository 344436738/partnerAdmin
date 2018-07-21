package com.base.singleton;

public class ClientSingleton {

	/**
	 * 懒汉式单例模式
	 * 
	 * 
	 */
	public static void myprint2a() {
		System.out.println("---------------懒汉式单例模式--------------");
		System.out.println("第一次取得实例");
		MySingleton s1 = MySingleton.getInstance();
		System.out.println("第二次取得实例");
		MySingleton s2 = MySingleton.getInstance();
		if (s1 == s2) {
			System.out.println("懒汉式");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 懒汉式
		myprint2a();

	}
}
