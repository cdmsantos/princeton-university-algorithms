package com.crystalsantos.week2.queues.and.stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BagWithLinkedList<Item> extends Bag<Item> {

	public BagWithLinkedList() {
		super();
	}

	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			Node<Item> current = first;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Item next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}

				Item item = current.item;
				current = current.next;
				return item;
			}

		};
	}

	@Override
	void add(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		numberOfItems++;
	}

	@Override
	int size() {
		return numberOfItems;
	}

	@Override
	boolean isEmpty() {
		return first == null;
	}

}
