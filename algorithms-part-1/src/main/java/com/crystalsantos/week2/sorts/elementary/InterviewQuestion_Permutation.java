package com.crystalsantos.week2.sorts.elementary;

/*
 * Permutation. Given two integer arrays of size n, design a subquadratic 
 * algorithm to determine whether one is a permutation of the other. 
 * That is, do they contain exactly the same entries but, possibly, 
 * in a different order.
 * */
public class InterviewQuestion_Permutation extends InsertionSort<Integer> {

	boolean permutation(Integer a[], Integer b[]) {
		if (a.length != b.length) {
			return false;
		} else {
			sort(a);
			sort(b);
			for (int i = 0; i < a.length; i++) {
				if (a[i] != b[i]) {
					return false;
				}
			}
			return true;
		}
	}

	public static void main(String arg[]) {
		InterviewQuestion_Permutation app = new InterviewQuestion_Permutation();
		Integer arr1[] = { 2, 1, 3, 5, 4, 3, 2 };
		Integer arr2[] = { 3, 2, 2, 4, 5, 3, 1 };
		if (app.permutation(arr1, arr2)) {
			System.out.println("Arrays are permutations of each other");			
		} else {
			System.out.println("Arrays are NOT permutations of each other");			
		}
		
		Integer arr3[] = {2, 1, 3, 5};
		Integer arr4[] = {3, 2, 2, 4};
		if (app.permutation(arr3, arr4)) {
			System.out.println("Arrays are permutations of each other");			
		} else {
			System.out.println("Arrays are NOT permutations of each other");			
		}
	}
}
