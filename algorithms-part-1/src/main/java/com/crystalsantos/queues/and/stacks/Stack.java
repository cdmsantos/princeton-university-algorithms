package com.crystalsantos.queues.and.stacks;

public abstract class Stack<Item> implements Iterable<Item> {

	abstract boolean isEmpty();

	abstract void push(Item item);

	abstract Item pop();
}
