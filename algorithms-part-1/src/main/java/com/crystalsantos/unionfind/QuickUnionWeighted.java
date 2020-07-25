package com.crystalsantos.unionfind;

/*
 * Algorithm: Quick Union Weighted
 * 
 * Time Complexity:
 * 		Initialize: N
 * 		Union: logN (includes cost of finding roots)
 * 		Connected: logN
 */
public class QuickUnionWeighted extends UnionFind {

	private int[] size;

	public QuickUnionWeighted(int n) {
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
}
