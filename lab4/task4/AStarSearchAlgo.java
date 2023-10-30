package lab4.task4;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				int re = Double.compare(o1.getH() + o1.getG(), o2.getH() + o2.getG());
				if (re == 0) {
					return o1.getLabel().compareTo(o2.getLabel());
				}
				return re;
			}
		});

		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			}

			for (Edge edge : current.getChildren()) {
				Node begin = edge.getBegin();
				Node end = edge.getEnd();

				double temp = end.getG();
				end.setG(begin.getG() + edge.getWeight());

				if (temp <= end.getG() && temp != 0) {
					end.setG(temp);
					end.setParent(end.getParent());
				} else {
					end.setG(begin.getG() + edge.getWeight());
					end.setParent(begin);
				}

				frontier.add(end);
			}
		}

		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				int re = Double.compare(o1.getH() + o1.getG(), o2.getH() + o2.getG());
				if (re == 0) {
					return o1.getLabel().compareTo(o2.getLabel());
				}
				return re;
			}
		});

		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node node = frontier.poll();

			if (node.getLabel().equals(start)) {
				return execute(node, goal);
			}

			for (Edge e : node.getChildren()) {
				frontier.add(e.getEnd());
			}
		}

		return null;
	}

}
