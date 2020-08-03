package com.crystalsantos.week1.unionfind;

/*
 * Algorithm: Quick Find	
 * 
 * Time Complexity:
 * 		Initialize: N
 * 		Union: N 
 * 		Connected: 1
 */
public class QuickFind extends UnionFind {

	public QuickFind(int n) {
		super(n);
	}

	@Override
	boolean connected(int p, int q) {
		return id[p] == id[q];
	}

	@Override
	int find(int p) {
		return id[p];
	}

	@Override
	void union(int p, int q) {
		int pID = id[p];
		int qID = id[q];

		for (int i = 0; i < id.length; i++) {
			if (id[i] == pID) {
				id[i] = qID;
			}
		}
	}
}
