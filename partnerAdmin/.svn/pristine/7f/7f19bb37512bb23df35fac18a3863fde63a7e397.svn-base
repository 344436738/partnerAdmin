package com.base.singleton;

//懒汉式单例模式  
public class MySingleton {

	/**
	 * 
	 * 
	 * @author
	 * 
	 */
	private static class Singleton {
		/**
		 * 静态初始化器，用JVM来保证线程安全
		 */
		private static MySingleton singleton = new MySingleton();

		static {
			System.out.println("---->类级的内部类被加载");
		}

		private Singleton() {
			System.out.println("---->类级的内部类构造函数被调用");
		}
	}

	// 私有化构造函数
	private MySingleton() {
		System.out.println("-->开始调用构造函数");
	}

	// 开放一个公有方法，判断是否已经存在实例，有返回，没有新建一个在返回
	public static MySingleton getInstance() {
		System.out.println("-->开始调用公有方法返回实例");
		MySingleton s1 = null;
		s1 = Singleton.singleton;
		System.out.println("-->返回单例");
		return s1;
	}
}
