package com.crystalsantos.week4.elementarytables;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

	private Node root;

	private class Node {

		private int size;
		private Key key;
		private Value value;
		private Node left, right;

		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
			size = 0;
		}
	}

	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("Argument to contains() is null");
		}
		return get(key) != null;
	}

	public void delete(Key key) {
		root = delete(key, root);
	}

	private Node delete(Key key, Node node) {
		if (node == null) {
			return node;
		}

		int compare = key.compareTo(node.key);
		if (compare < 0) {
			node.left = delete(key, node.left);
		} else if (compare > 0) {
			node.right = delete(key, node.right);
		} else {
			if (node.right == null) {
				return node.left;
			}

			if (node.left == null) {
				return node.right;
			}

			Node temp = node;
			node = min(temp.right);
			node.right = deleteMin(temp.right);
			node.left = temp.left;
		}

		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node node) {
		if (node.left == null) {
			return node.right;
		}

		node.left = deleteMin(node.left);
		node.size = 1 + size(node.left) + size(node.right);

		return node;
	}

	public Key floor(Key key) {
		Key floorKey = null;
		Node searchedNode = floor(root, key);

		if (searchedNode != null) {
			floorKey = searchedNode.key;
		}

		return floorKey;
	}

	private Node floor(Node node, Key key) {
		Node floodNode = null;

		if (node != null) {
			int compare = key.compareTo(node.key);
			if (compare < 0) {
				floodNode = floor(node.left, key);
			} else if (compare == 0) {
				floodNode = node;
			}

			Node rightNode = floor(node.right, key);
			if (rightNode != null) {
				floodNode = rightNode;
			} else {
				floodNode = node;
			}
		}

		return floodNode;
	}

	public Value get(Key key) {
		return get(key, root);
	}

	public Value get(Key key, Node node) {
		Value valueReturned = null;

		if (key == null) {
			throw new IllegalArgumentException("Calls get() with a null key");
		}

		while (node != null) {
			int compare = key.compareTo(node.key);
			if (compare < 0) {
				return get(key, node.left);
			} else if (compare > 0) {
				return get(key, node.right);
			} else {
				valueReturned = node.value;
			}
		}

		return valueReturned;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Iterable<Key> keys() {
		if (isEmpty()) {
			return new Queue<Key>();
		}
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key low, Key high) {
		if (low == null) {
			throw new IllegalArgumentException("First argument to keys() is null");
		}

		if (high == null) {
			throw new IllegalArgumentException("Second argument to keys() is null");
		}

		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, low, high);
		return queue;
	}

	private void keys(Node node, Queue<Key> queue, Key low, Key high) {
		if (node != null) {
			int compareLow = low.compareTo(node.key);
			int compareHigh = high.compareTo(node.key);
			if (compareLow < 0) {
				keys(node.left, queue, low, high);
			}
			if (compareLow <= 0 && compareHigh >= 0) {
				queue.enqueue(node.key);
			}
			if (compareHigh > 0) {
				keys(node.right, queue, low, high);
			}
		}
	}

	public Key max() {
		if (isEmpty()) {
			throw new NoSuchElementException("Calls max() with empty symbol table");
		}
		return max(root).key;
	}

	private Node max(Node node) {
		if (node.right == null) {
			return node;
		} else {
			return max(node.right);
		}
	}

	public Key min() {
		if (isEmpty()) {
			throw new NoSuchElementException("Calls min() with empty symbol table");
		}
		return min(root).key;
	}

	private Node min(Node node) {
		if (node.left == null) {
			return node;
		} else {
			return min(node.left);
		}
	}

	public void put(Key key, Value value) {
		if (key == null) {
			throw new IllegalArgumentException("Calls put() with a null key");
		}

		if (value == null) {
			delete(key);
		} else {
			root = put(root, key, value);
		}
	}

	private Node put(Node node, Key key, Value value) {
		if (node == null) {
			node = new Node(key, value);
		} else {
			int compare = key.compareTo(node.key);
			if (compare < 0) {
				node.left = put(node.left, key, value);
			} else if (compare > 0) {
				node.right = put(node.right, key, value);
			} else {
				node.value = value; // already in the tree, so update the value
			}
		}
		node.size = 1 + size(node.left) + size(node.right);

		return node;
	}

	public int rank(Key key) {
		return rank(key, root);
	}

	private int rank(Key key, Node node) {
		int rank = 0;

		if (node != null) {
			int compare = key.compareTo(node.key);
			if (compare < 0) {
				return rank(key, node.left);
			} else if (compare > 0) {
				return 1 + size(node.left) + rank(key, node.right);
			} else {
				rank = size(node.left);
			}
		}

		return rank;
	}

	public int size() {
		return size(root);
	}

	private int size(Node node) {
		int size = 0;
		if (node != null) {
			size = node.size;
		}
		return size;
	}
}
