package lab2.task4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node node1, Node node2) {
				// TODO Auto-generated method stub
				int res = Double.compare(node1.getPathCost(), node2.getPathCost());

				if (res == 0) {
					return node1.getLabel().compareTo(node2.getLabel());
				}

				return res;
			}
		});

		frontier.add(root);
		frontier.peek().setPathCost(0);

		while (!frontier.isEmpty()) {
			Node node = frontier.poll();

			for (Edge e : node.getChildren()) {
				Node parent = e.getBegin();
				Node child = e.getEnd();

				child.setParent(parent);

				if (child.getPathCost() == 0) {
					child.setPathCost(Integer.MAX_VALUE);
				}

				if (parent.getPathCost() + e.getWeight() < child.getPathCost()) {
					child.setPathCost(parent.getPathCost() + e.getWeight());
				}
				frontier.add(child);
			}

			if (node.getLabel().equals(goal)) {
				return node;
			}
		}

		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		return null;
	}

}
