package com.crystalsantos.week2.sorts.elementary;

public abstract class Sort<Item> {
	void exchange(Comparable<Item>[] a, int i, int j) {
		Comparable<Item> temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	@SuppressWarnings("unchecked")
	boolean less(Comparable<Item> v, Comparable<Item> w) {
		return v.compareTo((Item) w) < 0;
	}
	
	abstract void sort(Comparable<Item>[] a);
}
