package com.crystalsantos.week3.quicksort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSortThreeWay<Item> extends Quick<Item> {

	void sort(Comparable<Item>[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	@SuppressWarnings("unchecked")
	void sort(Comparable<Item>[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int lt = lo, gt = hi;
		Comparable<Item> v = a[lo];
		int i = lo + 1;
		while (i <= gt) {
			int cmp = a[i].compareTo((Item) v);
			if (cmp < 0)
				exchange(a, lt++, i++);
			else if (cmp > 0)
				exchange(a, i, gt--);
			else
				i++;
		}

		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}
}
