package com.crystalsantos.unionfind;

/*
 * Algorithm: Quick Union Weighted + Path Compression
 * 
 * Time Complexity:
 * 		Initialize: N
 * 		Union: N + MlogN
 * 		Connected: N + MlogN
 */
public class QuickUnionWeightedPathCompression extends UnionFind {

	private int[] size;

	public QuickUnionWeightedPathCompression(int n) {
		super(n);
		setInitialSizes(n);
	}

	@Override
	boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	@Override
	int find(int p) {
		while (p != id[p]) {
			id[p] = id[id[p]];
			p = id[p];
		}
		return p;
	}

	@Override
	void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);

		if (rootP == rootQ) return;
		
		if (size[rootP] < size[rootQ]) {
			id[rootP] = rootQ;
			size[rootQ] += size[rootP];
		} else {
			id[rootQ] = rootP;
			size[rootP] += size[rootQ];
		}
		
	}

	private void setInitialSizes(int n) {
		size = new int[n];
		for (int i = 0; i < n; i++) {
			size[i] = 1;
		}
	}

	private static void print(int[] size) {
		for (int i = 0; i < size.length; i++) { 
            System.out.print(size[i] + " "); 
        }
		System.out.println();
	}
	
	public static void main(String[] args) {
		QuickUnionWeightedPathCompression app = new QuickUnionWeightedPathCompression(13);

		app.union(0, 2);
		print(app.id);
		
		app.union(3, 7);
		print(app.id);

		app.union(1, 4);
		print(app.id);

		app.union(1, 5);
		print(app.id);
		
		app.union(8, 10);
		print(app.id);
		
		app.union(9, 11);
		print(app.id);
		
		app.union(9, 12);
		print(app.id);
		
		app.union(6, 8);
		print(app.id);
		
		app.union(6, 9);
		print(app.id);

		app.union(3, 6);
		print(app.id);

		app.union(0, 1);
		print(app.id);

		app.union(1, 3);
		print(app.id);
		
		int find = app.find(10);
		System.out.println("\nroot = " +find);
	}
}
