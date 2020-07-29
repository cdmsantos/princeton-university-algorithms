package com.crystalsantos.queues.and.stacks;

public abstract class Queue<Item> implements Iterable<Item> {
	Node<Item> first;
	Node<Item> last;
	int numberOfItens;

	public Queue() {
		first = null;
		last = null;
		numberOfItens = 0;
	}

	abstract void enqueue(Item item);

	abstract Item dequeue();

	abstract boolean isEmpty();
	
	abstract int size();
	
	abstract Item peek();
	
	public abstract String toString();
}
