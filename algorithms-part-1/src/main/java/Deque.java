import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private Item[] stack;
	private Item[] temp;
	private int nextIndex;

	// construct an empty deque
	public Deque() {
		nextIndex = 0;
		stack = (Item[]) new Object[1];
	}

	// is the deque empty?
	public boolean isEmpty() {
		return nextIndex == 0;
	}

	// return the number of items on the deque
	public int size() {
		return stack.length;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("You cannot add an empty element into the stack");
		} else if(isEmpty()) {
			addLast(item);
		} else {
			temp = (Item[]) new Object[stack.length+1];
			temp[0] = item;
			for(int i = 0; i < stack.length; i++) {
				temp[i+1] = stack[i];
			}
			stack = temp;
			nextIndex++;
		}
	}

	// add the item to the back
	public void addLast(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("You cannot add an empty element into the stack");
		}
		if (nextIndex == stack.length) {
			resize(2 * stack.length);
		}
		stack[nextIndex++] = item;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		Item removed = null;
		if (isEmpty()) {
			throw new NoSuchElementException("The deque is empty.");
		} else if (stack.length == 1) {
			removeLast();
		} else {
			temp = (Item[]) new Object[stack.length-1];
			removed = stack[0];
			for(int i = 0; i < stack.length-1; i++) {
				temp[i] = stack[i+1];
			}
			stack = temp;
			nextIndex--;
		}
		return removed;
	}

	// remove and return the item from the back
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("The deque is empty.");
		}

		Item item = stack[--nextIndex];
		stack[nextIndex] = null;
		if (nextIndex > 0 && nextIndex == (stack.length / 4)) {
			resize(stack.length / 2);
		}
		return item;
	}

	// return an iterator over items in order from front to back
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			int current = nextIndex;

			@Override
			public boolean hasNext() {
				return current > 0;
			}

			@Override
			public Item next() {
				if (hasNext()) {
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
	}
}
