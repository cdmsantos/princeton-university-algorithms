package com.crystalsantos.sorts.elementary;

public class ShellSort<Item> extends Sort<Item> {

	@Override
	void sort(Comparable<Item>[] a) {
		int length = a.length;
		int h = 1;
		while (h >= 1) {
			for (int i = h; i < length; i++) {
				for (int j = i; j > h && less(a[j], a[j - h]); j -= h) {
					exchange(a, j, j - h);
				}
			}
			h = h / 3;
		}
	}
}
