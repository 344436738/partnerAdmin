package com.base.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestQuartz2 {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		System.out.println("Test start." + "\n");
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("spring-quartz.xml");
		System.out.print("Test end..");
	}

	public void work3() {

		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(format);

		System.out.println(formatter.format(new Date()) + "在制定的时间做指定的事情！！");

		System.out.println("每15秒查看一下！！！");

		System.out.println("\n");
	}
}