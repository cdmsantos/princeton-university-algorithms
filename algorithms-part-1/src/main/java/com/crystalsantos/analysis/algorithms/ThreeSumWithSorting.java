package com.crystalsantos.analysis.algorithms;

import java.util.Arrays;

/*
 * Complexity: N^2
 * Auxiliary Space: 1 (no needed)
 * */
public class ThreeSumWithSorting {

	static int  findTriplets(int arr[], int n) {
		boolean found = false;
		int count = 0;

		Arrays.sort(arr);

		for (int i = 0; i < n - 1; i++) {
			int l = i + 1; //left
			int r = n - 1; //right
			int x = arr[i];
			while (l < r) {
				if (x + arr[l] + arr[r] == 0) {
					System.out.print(x + " ");
					System.out.print(arr[l] + " ");
					System.out.println(arr[r] + " ");

					l++;
					r--;
					found = true;
					count++;
				} else if (x + arr[l] + arr[r] < 0) {
					l++;
					// If sum of three elements is less
					// than zero then increment in left
				} else {
					r--;
					// if sum is greater than zero than
					// decrement in right side
				}
			}
		}

		if (found == false) {
			System.out.println(" No Triplet Found");
		}
		
		return count;
	}

	public static void main(String[] args) {
		int[] a = {30, -40, -20, -10, 40, 0, 10, 5};
		System.out.println(findTriplets(a, a.length));
	}
}
