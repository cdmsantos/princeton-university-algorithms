package com.crystalsantos.week5.searchtrees.balanced;

import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root;

	private class Node {
		private boolean color;
		private Key key;
		private Value value;
		private Node left, right;
		private int size;

		public Node(Key key, Value value, boolean color, int size) {
			this.key = key;
			this.value = value;
			this.color = color;
			this.size = size;
		}
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}

		if (!contains(key)) {
			return;
		}

		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}

		root = delete(root, key);
		if (!isEmpty()) {
			root.color = BLACK;
		}
	}

	public void deleteMax() {
		if (isEmpty()) {
			throw new NoSuchElementException("BST underflow");
		}

		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}

		root = deleteMax(root);
		if (!isEmpty()) {
			root.color = BLACK;
		}
	}

	public void deleteMin() {
		if (isEmpty()) {
			throw new NoSuchElementException("BST underflow");
		}

		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}

		root = deleteMin(root);
		if (!isEmpty()) {
			root.color = BLACK;
		}
	}

	public Value get(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("Argument to get() is null");
		}
		return get(root, key);
	}

	public int height() {
		return height(root);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public Key max() {
		if (isEmpty()) {
			throw new NoSuchElementException("calls max() with empty symbol table");
		}
		return max(root).key;
	}

	public Key min() {
		if (isEmpty()) {
			throw new NoSuchElementException("calls min() with empty symbol table");
		}
		return min(root).key;
	}

	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("The first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}

		root = put(root, key, val);
		root.color = BLACK;
	}

	public int size() {
		return size(root);
	}

	private Node balance(Node node) {
		if (isRed(node.right)) {
			node = rotateLeft(node);
		}
		if (isRed(node.left) && isRed(node.left.left)) {
			node = rotateRight(node);
		}
		if (isRed(node.left) && isRed(node.right)) {
			flipColors(node);
		}

		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}

	private Node delete(Node node, Key key) {

		if (key.compareTo(node.key) < 0) {
			if (!isRed(node.left) && !isRed(node.left.left)) {
				node = moveRedLeft(node);
			}
			node.left = delete(node.left, key);
		} else {
			if (isRed(node.left)) {
				node = rotateRight(node);
			}

			if (key.compareTo(node.key) == 0 && (node.right == null)) {
				return null;
			}

			if (!isRed(node.right) && !isRed(node.right.left)) {
				node = moveRedRight(node);
			}

			if (key.compareTo(node.key) == 0) {
				Node x = min(node.right);
				node.key = x.key;
				node.value = x.value;
				node.right = deleteMin(node.right);
			} else
				node.right = delete(node.right, key);
		}
		return balance(node);
	}

	private Node deleteMax(Node node) {
		if (isRed(node.left)) {
			node = rotateRight(node);
		}
		
		if (node.right == null) {
			return null;
		}

		if (!isRed(node.right) && !isRed(node.right.left)) {
			node = moveRedRight(node);
		}

		node.right = deleteMax(node.right);

		return balance(node);
	}

	private Node deleteMin(Node node) {
		if (node.left == null) {
			return null;
		}

		if (!isRed(node.left) && !isRed(node.left.left)) {
			node = moveRedLeft(node);
		}

		node.left = deleteMin(node.left);
		return balance(node);
	}

	private void flipColors(Node node) {
		node.color = !node.color;
		node.left.color = !node.left.color;
		node.right.color = !node.right.color;
	}

	private Value get(Node node, Key key) {
		while (node != null) {
			int cmp = key.compareTo(node.key);
			if (cmp < 0) {
				node = node.left;
			} else if (cmp > 0) {
				node = node.right;
			} else {
				return node.value;
			}
		}
		return null;
	}

	private int height(Node node) {
		if (node == null) {
			return -1;
		}
		return 1 + Math.max(height(node.left), height(node.right));
	}

	private boolean isRed(Node node) {
		if (node == null) {
			return false; // null links are always black
		} else {
			return node.color == true;
		}
	}

	private Node moveRedLeft(Node node) {
		flipColors(node);
		if (isRed(node.right.left)) {
			node.right = rotateRight(node.right);
			node = rotateLeft(node);
			flipColors(node);
		}
		return node;
	}

	private Node moveRedRight(Node node) {
		flipColors(node);
		if (isRed(node.left.left)) {
			node = rotateRight(node);
			flipColors(node);
		}
		return node;
	}

	private Node max(Node node) {
		if (node.right == null) {
			return node;
		} else {
			return max(node.right);
		}
	}

	private Node min(Node node) {
		if (node.left == null) {
			return node;
		} else {
			return min(node.left);
		}
	}

	private Node put(Node node, Key key, Value value) {
		if (node == null) {
			return new Node(key, value, RED, 1);
		}

		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			node.left = put(node.left, key, value);
		} else if (cmp > 0) {
			node.right = put(node.right, key, value);
		} else {
			node.value = value;
		}

		if (isRed(node.right) && !isRed(node.left)) {
			node = rotateLeft(node);
		}

		if (isRed(node.left) && isRed(node.left.left)) {
			node = rotateRight(node);
		}

		if (isRed(node.left) && isRed(node.right)) {
			flipColors(node);
		}

		node.size = size(node.left) + size(node.right) + 1;

		return node;
	}

	private Node rotateLeft(Node node) {
		Node right = node.right;
		node.right = right.left;
		right.left = node;
		right.color = right.left.color;
		right.left.color = RED;
		right.size = node.size;
		node.size = size(node.left) + size(node.right) + 1;
		return right;
	}

	private Node rotateRight(Node node) {
		Node left = node.left;
		node.left = left.right;
		left.right = node;
		left.color = left.right.color;
		left.right.color = RED;
		left.size = node.size;
		node.size = size(node.left) + size(node.right) + 1;
		return left;
	}

	private int size(Node node) {
		if (node == null) {
			return 0;
		}
		return node.size;
	}
}
