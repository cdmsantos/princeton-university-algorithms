package com.crystalsantos.sorts.elementary;

public class InsertionSort extends Sort {

	@Override
	void sort(Comparable<Comparable<?>>[] a) {
		int length = a.length;
		for (int i = 0; i < length; i++) {
			int min = i;
			for (int j = i; j > 0; j--) {
				if (less(a[j], a[min])) {
					exchange(a, i, min);
				} else {
					break;
				}
			}
		}
	}

}
