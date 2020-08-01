package com.crystalsantos.week2.assignment;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private class Node<Item> {
		Item item;
		Node<Item> next;
		Node<Item> previous;

		public Node(Item item) {
			this.item = item;
		}
	}

	private Node<Item> first;
	private Node<Item> last;
	private int size;

	// construct an empty deque
	public Deque() {
		size = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the deque
	public int size() {
		return size;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("You cannot add an empty element into the stack");
		} else if (isEmpty()) {
			first = new Node<Item>(item);
			last = first;
		} else {
			Node<Item> newFirst = new Node<Item>(item);
			newFirst.next = first;
			first.previous = newFirst;
			first = newFirst;
		}
		size++;
	}

	// add the item to the back
	public void addLast(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("You cannot add an empty element into the stack");
		} else if (isEmpty()) {
			first = new Node<Item>(item);
			last = first;
		} else {
			Node<Item> newLast = new Node<Item>(item);
			newLast.previous = last;
			last.next = newLast;
			last = newLast;
		}

		size++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("The deque is empty.");
		}

		Item removed = first.item;
		size--;
		if (isEmpty()) {
			first = last = null;
		} else {
			Node<Item> temp = first.next;
			temp.previous = null;
			first = temp;
		}
		return removed;
	}

	// remove and return the item from the back
	public Item removeLast() {
		Item removed = null;
		if (isEmpty()) {
			throw new NoSuchElementException("The deque is empty.");
		} else if (size == 1) {
			removed = first.item;
			size--;
			first = last = null;
		} else {
			removed = last.item;
			Node<Item> temp = last.previous;
			temp.next = null;
			last = temp;
			size--;
		}
		return removed;
	}

	// return an iterator over items in order from front to back
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

	// unit testing (required)
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		System.out.println(deque.isEmpty());
		deque.addFirst(2);
		deque.addLast(3);
		deque.addLast(4);
		deque.addLast(5);
		deque.addFirst(1);
		deque.addFirst(0);
		System.out.println(deque.isEmpty());
		System.out.println(deque.size());
		System.out.println(deque.removeFirst());
		System.out.println(deque.removeLast());

		System.out.println("Final queue:");
		Iterator<Integer> itr = deque.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next() + " ");
		}
	}
}
