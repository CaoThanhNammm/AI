package lab2.task6;

import java.util.Stack;

public class DepthLimitSerchAlgo {

	public Node execute(Node root, String goal, int limitedDepth) {
		// TODO Auto-generated method stub
		Stack<Node> frontier = new Stack<Node>();

		frontier.add(root);
		frontier.peek().setPathCost(0);
		frontier.peek().setDepth(0);

		while (!frontier.isEmpty()) {
			Node node = frontier.pop();

			for (Edge e : node.getChildren()) {
				e.getEnd().setPathCost(e.getWeight() + e.getBegin().getPathCost());
				e.getEnd().setParent(e.getBegin());
				e.getEnd().setDepth(e.getBegin().getDepth() + 1);

				if (e.getEnd().getDepth() <= limitedDepth) {
					if (e.getEnd().equals(new Node(goal))) {
						return e.getEnd();
					} else {
						frontier.add(e.getEnd());
					}
				}
			}
		}

		return null;
	}

}
