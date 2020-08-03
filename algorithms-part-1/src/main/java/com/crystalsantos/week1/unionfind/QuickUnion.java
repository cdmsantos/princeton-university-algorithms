package com.crystalsantos.week1.unionfind;

/*
 * Algorithm: Quick Union
 * 
 * Time Complexity:
 * 		Initialize: N
 * 		Union: N (includes cost of finding roots)
 * 		Connected: N
 */
public class QuickUnion extends UnionFind {

	public QuickUnion(int n) {
		super(n);
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

		if(rootP != rootQ) {
			id[rootP] = rootQ;
		}
	}
}
