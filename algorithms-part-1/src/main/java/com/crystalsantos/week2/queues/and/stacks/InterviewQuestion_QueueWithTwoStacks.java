package com.crystalsantos.week2.queues.and.stacks;

import java.util.NoSuchElementException;

/*
 * Implement a queue with two stacks so that each 
 * queue operations takes a constant amortized number of stack operations.
 * */
public class InterviewQuestion_QueueWithTwoStacks {

	StackWithLinkedList<Integer> stack1;
	StackWithLinkedList<Integer> stack2;

	InterviewQuestion_QueueWithTwoStacks() {
		stack1 = new StackWithLinkedList<>();
		stack2 = new StackWithLinkedList<>();
	}

	boolean isEmpty() {
		return stack1.isEmpty();
	}

	void enqueue(Integer item) {
		stack1.push(item);
	}

	Integer dequeue() {
		if (stack1.isEmpty()) {
			throw new NoSuchElementException();
		}

		Integer dequeued = 0;
		while (stack1.iterator().hasNext()) {
			stack2.push(stack1.pop());
		}
		dequeued = stack2.pop();

		while (stack2.iterator().hasNext()) {
			stack1.push(stack2.pop());
		}

		return dequeued;
	}

	public static void main(String[] args) {
		InterviewQuestion_QueueWithTwoStacks app = new InterviewQuestion_QueueWithTwoStacks();

		app.enqueue(1);
		app.enqueue(2);
		app.enqueue(3);
		app.enqueue(4);
		app.enqueue(5);
		System.out.println(app.dequeue());
	}
}
