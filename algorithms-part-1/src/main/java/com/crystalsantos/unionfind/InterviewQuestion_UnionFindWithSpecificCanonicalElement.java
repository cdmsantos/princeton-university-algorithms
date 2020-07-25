package com.crystalsantos.unionfind;

/*
 * Add a method find() to the union-find data type so that find(i) returns the largest element in 
 * the connected component containing i. The operations, union(), connected(), and find() should 
 * all take logarithmic time or better.
 * 
 * For example, if one of the connected components is {1,2,6,9}, then the find() method should 
 * return 9 for each of the four elements in the connected components.
 * 
 * */
public class InterviewQuestion_UnionFindWithSpecificCanonicalElement {

	private int[] biggest;
	private int[] size;
	private int[] id;
	
	public InterviewQuestion_UnionFindWithSpecificCanonicalElement(int n) {
		biggest = new int[n];
		size = new int[n];
		id = new int[n];
		setArrays(n);
	}

	boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	int find(int p) {
		return biggest[root(p)];
	}
	
	int root(int p) {
		while (p != id[p]) {
			id[p] = id[id[p]];
			p = id[p];
		}
		return p;
	}

	void union(int p, int q) {
		int rootP = root(p);
		int rootQ = root(q);
		
		int biggestP = biggest[rootP];
		int biggestQ = biggest[rootQ];

		if (rootP == rootQ) return;
		
		if (size[rootP] < size[rootQ]) {
			id[rootP] = rootQ;
			size[rootQ] += size[rootP];
			
			if(biggestP > biggestQ) {
				biggest[rootQ] = biggestP;
			}
		} else {
			id[rootQ] = rootP;
			size[rootP] += size[rootQ];
			
			if(biggestQ > biggestP) {
				biggest[rootP] = biggestQ;
			}
		}
	}
	
	private void setArrays(int n) {
		size = new int[n];
		biggest = new int[n];

		for (int i = 0; i < n; i++) {
			size[i] = 1;
			biggest[i] = i;
			id[i] = i;
		}
	}
	
	public static void main(String[] args) {
		InterviewQuestion_UnionFindWithSpecificCanonicalElement app 
			= new InterviewQuestion_UnionFindWithSpecificCanonicalElement(15);
		
		app.union(1, 2);
		app.union(6, 2);
		app.union(6, 9);
		app.union(6, 12);

		//it should print 12 because it's the biggest value with union
		System.out.println(app.find(6)); 
	}

}
