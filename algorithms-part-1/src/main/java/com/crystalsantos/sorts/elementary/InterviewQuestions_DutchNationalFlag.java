package com.crystalsantos.sorts.elementary;

/*
 * Given an array of n buckets, each containing a red, white, or blue pebble, 
 * sort them by color. The allowed operations are:
 * 		swap(i, j): swap the pebble in bucket i with the pebble in bucket j.
 * 		color(i): determine the color of the pebble in bucket i.
 * The performance requirements are as follows:
 * 		At most n calls to color().
 * 		At most n calls to swap().
 * 		Constant extra space.
 * */
public class InterviewQuestions_DutchNationalFlag {
	void sort(char flags[]) {
		int low = 0;
		int mid = 0;
		int high = flags.length - 1;
		char temp;

		while (mid <= high) {
			if (flags[mid] == 'b') { // blue pebble
				temp = flags[low];
				flags[low] = flags[mid];
				flags[mid] = temp;
				low++;
				mid++;
			} else if (flags[mid] == 'r') { // red
				mid++;
			} else { // white
				temp = flags[high];
				flags[high] = flags[mid];
				flags[mid] = temp;
				high--;
			}
		}
	}

	void printArray(char flags[]) {
		int i;
		for (i = 0; i < flags.length; i++) {
			System.out.print(flags[i] + " ");
		}
	}

	public static void main(String[] args) {
		
		InterviewQuestions_DutchNationalFlag app = new InterviewQuestions_DutchNationalFlag();
		
		char flags[] = { 'r', 'w', 'w', 'b', 'b', 'b', 'w', 'w', 'b', 'r', 'r', 'b' };
		app.sort(flags);
		System.out.println("Array organized by color: ");
		app.printArray(flags);
	}
}
