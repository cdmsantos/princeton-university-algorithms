package com.crystalsantos.week3.assignment;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
	private LineSegment[] segments;

	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
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
		int length = points.length;
		ArrayList<LineSegment> lineSegments = new ArrayList<>();
		Point[] clonedPoints = points.clone();
		Arrays.sort(clonedPoints);
		for (int p = 0; p < length; p++) {
			for (int q = p + 1; q < length; q++) {
				for (int r = q + 1; r < length; r++) {
					for (int s = r + 1; s < length; s++) {
						if (isCollinear(clonedPoints[p], clonedPoints[q], clonedPoints[r], clonedPoints[s])) {
							lineSegments.add(new LineSegment(clonedPoints[p], clonedPoints[s]));
						}
					}
				}
			}
		}

		return lineSegments.toArray(new LineSegment[lineSegments.size()]);
	}

	private boolean isCollinear(Point p, Point q, Point r, Point s) {
		boolean isCollinear = false;
		double slopeQ = p.slopeTo(q);
		double slopeR = p.slopeTo(r);
		double slopeS = p.slopeTo(s);

		if ((slopeQ == slopeR) && (slopeQ == slopeS)) {
			isCollinear = true;
		}

		return isCollinear;
	}

	public static void main(String[] args) {
		Point[] points = { null };
		BruteCollinearPoints app = new BruteCollinearPoints(points);
		app.segments();
		System.out.println(app.numberOfSegments());
	}
}
