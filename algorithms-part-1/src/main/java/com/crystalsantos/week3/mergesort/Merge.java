package com.crystalsantos.week3.mergesort;

public abstract class Merge<Item> {

	void merge(Comparable<Item>[] a, Comparable<Item>[] aux, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);

		for (int k = lo; k <= hi; k++) { // copy a to aux
			aux[k] = a[k];
		}

		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (less(aux[j], aux[i])) {
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
		}

		assert isSorted(a, lo, hi);
	}

	boolean isSorted(Comparable<Item>[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	@SuppressWarnings("unchecked")
	boolean less(Comparable<Item> v, Comparable<Item> w) {
		return v.compareTo((Item) w) < 0;
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
    }

	abstract void sort(Comparable<Item>[] a);
}
