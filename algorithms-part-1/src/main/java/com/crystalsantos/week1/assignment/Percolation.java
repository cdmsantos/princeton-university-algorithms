package com.crystalsantos.week1.assignment;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int openSites;
	private boolean[][] site;
	final private WeightedQuickUnionUF quickUnion;
	final private int VIRTUAL_BOTTOM;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		openSites = 0;
		quickUnion = new WeightedQuickUnionUF(n * n + 2);
		site = new boolean[n][n];
		VIRTUAL_BOTTOM = n * n + 1;
	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col) {
		if (!isOpen(row, col)) {
			row--;
			col--;
			site[row][col] = true;

			int transformed = transform(row, col);

			unionNeighbors(transformed, row - 1, col);
			unionNeighbors(transformed, row + 1, col);

			unionNeighbors(transformed, row, col - 1);
			unionNeighbors(transformed, row, col + 1);

			openSites++;

			if (row == 0) {
				quickUnion.union(transformed, 0);
			} else if (row == site.length - 1) {
				quickUnion.union(transformed, VIRTUAL_BOTTOM);
			}
		}
	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		row--;
		col--;
		checkRange(row, col);

		return site[row][col];
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) {
		row--;
		col--;
		checkRange(row, col);

		return quickUnion.find(transform(row, col)) == quickUnion.find(0);
	}

	// returns the number of open sites
	public int numberOfOpenSites() {
		return openSites;
	}

	// does the system percolate?
	public boolean percolates() {
		if (site.length == 1) {
			return isOpen(1, 1);
		} else {
			return quickUnion.find(VIRTUAL_BOTTOM) == quickUnion.find(0);
		}
	}

	private void unionNeighbors(int transformed, int row, int col) {
		if (canJoin(row) && canJoin(col) && site[row][col]) {
			quickUnion.union(transformed, transform(row, col));
		}
	}

	private int transform(int row, int col) {
		checkRange(row, col);
		return 1 + col + (row * site.length);
	}

	private boolean canJoin(int element) {
		if (element >= 0 && element < site.length) {
			return true;
		} else {
			return false;
		}
	}

	private void checkRange(int row, int col) {
		if (row < 0 || row >= site.length || col < 0 || col >= site.length) {
			throw new IllegalArgumentException();
		}
	}

//	private void printSites() {
//		for (int i = 0; i < site.length; i++) {
//			for (int j = 0; j < site.length; j++) {
//				System.out.print(site[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
//
//	// test client (optional)
//	public static void main(String[] args) {
//		Percolation percolation = new Percolation(5);
//
////		percolation.open(5,11);
////		System.out.println(percolation.isFull(1, 1));
//		percolation.open(3, 1);
//		percolation.open(3, 4);
//		percolation.open(2, 3);
//		percolation.open(2, 4);
//		percolation.open(1, 3);
//		percolation.open(4, 4);
//		percolation.open(4, 5);
//		percolation.open(5, 4);
//		percolation.printSites();
//		System.out.println(percolation.percolates());
//	}
}