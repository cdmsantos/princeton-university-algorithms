package com.crystalsantos.week3.mergesort;

public class MergeSortBottomUp<Item> extends Merge<Item> {

	@SuppressWarnings("unchecked")
	@Override
	public void sort(Comparable<Item>[] a) {
		int n = a.length;
		Comparable<Item>[] aux = (Comparable<Item>[]) new Comparable[a.length];
		for (int size = 1; size < n; size = size + size) {
			for (int lo = 0; lo < n - size; lo += size + size) {
				merge(a, aux, lo, lo + size - 1, Math.min(lo + size + size - 1, n - 1));
			}
		}
	}
	
	public static void main(String[] args) {
		String[] a = { "DD", "CC", "BB", "AA" };

		MergeSortBottomUp<String> merge = new MergeSortBottomUp<String>();
		merge.sort(a);
		merge.show(a);
	}
}
