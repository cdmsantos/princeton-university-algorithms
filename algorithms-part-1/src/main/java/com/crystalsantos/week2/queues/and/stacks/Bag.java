package com.crystalsantos.week2.queues.and.stacks;

public abstract class Bag<Item> implements Iterable<Item> {
	Node<Item> first;
	int numberOfItems;

	public Bag() {
		first = null;
		numberOfItems = 0;
	}

	abstract void add(Item item);

	abstract int size();

	abstract boolean isEmpty();
}
