package com.crystalsantos.week3.mergesort;

/*
 * Suppose that the subarray a[0] to a[n−1] is sorted and the subarray 
 * a[n] to a[2*n−1] is sorted. How can you merge the two subarrays so that 
 * a[0] to a[2*n−1] is sorted using an auxiliary array of length n 
 * (instead of 2n)?
 * */
public class InterviewQuestion_MergingWithSmallerAuxiliaryArray {

	void merge(Integer[] a) {
		Integer[] aux = new Integer[a.length / 2];

		for (int k = 0; k < a.length / 2; k++) { // copy a[0] to a[n-1] to aux
			aux[k] = a[k];
		}

		int k = 0, j = a.length / 2;
		for (int i = 0; i < a.length; i++) {
			if (j > a.length - 1) {
				a[i] = aux[k];
				k++;
			} else if(k > a.length/2 - 1) {
				a[i] = a[j];
				j++;
			} else {
				if (a[j] < aux[k]) {
					a[i] = a[j];
					j++;
				} else {
					a[i] = aux[k];
					k++;
				}
			}
		}

	}

	void show(Integer[] a) {
		System.out.println("Sorted array:");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	void sort(Integer[] a) {

		merge(a);
	}

	public static void main(String[] args) {
		Integer[] a = { 1, 2, 3, 0, 2, 5 };
//		Integer[] a = { 10, 32, 55, 9, 17, 28 };

		InterviewQuestion_MergingWithSmallerAuxiliaryArray app = new InterviewQuestion_MergingWithSmallerAuxiliaryArray();
		app.sort(a);
		app.show(a);
	}

}
