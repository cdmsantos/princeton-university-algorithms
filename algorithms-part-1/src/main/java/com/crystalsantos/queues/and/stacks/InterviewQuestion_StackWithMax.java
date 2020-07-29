package com.crystalsantos.queues.and.stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Create a data structure that efficiently supports the stack operations 
 * (push and pop) and also a return-the-maximum operation. Assume the elements 
 * are real numbers so that you can compare them.
 * */
public class InterviewQuestion_StackWithMax implements Iterable<Integer> {
	Node<Integer> first;
	Integer maxValue;

	InterviewQuestion_StackWithMax() {
		first = null;
		maxValue = -2147483648;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public Integer getMaximum() {
		return maxValue;
	}

	public void push(Integer item) {
		Node<Integer> oldFirst = first;
		first = new Node<Integer>();
		first.item = item;
		first.next = oldFirst;

		if (Integer.compare(item, maxValue) > 0) {
			maxValue = item;
		}
	}

	public Integer pop() {
		Integer item = first.item;
		first = first.next;

		if (item == maxValue) {
			searchNewMax();
		}
		return item;
	}

	void searchNewMax() {
		if (!isEmpty()) {
			Node<Integer> temp = first;
			Integer maxTemp = temp.item;
			while (temp.next != null) {
				if (temp.item > maxTemp) {
					maxTemp = temp.item;
				}
				temp = temp.next;
			}
			maxValue = maxTemp;
		} else {
			maxValue = null;
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			Node<Integer> current = first;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Integer next() {
				if (hasNext()) {
					Integer item = current.item;
					current = current.next;
					return item;
				} else {
					throw new NoSuchElementException();
				}
			}
		};
	}

	public static void main(String[] args) {
		InterviewQuestion_StackWithMax stack = new InterviewQuestion_StackWithMax();
		stack.push(4);
		System.out.println(stack.getMaximum());
		stack.push(14);
		System.out.println(stack.getMaximum());
		stack.push(7);
		System.out.println(stack.getMaximum());
		stack.push(19);
		System.out.println(stack.getMaximum());
		stack.push(20);
		System.out.println(stack.getMaximum());
		stack.pop();
		System.out.println("new max = " + stack.getMaximum());
		stack.pop();
		System.out.println("new max = " + stack.getMaximum());
	}
}
