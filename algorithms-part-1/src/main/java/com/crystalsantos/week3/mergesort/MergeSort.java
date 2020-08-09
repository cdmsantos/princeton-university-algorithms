package com.crystalsantos.week3.mergesort;

public class MergeSort<Item> extends Merge<Item> {

	public MergeSort() {
	}

	private void sort(Comparable<Item>[] a, Comparable<Item>[] aux, int lo, int hi) {
		if (hi <= lo) {
			return;
		}

		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		if (!less(a[mid + 1], a[mid])) {
			return;
		}
		merge(a, aux, lo, mid, hi);

		assert isSorted(a);
	}

	@SuppressWarnings("unchecked")
	@Override
	void sort(Comparable<Item>[] a) {
		Comparable<Item>[] aux = (Comparable<Item>[]) new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	public static void main(String[] args) {
		String[] a = { "DD", "CC", "BB", "AA" };

		MergeSort<String> merge = new MergeSort<String>();
		merge.sort(a);
		merge.show(a);
	}
}
