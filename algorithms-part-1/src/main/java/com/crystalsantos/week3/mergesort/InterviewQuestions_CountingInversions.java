package com.crystalsantos.week3.mergesort;

/*
 * An inversion in an array a[] is a pair of entries a[i] and a[j] such that i < j
 * but a[i] > a[j]. Given an array, design a linearithmic algorithm to count the 
 * number of inversions.
 * */
public class InterviewQuestions_CountingInversions {

	private int sort(int[] a, int[] aux, int lo, int hi) {
		int inversions = 0;
		
		if (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			inversions += sort(a, aux, lo, mid);
			inversions += sort(a, aux, mid + 1, hi);
			if (!less(a[mid + 1], a[mid])) {
				return inversions;
			}
			inversions += merge(a, aux, lo, mid, hi);
		}

		return inversions;
	}

	int sort(int[] a) {
		int[] aux = new int[a.length];
		return sort(a, aux, 0, a.length - 1);
	}

	int merge(int[] a, int[] aux, int lo, int mid, int hi) {
		int inversions = 0;
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
				inversions += j - k;
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
		}
		
		return inversions;
	}

	boolean less(int v, int w) {
		return v < w;
	}

	void show(int[] a) {
		System.out.println("Sorted array:");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	public static void main(String[] args) {
		int[] a = { 8, 4, 2, 1 };
		InterviewQuestions_CountingInversions app = new InterviewQuestions_CountingInversions();
		System.out.println(app.sort(a));
	}
}
