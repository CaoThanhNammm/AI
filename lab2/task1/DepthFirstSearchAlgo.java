package lab2.task1;

import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Stack<Node> frontier = new Stack<Node>();

		frontier.add(root);
		frontier.peek().setPathCost(0);

		while (!frontier.isEmpty()) {
			Node node = frontier.pop();

			for (Edge e : node.getChildren()) {
				e.getEnd().setPathCost(e.getWeight() + e.getBegin().getPathCost());
				e.getEnd().setParent(e.getBegin());
				e.getEnd().setDepth(e.getBegin().getDepth() + 1);

				if (e.getEnd().equals(new Node(goal))) {
					return e.getEnd();
				} else if (!frontier.contains(e.getEnd())) {
					frontier.add(e.getEnd());
				}
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
