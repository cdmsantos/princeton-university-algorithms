package com.crystalsantos.week4.priorityqueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Heap {

	private Heap() {
	}

	// Rearranges the array in ascending order, using the natural order.
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] pq) {
		int n = pq.length;

		// heapify phase
		for (int k = n / 2; k >= 1; k--) {
			sink(pq, k, n);
		}

		// sortdown phase
		int k = n;
		while (k > 1) {
			exch(pq, 1, k--);
			sink(pq, 1, k);
		}
	}

	@SuppressWarnings("rawtypes")
	private static void sink(Comparable[] pq, int k, int n) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && less(pq, j, j + 1)) {
				j++;
			}
			if (!less(pq, k, j)) {
				break;
			}
			exch(pq, k, j);
			k = j;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparable[] pq, int i, int j) {
		return pq[i - 1].compareTo(pq[j - 1]) < 0;
	}

	private static void exch(Object[] pq, int i, int j) {
		Object swap = pq[i - 1];
		pq[i - 1] = pq[j - 1];
		pq[j - 1] = swap;
	}

	@SuppressWarnings("rawtypes")
	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.println(a[i]);
		}
	}

	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		Heap.sort(a);
		show(a);
	}
}