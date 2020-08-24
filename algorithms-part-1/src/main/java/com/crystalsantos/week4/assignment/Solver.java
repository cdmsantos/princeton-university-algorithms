package com.crystalsantos.week4.assignment;

import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	private final MinPQ<Node> priorityQueue;
	private Node lastMove;
	private int moves = Integer.MIN_VALUE;

	private class Node implements Comparable<Node> {
		Board board;
		Node previous;
		int moves = 0;

		public Node(Board board) {
			this.board = board;
			previous = null;
		}

		public Node(Board board, Node previous) {
			this.board = board;
			this.previous = previous;
			this.moves = previous.moves + 1;
		}

		@Override
		public int compareTo(Node node) {
			return (this.board.manhattan() - node.board.manhattan()) + (this.moves - node.moves);
		}
	}

	public Solver(Board initial) {
		if (initial == null) {
			throw new IllegalArgumentException("The initial board must no be null");
		}
		priorityQueue = new MinPQ<Node>();
		priorityQueue.insert(new Node(initial));

		play();
	}

	public boolean isSolvable() {
		return moves != Integer.MIN_VALUE ? true : false;
	}

	public int moves() {
		return moves;
	}

	public Iterable<Board> solution() {
		if (isSolvable()) {
			Stack<Board> allMoves = new Stack<Board>();
			while (lastMove != null) {
				allMoves.push(lastMove.board);
				lastMove = lastMove.previous;
			}

			return allMoves;
		} else {
			return null;
		}
	}

	private void play() {
		try {
			Node currentNode = priorityQueue.delMin();
			while (!currentNode.board.isGoal()) {
				for (Board neighbor : currentNode.board.neighbors()) {
					if (currentNode.previous == null || !neighbor.equals(currentNode.previous.board)) {
						priorityQueue.insert(new Node(neighbor, currentNode));
					}
				}
				currentNode = priorityQueue.delMin();
			}
			lastMove = currentNode;
			moves = currentNode.moves;
		} catch (NoSuchElementException e) {
			moves = -1;
			lastMove = null;
		}
	}

	public static void main(String[] args) {

		In in = new In("/Users/crystalsantos/Downloads/8puzzle/puzzle01.txt");
		int dimension = in.readInt();

		int[][] tiles = new int[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				tiles[i][j] = in.readInt();
			}
		}

		Board initial = new Board(tiles);

		Solver solver = new Solver(initial);

		if (!solver.isSolvable()) {
			StdOut.println("No solution possible");
		} else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution()) {
				StdOut.println(board);
			}
		}
	}
}