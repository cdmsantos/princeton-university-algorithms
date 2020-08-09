package com.crystalsantos.week3.quicksort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSelection<Item> extends Quick<Item> {

	Comparable<Item> select(Comparable<Item>[] a, int k) {
		StdRandom.shuffle(a);
		int low = 0;
		int high = a.length - 1;

		while (high > low) {
			int j = partition(a, low, high);

			if (j < k) {
				low = j + 1;
			} else if (j > k) {
				high = j - 1;
			} else {
				return a[k];
			}
		}

		return a[k];
	}
}
