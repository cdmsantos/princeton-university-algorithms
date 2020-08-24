package com.crystalsantos.week5.assignment;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {

	private TreeSet<Point2D> tree;

	// construct an empty set of points
	public PointSET() {
		tree = new TreeSet<Point2D>();
	}

	public boolean isEmpty() {
		return tree.isEmpty();
	}

	// number of points in the set
	public int size() {
		return tree.size();
	}

	public void insert(Point2D p) {
		checkNullValue(p);
		tree.add(p);
	}

	public boolean contains(Point2D p) {
		checkNullValue(p);
		return tree.contains(p);
	}

	public void draw() {
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 1);
		StdDraw.setYscale(0, 1);

		for (Point2D point : tree) {
			point.draw();
		}

		StdDraw.show();
	}

	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		checkNullValue(rect);

		List<Point2D> range = new ArrayList<Point2D>();
		for (Point2D point : tree) {
			if (rect.contains(point)) {
				range.add(point);
			}
		}
		return range::iterator;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		checkNullValue(p);

		double distance = Integer.MIN_VALUE;
		Point2D nearest = null;
		for (Point2D point : tree) {
			double pointDistance = point.distanceSquaredTo(p);
			if (distance == Integer.MIN_VALUE || pointDistance < distance) {
				distance = pointDistance;
				nearest = point;
			}
		}

		return nearest;
	}

	private void checkNullValue(Object o) {
		if (o == null) {
			throw new IllegalArgumentException("Argument must not be null");
		}
	}

	public static void main(String[] args) {
		PointSET set = new PointSET();

		set.insert(new Point2D(0.1, 0.2));
		set.insert(new Point2D(0.3, 0.4));
		set.insert(new Point2D(0.5, 0.6));
		set.insert(new Point2D(0.7, 0.8));

		set.nearest(new Point2D(0.3, 0.2));

	}
}
