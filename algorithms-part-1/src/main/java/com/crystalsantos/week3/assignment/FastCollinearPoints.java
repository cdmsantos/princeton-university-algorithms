package com.crystalsantos.week3.assignment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private LineSegment[] segments;

	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		checkCornerCases(points);

		segments = buildSegments(points);
	}

	// the number of line segments
	public int numberOfSegments() {
		return segments.length;
	}

	// the line segments
	public LineSegment[] segments() {
		return segments.clone();

	}

	private LineSegment[] buildSegments(Point[] points) {
		List<LineSegment> segments = new ArrayList<LineSegment>();

		Point[] clonedPoints = Arrays.copyOf(points, points.length);

		for (Point p : points) {
			Arrays.sort(clonedPoints, p.slopeOrder());

			double tempSlope = p.slopeTo(clonedPoints[0]);
			int collinearPoints = 1;

			int q = 1;
			for (; q < clonedPoints.length; q++) {

				double newSlope = p.slopeTo(clonedPoints[q]);
				
				if (newSlope == tempSlope) {
					collinearPoints++;
				} else {
					checkCollinear(segments, clonedPoints, p, collinearPoints, q);
					tempSlope = newSlope;
					collinearPoints = 1;
				}
			}
			checkCollinear(segments, clonedPoints, p, collinearPoints, q);
		}
		return segments.toArray(new LineSegment[segments.size()]);
	}

	private void checkCollinear(List<LineSegment> segments, Point[] clonedPoints, Point p, int collinearPoints,
			int finalIndex) {
		if (collinearPoints >= 3) {
			Arrays.sort(clonedPoints, finalIndex - collinearPoints, finalIndex);
			if (p.compareTo(clonedPoints[finalIndex - collinearPoints]) < 0)
				segments.add(new LineSegment(p, clonedPoints[finalIndex - 1]));
		}
	}

	private void checkCornerCases(Point[] points) {
		if (points == null) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < points.length; i++) {

			if (points[i] == null) {
				throw new IllegalArgumentException();
			}

			for (int j = i + 1; j < points.length; j++) {
				if (points[j] == null) {
					throw new IllegalArgumentException();
				}

				if (points[i].compareTo(points[j]) == 0) {
					throw new IllegalArgumentException();
				}
			}
		}
	}

	public static void main(String[] args) {

		// read the n points from a file
		In in = new In("/Users/crystalsantos/Downloads/collinear/input8.txt");
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
//		StdDraw.enableDoubleBuffering();
//		StdDraw.setXscale(0, 32768);
//		StdDraw.setYscale(0, 32768);
//		for (Point p : points) {
//			p.draw();
//		}
//		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
//			segment.draw();
		}
//		StdDraw.show();
	}
}