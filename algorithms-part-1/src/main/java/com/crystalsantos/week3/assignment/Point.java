package com.crystalsantos.week3.assignment;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw() {
		StdDraw.point(x, y);
	}

	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public double slopeTo(Point that) {
		double slope;

		if (compareTo(that) == 0) { // degenerate line segment
			slope = Double.NEGATIVE_INFINITY;
		} else if (that.y == this.y) { // horizontal line segment
			slope = 0.0;
		} else if (that.x == this.x) { // vertical line segment
			slope = Double.POSITIVE_INFINITY;
		} else {
			slope = (double)(that.y - this.y) / (double)(that.x - this.x);
		}

		return slope;
	}

	public int compareTo(Point that) {
		int difference = this.y - that.y;
		return difference != 0 ? difference : this.x - that.x;
	}

	public Comparator<Point> slopeOrder() {
		return new Comparator<Point>() {

			@Override
			public int compare(Point p1, Point p2) {

				double slopeP1 = slopeTo(p1);
				double slopeP2 = slopeTo(p2);

				if (slopeP1 == slopeP2) {
					return 0;
				} else if (slopeP1 < slopeP2) {
					return -1;					
				}
				return 1;
			}
		};
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public static void main(String[] args) {
		Point p = new Point(13, 31);
		Point q = new Point(254, 342);
		Point r = new Point(13, 313);
		Point s = new Point(468, 483);

		System.out.println(p.slopeOrder().compare(q, r));
		System.out.println(p.slopeOrder().compare(r, s));
		System.out.println(p.slopeOrder().compare(q, s));


	}
}