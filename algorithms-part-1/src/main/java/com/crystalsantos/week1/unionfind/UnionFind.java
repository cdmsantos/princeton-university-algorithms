package com.crystalsantos.week1.unionfind;

public abstract class UnionFind {

	int[] id;

	public UnionFind(int n) {
		super();

		id = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
	}

	abstract boolean connected(int p, int q);

	abstract int find(int p);
	
	abstract void union(int p, int q);
}
