package com.crystalsantos.week2.assignment;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] items;
	private int nextIndex;

	// construct an empty randomized queue
	public RandomizedQueue() {
		items = (Item[]) new Object[1];
		nextIndex = 0;
	}

	// is the randomized queue empty?
	public boolean isEmpty() {
		return nextIndex == 0;
	}

	// return the number of items on the randomized queue
	public int size() {
		return nextIndex;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("You cannot add an empty element into the stack");
		} else if (nextIndex == items.length) {
			resize(2 * items.length);
		}
		items[nextIndex++] = item;
	}

	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("The deque is empty.");
		}

		Item item = dequeue(items, nextIndex);
		nextIndex--;
		if (nextIndex > 0 && nextIndex == (items.length / 4)) {
			resize(items.length / 2);
		}
		return item;
	}

	// return a random item (but do not remove it)
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException("The deque is empty.");
		}

		int random = StdRandom.uniform(nextIndex);
		return items[random];
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			int tempSize = nextIndex;
			private Item[] tempItems = copyItems(items);

			@Override
			public boolean hasNext() {
				return tempSize > 0;
			}

			@Override
			public Item next() {
				if (hasNext()) {
					Item item = dequeue(tempItems, tempSize);
					tempSize--;
					return item;
				} else {
					throw new NoSuchElementException();
				}
			}
		};
	}

	// when we perform N operations, the resize method is called logN times.
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < nextIndex; i++) {
			copy[i] = items[i];
		}

		items = copy;
	}
	
	private Item dequeue(Item[] itemsToBeDequeued, int size) {
		int random = StdRandom.uniform(size);
		Item item = itemsToBeDequeued[random];

		if (random < (size - 1)) { // in the middle of the stack
			itemsToBeDequeued[random] = itemsToBeDequeued[size - 1];
			itemsToBeDequeued[size - 1] = null;
		} else {
			itemsToBeDequeued[random] = null;
		}
		
		return item;
	}
	
	private Item[] copyItems(Item items[]) {
		Item newItems[] = (Item[]) new Object[items.length];
		for(int i = 0; i < items.length; i++) {
			newItems[i] = items[i];
		}
		
		return newItems;
	}

	// unit testing (required)
	public static void main(String[] args) {
		RandomizedQueue<Integer> app = new RandomizedQueue<Integer>();
		System.out.println("Is empty? " + app.isEmpty());
		app.enqueue(0);
		app.enqueue(1);
		app.enqueue(2);
		app.enqueue(3);
		app.enqueue(4);
		app.enqueue(5);
		app.enqueue(6);
		System.out.println("Size = " + app.size());
		System.out.println("Sample = " + app.sample());
		System.out.println("Sample = " + app.sample());
		System.out.println("Final queue:");
		Iterator<Integer> itr = app.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next() + " ");
		}
		System.out.println();
		System.out.println("Item removed = " + app.dequeue());
		System.out.println("Size = " + app.size());
		System.out.println("Sample = " + app.sample());
		System.out.println("Final queue:");
		Iterator<Integer> itr2 = app.iterator();
		while (itr2.hasNext()) {
			System.out.print(itr2.next() + " ");
		}
		System.out.println();
	}
}