package com.crystalsantos.week2.sorts.elementary;

public class SelectionSort<Item> extends Sort<Item>{
	
	@Override
	void sort(Comparable<Item>[] a) {
		int length = a.length;
		for(int i = 0; i < length; i++) {
			int min = i;
			for(int j = i+1; j < length; j++) {
				if(less(a[j], a[min])) {
					min = j;
				}
			}
			exchange(a, i, min);
		}
	}
}
