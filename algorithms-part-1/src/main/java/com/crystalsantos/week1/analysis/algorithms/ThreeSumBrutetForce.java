package com.crystalsantos.week1.analysis.algorithms;

/*
 * Complexity: N^3
 * */
public class ThreeSumBrutetForce {

	public static int count(int[] a) {
		int n = a.length;
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {
				for(int k = j+1; k < n; k++) {
					if(a[i] + a[j] + a[k] == 0) {
						count++;
					}
				}
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		int[] a = {30, -40, -20, -10, 40, 0, 10, 5};
		System.out.println(count(a));
	}
}
