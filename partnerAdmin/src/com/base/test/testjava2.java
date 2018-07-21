package com.base.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class testjava2 {

	public static void main(String[] args) {
		
//		List<String> list = Collections.synchronizedList(new ArrayList<String>());
		List<String> list = new ArrayList<String>();
		List<String> vect = new Vector<String>();
		List<String> link = new LinkedList<String>();
		
//		int size=10000000;
		int size=1000000;
		
		long t1, t2;
		//add
		t1 = System.currentTimeMillis();
		for (int j = 0; j < size; j++) {
			list.add("aaaaaa" + j);
		}
		t2 = System.currentTimeMillis();
		System.out.println("ArrayList add:" + (t2 - t1) + "(ms)");
		
		
		t1 = System.currentTimeMillis();
		for (int j = 0; j < size; j++) {
			vect.add("aaaaaa" + j);
		}
		t2 = System.currentTimeMillis();
		System.out.println("Vector add:" + (t2 - t1) + "(ms)");
		
		
		t1 = System.currentTimeMillis();
		for (int j = 0; j < size; j++) {
			link.add("aaaaaa" + j);
		}
		t2 = System.currentTimeMillis();
		System.out.println("LinkedList add:" + (t2 - t1) + "(ms)");
		
		
		
		//add2
		t1 = System.currentTimeMillis();
		list.add(size/2,"ccccc");
		t2 = System.currentTimeMillis();
		System.out.println("ArrayList add2:" + (t2 - t1) + "(ms)");

		t1 = System.currentTimeMillis();
		vect.add(size/2,"ccccc");
		t2 = System.currentTimeMillis();
		System.out.println("Vector add2:" + (t2 - t1) + "(ms)");

		t1 = System.currentTimeMillis();
		link.add(size/2,"ccccc");
		t2 = System.currentTimeMillis();
		System.out.println("LinkedList add2:" + (t2 - t1) + "(ms)");
				
		
		
		//set
		t1 = System.currentTimeMillis();
		list.set(size/2,"ccccc");
		t2 = System.currentTimeMillis();
		System.out.println("ArrayList set:" + (t2 - t1) + "(ms)");

		t1 = System.currentTimeMillis();
		vect.set(size/2,"ccccc");
		t2 = System.currentTimeMillis();
		System.out.println("Vector set:" + (t2 - t1) + "(ms)");

		t1 = System.currentTimeMillis();
		link.set(size/2,"ccccc");
		t2 = System.currentTimeMillis();
		System.out.println("LinkedList set:" + (t2 - t1) + "(ms)");
		
		
		
		
		//remove
		
		t1 = System.currentTimeMillis();
		list.remove(size/2);
		t2 = System.currentTimeMillis();
		System.out.println("ArrayList remove:" + (t2 - t1) + "(ms)");
		
		
		t1 = System.currentTimeMillis();
		vect.remove(size/2);
		t2 = System.currentTimeMillis();
		System.out.println("Vector remove:" + (t2 - t1) + "(ms)");
		
		
		t1 = System.currentTimeMillis();
		link.remove(size/2);
		t2 = System.currentTimeMillis();
		System.out.println("LinkedList remove:" + (t2 - t1) + "(ms)");
		
		
		
		 //iterator
		t1 = System.currentTimeMillis();
//		for (int i = 0; i < list.size(); i++) {
//			list.get(i);
//		}
		
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			iter.next();
			// System.out.println(iter.next());
		}
		
		t2 = System.currentTimeMillis();
		System.out.println("ArrayList Time iterator:" + (t2 - t1) + "(ms)");
		
		
		t1 = System.currentTimeMillis();
//		for (int i = 0; i < vect.size(); i++) {
//			vect.get(i);
//		}
		
		Iterator<String> iter2 = vect.iterator();
		while (iter2.hasNext()) {
			iter2.next();
			// System.out.println(iter.next());
		}
		t2 = System.currentTimeMillis();
		System.out.println("Vector Time iterator:" + (t2 - t1) + "(ms)");
		
		
		
		t1 = System.currentTimeMillis();
		Iterator<String> iter3 = link.iterator();
		while (iter3.hasNext()) {
			iter3.next();
			// System.out.println(iter.next());
		}
		t2 = System.currentTimeMillis();
		System.out.println("Linked Time iterator:" + (t2 - t1) + "(ms)");
		
		

		 
		

		System.out.println("Finished!!!!!!!!");

	}

}
