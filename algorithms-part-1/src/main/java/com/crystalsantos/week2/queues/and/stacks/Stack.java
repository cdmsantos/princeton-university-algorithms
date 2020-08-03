package com.crystalsantos.week2.queues.and.stacks;

public abstract class Stack<Item> implements Iterable<Item> {

	abstract boolean isEmpty();

	abstract void push(Item item);

	abstract Item pop();
}
