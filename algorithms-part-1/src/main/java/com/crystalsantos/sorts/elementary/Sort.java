package com.crystalsantos.sorts.elementary;

public abstract class Sort {
	void exchange(Comparable<Comparable<?>>[] a, int i, int j) {
		Comparable<Comparable<?>> swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	boolean less(Comparable<Comparable<?>> v, Comparable<Comparable<?>> w) {
		return v.compareTo(w) < 0;
	}
	
	abstract void sort(Comparable<Comparable<?>>[] a);
}
