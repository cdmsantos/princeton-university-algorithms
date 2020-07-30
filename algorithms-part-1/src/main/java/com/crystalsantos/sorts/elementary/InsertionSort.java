package com.crystalsantos.sorts.elementary;

public class InsertionSort<Item> extends Sort<Item> {

	@Override
	void sort(Comparable<Item>[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j > 0 && less(array[j], array[j - 1]); j--) {
				exchange(array, j, j - 1);
			}
		}
	}
}
