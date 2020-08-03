package com.crystalsantos.week2.queues.and.stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class StackWithArray<Item> extends Stack<Item> {
	private Item[] stack;
	private int nextIndex = 0;

	public StackWithArray() {
		stack = (Item[]) new Object[1];
	}

	@Override
	public boolean isEmpty() {
		return nextIndex == 0;
	}

	@Override
	public void push(Item item) {
		if (nextIndex == stack.length) {
			resize(2 * stack.length);
		}
		stack[nextIndex++] = item;
	}

	@Override
	public Item pop() {
		Item item = stack[--nextIndex];
		stack[nextIndex] = null;
		if (nextIndex > 0 && nextIndex == (stack.length / 4)) {
			resize(stack.length / 2);
		}
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			int current = nextIndex;
			
			@Override
			public boolean hasNext() {
				return current > 0;
			}

			@Override
			public Item next() {
				if(hasNext()) {
					return stack[--current];
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
			copy[i] = stack[i];
		}
		
		stack = copy;
	}
}