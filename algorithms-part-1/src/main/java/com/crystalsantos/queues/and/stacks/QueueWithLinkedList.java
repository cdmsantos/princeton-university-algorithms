package com.crystalsantos.queues.and.stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueWithLinkedList<Item> extends Queue<Item> {

	QueueWithLinkedList() {
		super();
	}

	@Override
	void enqueue(Item item) {
		Node<Item> oldLast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		if (isEmpty()) {
			first = last;
		} else {
			oldLast.next = last;
		}

		numberOfItens++;
	}

	@Override
	Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue underflow");
		}

		Item item = first.item;
		first = first.next;
		numberOfItens--;

		if (isEmpty()) {
			last = null;
		}
		return item;
	}

	@Override
	boolean isEmpty() {
		return first == null;
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
				if (hasNext()) {
					Item item = current.item;
					current = current.next;
					return item;
				} else {
					throw new NoSuchElementException("There is no element");
				}
			}
		};
	}

	@Override
	public int size() {
		return numberOfItens;
	}

	@Override
	public Item peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue underflow");
		}
		return first.item;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this) {
			s.append(item);
			s.append(' ');
		}
		return s.toString();
	}
}
