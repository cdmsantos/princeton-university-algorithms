package com.crystalsantos.week2.queues.and.stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackWithLinkedList<Item> extends Stack<Item> {
	Node<Item> first;

	public StackWithLinkedList() {
		first = null;
	}
	
	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public void push(Item item) {
		Node<Item> oldFirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldFirst;
	}

	@Override
	public Item pop() {
		Item item = first.item;
		first = first.next;
		return item;
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
				if(hasNext()) {
					Item item = current.item;
					current = current.next;
					return item;
				} else {
					throw new NoSuchElementException();
				}
			}
		};
	}

}
