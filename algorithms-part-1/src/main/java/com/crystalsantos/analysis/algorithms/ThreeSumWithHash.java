package com.crystalsantos.analysis.algorithms;

import java.util.HashSet;

/*
 * Complexity: N^2
 * Auxiliary Space: N (hashmap)
 * */
public class ThreeSumWithHash {
	
	static int findTriplets(int arr[], int n) {
		int count = 0;
		boolean found = false;

		for (int i = 0; i < n - 1; i++) {
			HashSet<Integer> s = new HashSet<Integer>();
			for (int j = i + 1; j < n; j++) {
				int x = -(arr[i] + arr[j]);
				if (s.contains(x)) {
					System.out.printf("%d %d %d\n", x, arr[i], arr[j]);
					found = true;
					count++;
				} else {
					s.add(arr[j]);
				}
			}
		}

		if (found == false) {
			System.out.println("No Triplet Found!");
		}
		
		return count;
	}

	public static void main(String[] args) {
		int[] a = {30, -40, -20, -10, 40, 0, 10, 5};
		System.out.println(findTriplets(a, a.length));
	}
}
