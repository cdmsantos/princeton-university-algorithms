package com.crystalsantos.week3.mergesort;

import com.crystalsantos.week2.queues.and.stacks.StackWithArray;

import edu.princeton.cs.algs4.StdRandom;

/*
 * Given a singly-linked list containing n items, rearrange the items 
 * uniformly at random. Your algorithm should consume a logarithmic 
 * (or constant) amount of extra memory and run in time proportional 
 * to nlogn in the worst case.
 * */
public class InterviewQuestions_ShufflingLinkedList {

	void shuffle(StackWithArray<Integer> original) {

		if (original.iterator().hasNext() == true) {
			StackWithArray<Integer> mid1 = new StackWithArray<Integer>();
			StackWithArray<Integer> mid2 = new StackWithArray<Integer>();
			while (!original.isEmpty()) {
				mid1.push(original.pop());
				if (!original.isEmpty()) {
					mid2.push(original.pop());
				}
			}

			shuffle(mid1);
			shuffle(mid2);

			int random = -1;
			if (mid2.size() < mid1.size()) {
				StdRandom.uniform(0, mid2.size());
				mid2.push(random);
			}

			while (!mid1.isEmpty() && !mid2.isEmpty()) {
				if (StdRandom.uniform(2) == 0) {
					original.push(mid1.pop());
				} else {
					original.push(mid2.pop());
				}
			}

			if (!mid1.isEmpty()) {
				while (!mid1.isEmpty()) {
					original.push(mid1.pop());
				}
			}
			if (!mid2.isEmpty()) {
				while (!mid2.isEmpty()) {
					original.push(mid2.pop());
				}
			}
			
			if(random > -1) {
				original.pop();
			}
		}
	}

	public static void main(String[] args) {
		StackWithArray<Integer> original = new StackWithArray<Integer>();
		original.push(8);
		original.push(3);
		original.push(10);
		original.push(1);
		original.push(0);
		original.push(22);
		original.push(19);
		original.push(2);

		InterviewQuestions_ShufflingLinkedList app = new InterviewQuestions_ShufflingLinkedList();
		app.shuffle(original);
	}
}
