package lab2.task5;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node node1, Node node2) {
				// TODO Auto-generated method stub
				return -node1.getLabel().compareTo(node2.getLabel());
			}
		});

		frontier.add(root);

		frontier.peek().setPathCost(0);
		frontier.peek().setDepth(0);

		while (!frontier.isEmpty()) {
			Node node = frontier.poll();

			for (Edge e : node.getChildren()) {
				e.getEnd().setParent(e.getBegin());
				e.getEnd().setDepth(e.getBegin().getDepth() + 1);

				if (!frontier.contains(e.getEnd())) {
					e.getEnd().setPathCost(Integer.MAX_VALUE);
				}

				if (e.getEnd().getPathCost() > e.getWeight() + e.getBegin().getPathCost()) {
					e.getEnd().setPathCost(e.getWeight() + e.getBegin().getPathCost());

					frontier.add(e.getEnd());
				}

				if (e.getEnd().equals(new Node(goal))) {
					return e.getEnd();
				}
			}
		}

		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node node1, Node node2) {
				// TODO Auto-generated method stub
				return -node1.getLabel().compareTo(node2.getLabel());
			}
		});

		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node node = frontier.poll();

			for (Edge e : node.getChildren()) {
				if (e.getBegin().equals(new Node(start))) {
					return execute(e.getBegin(), goal);
				} else if (e.getEnd().equals(new Node(start))) {
					return execute(e.getEnd(), goal);
				} else if (!frontier.contains(e.getEnd())) {
					frontier.add(e.getEnd());
				}
			}
		}
		return null;
	}

}
