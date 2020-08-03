package com.crystalsantos.week1.unionfind;

/*
 * Given a set of n integers S = { 0, 1, ... , n-1 } and a sequence of requests of the following form:
 * Remove x from S
 * Find the successor of x: the smallest y in S such that y >= x. D
 * Design a data type so that all operations (except construction) take logarithmic time or better in 
 * the worst case.
 * 
 * 
 * Hint: This problem can be a variation of the "Union Find With Specific Canonical Element" problem. 
 * Say for all the integers with same successor are connected. So we have an array "connected" of 
 * length n to indicate the set of integers who have the same successor in S. And connected[i] is 
 * initialized to i meaning integer in S has different successor (itself) in S initially.  
 * Meanwhile we have another array "max" of length n. max[i] indicates the max value of the connected 
 * components rooted at i.When element i is removed from S, that means a union operation of i and i + 1, 
 * because the successor of i is now the successor of i + 1 and successor of all the elements whose 
 * successor was i is now the successor of i + 1 too. So  the successor of x is the value of 
 * max[root(x)]. One special case needs to be handled is when n-1 is removed. then we mark the 
 * max[root(n-1)] as -1 , and when a component with max value as -1 is connected to another component, 
 * then the resulted connected component should have max value as -1 too. 
 * 
 * */
public class InterviewQuestion_SuccessorWithDelete {
	
	private static int[] maximum;
	private static int[] size;
	private static int[] id;
	
	public InterviewQuestion_SuccessorWithDelete(int n) {
		maximum = new int[n];
		size = new int[n];
		id = new int[n];
		setArrays(n);
	}

//  to this problem, we don't use the connected 
//	because we assume all nodes are connected
//	boolean connected(int p, int q) { 
//		return find(p) == find(q);
//	}

	int delete(int p) {
		// here if we try to delete the maximum of 
		// the whole set, just return it.
        if (p == id.length - 1) {
        	return p; 
        }

		return union(p, p + 1);
	}
	
	int root(int p) {
		while (p != id[p]) {
			id[p] = id[id[p]]; //path compression
			p = id[p];
		}
		return p;
	}

	int union(int p, int q) {
		int rootP = root(p);
		int rootQ = root(q);
		
		if (size[rootP] < size[rootQ]) {
			id[rootP] = rootQ;
			size[rootQ] += size[rootP];
			
			if(maximum[rootP] > maximum[rootQ]) {
				maximum[rootQ] = maximum[rootP];
			}
			return maximum[rootQ];
		} else {
			id[rootQ] = rootP;
			size[rootP] += size[rootQ];
			
			if(maximum[rootQ] > maximum[rootP]) {
				maximum[rootP] = maximum[rootQ];
			}
			
			return maximum[rootP];
		}
	}
	
	private void setArrays(int n) {
		size = new int[n];
		maximum = new int[n];

		for (int i = 0; i < n; i++) {
			size[i] = 1;
			maximum[i] = i;
			id[i] = i;
		}
	}
	
	private static void print(int[] size) {
		for (int i = 0; i < size.length; i++) { 
            System.out.print(size[i] + " "); 
        }
		System.out.println();
	}
	
	public static void main(String[] args) {
		InterviewQuestion_SuccessorWithDelete app 
			= new InterviewQuestion_SuccessorWithDelete(10);

		//it should print 12 because it's the biggest value with union
		System.out.println(app.delete(6));
		print(size);
		print(id);
		print(maximum);
	}

}
