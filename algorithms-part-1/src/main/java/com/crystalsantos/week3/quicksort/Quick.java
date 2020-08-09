package com.crystalsantos.week3.quicksort;

public class Quick<Item> {
	void exchange(Comparable<Item>[] a, int i, int j) {
		Comparable<Item> temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	@SuppressWarnings("unchecked")
	boolean less(Comparable<Item> v, Comparable<Item> w) {
		return v.compareTo((Item) w) < 0;
	}

	int partition(Comparable<Item>[] a, int low, int high) {
		int i = low;
		int j = high + 1;

		while (true) {
			while (less(a[++i], a[low])) {
				if (i == high) {
					break;
				}
			}

			while (less(a[low], a[--j])) {
				if (j == low) {
					break;
				}
			}

			if (i >= j) {
				break;
			}

			exchange(a, i, j);
		}

		exchange(a, low, j);

		return j;
	}

	boolean isSorted(Comparable<Item>[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	boolean isSorted(Comparable<Item>[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	void show(Comparable<Item>[] a) {
		System.out.println("Sorted array:");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}
