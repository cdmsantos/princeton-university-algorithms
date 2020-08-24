package com.crystalsantos.week5.assignment;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	private Node root;
	private int size;

	private class Node {
		private Point2D point;
		private Node left, right;
		private boolean isVertical;

		public Node(boolean isVertical, Point2D point) {
			this.isVertical = isVertical;
			this.left = null;
			this.right = null;
			this.point = point;
		}
	}

	// construct an empty set of points
	public KdTree() {
		root = null;
		size = 0;
	}

	public boolean isEmpty() {
		return root == null;
	}

	// number of points in the set
	public int size() {
		return size;
	}

	public void insert(Point2D p) {
		checkNullValue(p);

		if (isEmpty()) {
			root = new Node(true, p);
			size++;
		} else {
			if (!contains(p)) {
				size++;
				insert(p, root);
			}
		}
	}

	private void insert(Point2D p, Node current) {
		if (isLessThan(p, current)) { // left
			if (current.left != null) {
				insert(p, current.left);
			} else {
				current.left = new Node(!current.isVertical, p);
			}
		} else { // right
			if (current.right != null) {
				insert(p, current.right);
			} else {
				current.right = new Node(!current.isVertical, p);
			}
		}
	}

	public boolean contains(Point2D p) {
		checkNullValue(p);

		if (isEmpty()) {
			return false;
		} else {
			return contains(p, root);
		}
	}

	private boolean contains(Point2D p, Node current) {
		boolean contains = false;
		if (p.equals(current.point)) {
			contains = true;
		} else {
			if (isLessThan(p, current)) {
				if (current.left != null) {
					contains = contains(p, current.left);
				}
			} else {
				if (current.right != null) {
					contains = contains(p, current.right);
				}
			}
		}
		return contains;
	}

	public void draw() {

		if (!isEmpty()) {
			StdDraw.point(root.point.x(), root.point.y());
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(root.point.x(), 0, root.point.x(), 1);

			draw(root.left, root, true);
			draw(root.right, root, false);
		}

		StdDraw.show();
	}

	private void draw(Node current, Node parent, boolean left) {
		if (current != null) {
			StdDraw.point(current.point.x(), current.point.y());

			if (current.isVertical) {
				StdDraw.setPenColor(StdDraw.RED);
				if (left) {
					StdDraw.line(current.point.x(), 0, current.point.x(), parent.point.y());
				} else {
					StdDraw.line(parent.point.x(), parent.point.y(), parent.point.x(), 1);
				}
			} else {
				StdDraw.setPenColor(StdDraw.BLUE);
				if (left) {
					StdDraw.line(0, current.point.y(), parent.point.x(), current.point.y());
				} else {
					StdDraw.line(parent.point.x(), current.point.y(), 1, current.point.y());
				}
			}

			draw(current.left, current, true);
			draw(current.right, current, false);
		}
	}

	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		checkNullValue(rect);
		List<Point2D> points = new ArrayList<>();

		if (!isEmpty()) {
			if (rect.contains(root.point)) {
				points.add(root.point);
			}
			range(root.left, new RectHV(0, 0, root.point.x(), 1), rect, points);
			range(root.right, new RectHV(root.point.x(), 0, 1, 1), rect, points);
		}
		return points::iterator;
	}

	private RectHV buildRectangle(Node node, RectHV recParent, boolean leftTree) {
		if (node.isVertical) {
			if (leftTree) {
				return new RectHV(recParent.xmin(), recParent.ymin(), node.point.x(), recParent.ymax());
			} else {
				return new RectHV(node.point.x(), recParent.ymin(), recParent.xmax(), recParent.ymax());
			}
		} else {
			if (leftTree) {
				return new RectHV(recParent.xmin(), recParent.ymin(), recParent.xmax(), node.point.y());
			} else {
				return new RectHV(recParent.xmin(), node.point.y(), recParent.xmax(), recParent.ymax());
			}
		}
	}

	private void range(Node node, RectHV recParent, RectHV rect, List<Point2D> points) {
		if (node != null && rect.intersects(recParent)) {
			if (rect.contains(node.point)) {
				points.add(node.point);
			}

			if (node.left != null) {
				RectHV leftRetangle = buildRectangle(node, recParent, true);
				if (leftRetangle.intersects(rect)) {
					range(node.left, leftRetangle, rect, points);
				}
			}
			if (node.right != null) {
				RectHV rightRectangle = buildRectangle(node, recParent, false);
				if (rightRectangle.intersects(rect)) {
					range(node.right, rightRectangle, rect, points);
				}
			}
		}
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		checkNullValue(p);

		Point2D nearestPoint = null;
		if (!isEmpty()) {
			nearestPoint = root.point;
			nearestPoint = nearest(p, root.left, nearestPoint, new RectHV(0, 0, root.point.x(), 1));
			nearestPoint = nearest(p, root.right, nearestPoint, new RectHV(root.point.x(), 0, 1, 1));
		}

		return nearestPoint;
	}

	private Point2D nearest(Point2D p, Node node, Point2D nearestPoint, RectHV rectParent) {
		if (node != null && rectParent.distanceSquaredTo(p) < nearestPoint.distanceSquaredTo(p)) {

			if (node.point.distanceSquaredTo(p) < nearestPoint.distanceSquaredTo(p)) {
				nearestPoint = node.point;
			}

			nearestPoint = nearest(p, node.left, nearestPoint, buildRectangle(node, rectParent, true));
			nearestPoint = nearest(p, node.right, nearestPoint, buildRectangle(node, rectParent, false));
		}

		return nearestPoint;
	}

	private void checkNullValue(Object o) {
		if (o == null) {
			throw new IllegalArgumentException("Argument must not be null");
		}
	}

	private boolean isLessThan(Point2D p, Node root) {
		boolean compare = p.x() < root.point.x();
		if (!root.isVertical) {
			compare = p.y() < root.point.y();
		}
		return compare;
	}

	public static void main(String[] args) {
		KdTree tree = new KdTree();
//		tree.insert(new Point2D(0.7, 0.2));
//		tree.insert(new Point2D(0.5, 0.4));
//		tree.insert(new Point2D(0.2, 0.3));
//		tree.insert(new Point2D(0.4, 0.7));
//		tree.insert(new Point2D(0.9, 0.6));

		tree.insert(new Point2D(0.5, 0.25));
		tree.insert(new Point2D(0.0, 1.0));
		tree.insert(new Point2D(0.25, 0.5));

//		tree.draw();
		Iterable<Point2D> it = tree.range(new RectHV(0.75, 0.0, 1.0, 0.75));
		for (Point2D point : it) {
			System.out.println(point.toString());
		}

	}
}