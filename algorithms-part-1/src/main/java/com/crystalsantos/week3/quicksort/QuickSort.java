package com.crystalsantos.week3.quicksort;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort<Item> extends Quick<Item> {

	int CUTOFF = 4;

	void sort(Comparable<Item>[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	void sort(Comparable<Item>[] a, int low, int high) {
		if (high <= low + CUTOFF - 1) {
			Insertion.sort(a, low, high);
		} else if (low < high) {
			int j = partition(a, low, high);

			sort(a, low, j - 1);
			sort(a, j + 1, high);
		}
	}

	public static void main(String[] args) {
		QuickSort<Integer> app = new QuickSort<Integer>();
		Integer[] a = { 20, 30, 1, 27, 18, 20, 12, 4, 5, 2 };
		app.sort(a);
		app.show(a);
	}
}
