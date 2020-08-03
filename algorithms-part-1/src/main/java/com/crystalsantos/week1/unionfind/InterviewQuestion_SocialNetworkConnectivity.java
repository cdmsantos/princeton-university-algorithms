package com.crystalsantos.week1.unionfind;

/*
 * Given a social network containing n members and a log file containing m timestamps at which 
 * times pairs of members formed friendships, design an algorithm to determine the earliest time 
 * at which all members are connected (i.e., every member is a friend of a friend of a friend 
 * ... of a friend). Assume that the log file is sorted by timestamp and that friendship is an 
 * equivalence relation. The running time of your algorithm should be mlogn or better and use 
 * extra space proportional to n.
 * 
 */
public class InterviewQuestion_SocialNetworkConnectivity {

	int numberOfFriends;
	private int[] size;
	private int[] id;

	public InterviewQuestion_SocialNetworkConnectivity(int n) {
		numberOfFriends = n;
		size = new int[n];
		id = new int[n];
		setArrays(n);
	}

	boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	int find(int p) {
		while (p != id[p]) {
			id[p] = id[id[p]];
			p = id[p];
		}
		return p;
	}

	void union(int p, int q, String timestamp) {
		int rootP = find(p);
		int rootQ = find(q);

		if (rootP == rootQ) return;
		
		if (size[rootP] < size[rootQ]) {
			id[rootP] = rootQ;
			size[rootQ] += size[rootP];
			if(size[rootQ] == numberOfFriends) {
				System.out.println("all members are connected in the social network at time " + timestamp);
			}
		} else {
			id[rootQ] = rootP;
			size[rootP] += size[rootQ];
			if(size[rootP] == numberOfFriends) {
				System.out.println("all members are connected in the social network at time " + timestamp);
			}
		}		
	}
	
	private void setArrays(int n) {
		size = new int[n];
		for (int i = 0; i < n; i++) {
			size[i] = 1;
			id[i] = i;
		}
	}

	public static void main(String[] args) {
		InterviewQuestion_SocialNetworkConnectivity app 
			= new InterviewQuestion_SocialNetworkConnectivity(6);

		app.union(1, 5, "2020-07-23 19:19:00");
		app.union(2, 4, "2020-07-23 19:19:01");
		app.union(1, 3, "2020-07-23 19:19:02");
		app.union(5, 2, "2020-07-23 19:19:03");
		app.union(0, 3, "2020-07-23 19:19:04");
		app.union(2, 1, "2020-07-23 19:19:05");
		app.union(2, 1, "2020-07-23 19:19:06");

	}
}
