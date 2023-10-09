package lab2.task3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Queue<Node> frontier = new LinkedList<Node>();

		frontier.add(root);
		frontier.peek().setPathCost(0);

		while (!frontier.isEmpty()) {
			Node node = frontier.poll();

			for (Edge e : node.getChildren()) {
				e.getEnd().setPathCost(e.getWeight() + e.getBegin().getPathCost());
				e.getEnd().setParent(e.getBegin());
				e.getEnd().setDepth(e.getBegin().getDepth() + 1);

				if (e.getEnd().equals(new Node(goal))) {
					return e.getEnd();
				} else {
					frontier.add(e.getEnd());
				}
			}
		}

		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		Queue<Node> frontier = new LinkedList<Node>();

		frontier.add(root);
		frontier.peek().setPathCost(0);
		int total = 0;

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
