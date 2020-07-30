package com.crystalsantos.sorts.elementary;

/*
 * Given two arrays a[] and b[], each containing n distinct 2D points in 
 * the plane, design a subquadratic algorithm to count the number of points 
 * that are contained both in array a[] and array b[].
 * */
public class InterviewQuestion_IntersectionOfTwoSets {
	int intersection;

	public InterviewQuestion_IntersectionOfTwoSets() {
		intersection = 0;
	}

	int intersection(Integer a[], Integer b[]) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				if (a[i] == b[j]) {
					intersection++;
				}
			}
		}
		return intersection;
	}

	public static void main(String[] args) {
		InterviewQuestion_IntersectionOfTwoSets app = new InterviewQuestion_IntersectionOfTwoSets();

		Integer a[] = {7, 1, 5, 2, 3, 6};
		Integer b[] = {3, 8, 6, 20, 7};
		
		System.out.println(app.intersection(a, b));
	}
}
