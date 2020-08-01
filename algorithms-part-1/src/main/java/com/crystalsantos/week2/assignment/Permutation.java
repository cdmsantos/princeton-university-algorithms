package com.crystalsantos.week2.assignment;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		RandomizedQueue<String> queue = new RandomizedQueue<String>();

		while (!StdIn.isEmpty()) {
			queue.enqueue(StdIn.readString());
		}

		for (int i = 0; i < size; i++) {
			System.out.println(queue.dequeue());
		}
	}
}
