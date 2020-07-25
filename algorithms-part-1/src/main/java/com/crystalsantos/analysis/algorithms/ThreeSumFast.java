package com.crystalsantos.analysis.algorithms;

import java.util.Arrays;

/*
 * Complexity: N^2 logN 
 * */
public class ThreeSumFast {
	
	private ThreeSumFast() {}

	private static boolean containsDuplicates(int[] a) {
		for (int i = 1; i < a.length; i++)
			if (a[i] == a[i - 1])
				return true;
		return false;
	}

	public static int count(int[] a) {
		int n = a.length;
		int count = 0;

		Arrays.sort(a);
		
		if (containsDuplicates(a)) {
			throw new IllegalArgumentException("array contains duplicate integers");			
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int k = Arrays.binarySearch(a, -(a[i] + a[j]));
				if (k > j) {
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = {30, -40, -20, -10, 40, 0, 10, 5};
		int count = count(a);
		System.out.println(count);
	}
}
