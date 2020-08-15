package com.crystalsantos.week4.assignment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;

public class Board {
	private final int[] board;
	private final int dimension;

	public Board(int[][] tiles) {
		board = transform(tiles);
		dimension = tiles.length;
	}

	// board dimension n
	public int dimension() {
		return dimension;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.equals(board, other.board))
			return false;
		if (dimension != other.dimension)
			return false;
		return true;
	}

	// number of tiles out of place
	public int hamming() {
		int hamming = 0;
		for (int i = 0; i < board.length; i++) {
			if ((board[i] != (i + 1)) && (board[i] != 0)) {
				hamming++;
			}
		}
		return hamming;
	}

	// is this board the goal board?
	public boolean isGoal() {
		return hamming() == 0;
	}

	// sum of Manhattan distances between tiles and goal
	public int manhattan() {
		int manhattan = 0;

		for (int i = 0; i < board.length; i++) {

			if ((board[i] != (i + 1)) && (board[i] != 0)) {
				int currentRow = i / dimension;
				int currentCol = i % dimension;

				int goalRow = (board[i] - 1) / dimension;
				int goalCol = (board[i] - 1) % dimension;

				manhattan += (Math.abs(currentRow - goalRow) + Math.abs(currentCol - goalCol));
			}
		}
		return manhattan;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		List<Board> neighbors = getNeighbors();
		return neighbors::iterator;
	}

	// string representation of this board
	public String toString() {
		StringBuilder boardToString = new StringBuilder();

		boardToString.append(Integer.toString(dimension) + "\n");

		for (int i = 0; i < board.length; i++) {
			if (isLastColumn(i) && i == board.length - 1) {
				boardToString.append(board[i]);
			} else if (isLastColumn(i)) {
				boardToString.append(board[i] + "\n");
			} else {
				boardToString.append(board[i] + " ");
			}
		}
		return boardToString.toString();
	}

	// a board that is obtained by exchanging any pair of tiles
	public Board twin() {
		int[] twin = Arrays.copyOf(board, board.length);
		int firstIndex = -1;
		for (int i = 0; i < twin.length; i++) {
			if (board[i] != 0 && firstIndex < 0) {
				firstIndex = i;
			} else if (board[i] != 0 && firstIndex >= 0) {
				exchange(firstIndex, i, twin);
				return transformNeighbor(twin);
			}
		}
		return transformNeighbor(twin);
	}

	private int getEmptySpace() {
		int emptySpace = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i] == 0) {
				emptySpace = i;
			}
		}
		return emptySpace;
	}

	private List<Board> getNeighbors() {
		List<Board> neighbors = new ArrayList<>();
		int emptySpace = getEmptySpace();

		int col = emptySpace % dimension;
		int row = emptySpace / dimension;

		if ((col + 1) < dimension) { // direita
			int[] rightNeighbor = Arrays.copyOf(board, board.length);
			exchange(emptySpace, emptySpace + 1, rightNeighbor);
			neighbors.add(transformNeighbor(rightNeighbor));
		}

		if ((col - 1) >= 0) { // esquerda
			int[] leftNeighbor = Arrays.copyOf(board, board.length);
			exchange(emptySpace, emptySpace - 1, leftNeighbor);
			neighbors.add(transformNeighbor(leftNeighbor));
		}

		if ((row - 1) >= 0) { // cima
			int[] upNeighbor = Arrays.copyOf(board, board.length);
			exchange(emptySpace, emptySpace - dimension, upNeighbor);
			neighbors.add(transformNeighbor(upNeighbor));
		}

		if ((row + 1) < dimension) { // baixo
			int[] downNeighbor = Arrays.copyOf(board, board.length);
			exchange(emptySpace, emptySpace + dimension, downNeighbor);
			neighbors.add(transformNeighbor(downNeighbor));
		}

		return neighbors;
	}

	private void exchange(int actualPosition, int finalPosition, int[] board) {
		int temp = board[actualPosition];
		board[actualPosition] = board[finalPosition];
		board[finalPosition] = temp;
	}

	private boolean isLastColumn(int index) {
		return index != 0 && (index + 1) % dimension == 0;
	}

	private int[] transform(int[][] tiles) {
		int[] transformedBoard = new int[tiles.length * tiles.length];
		int count = 0;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				transformedBoard[count++] = tiles[i][j];
			}
		}
		return transformedBoard;
	}

	private Board transformNeighbor(int[] neighbor) {
		int[][] neighborBoard = new int[dimension][dimension];

		int count = 0;
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				neighborBoard[i][j] = neighbor[count++];
			}
		}
		return new Board(neighborBoard);
	}

	public static void main(String[] args) {
		In in = new In("/Users/crystalsantos/Downloads/8puzzle/puzzle04.txt");

		int n = in.readInt();
		int[][] tiles = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tiles[i][j] = in.readInt();
			}
		}
		Board board = new Board(tiles);
		System.out.println("----- ORIGINAL -----");
		System.out.println(board.toString());

		System.out.println("----- VIZINHOS -----");

		for (Board currentBoard : board.neighbors()) {
			for (Board currentCurrentBoard : currentBoard.neighbors()) {
				System.out.println(currentCurrentBoard.toString());
			}

			System.out.println("--------------");
		}

//		int[][] tiles = { { 1, 0, 3 }, { 4, 2, 5 }, { 7, 8, 6 } };
//		Board board = new Board(tiles);
//		System.out.println(board.toString());
//		System.out.println(board.hamming());
//		System.out.println(board.manhattan());
//
//		for (Board currentBoard : board.neighbors()) {
//			System.out.println(currentBoard.toString());
//		}
	}

}