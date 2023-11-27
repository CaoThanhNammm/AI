package lab8;

public class BestMove implements ISearchAlgo {

	@Override
	public void execute(Node node) {
		System.out.println(maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	public Node maxValue(Node node, int alpha, int beta) {
		// Enter your code here
		if (node.isTerminal())
			return node;
		node.getChildren().sort(Node.LabelComparator);
		Node re = new Node(node.getLabel(), Integer.MIN_VALUE);

		for (Node child : node.getChildren()) {
			Node temp = minValue(child, alpha, beta);
			if (re.getValue() < temp.getValue()) {
				re = child;
				re.setValue(temp.getValue());
			}
			alpha = re.getValue();

			if (beta <= alpha) {
				return re;
			}
		}

		return re;
	}

	public Node minValue(Node node, int alpha, int beta) {
		// Enter your code here
		if (node.isTerminal())
			return node;

		node.getChildren().sort(Node.LabelComparator);
		Node re = new Node(node.getLabel(), Integer.MAX_VALUE);

		for (Node child : node.getChildren()) {
			Node temp = maxValue(child, alpha, beta);
			if (re.getValue() > temp.getValue()) {
				re = child;
				re.setValue(temp.getValue());
			}
			beta = re.getValue();

			if (beta <= alpha) {
				return re;
			}

		}
		return re;
	}
}
