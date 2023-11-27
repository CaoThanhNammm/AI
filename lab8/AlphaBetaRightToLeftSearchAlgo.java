package lab8;

import java.util.Iterator;

public class AlphaBetaRightToLeftSearchAlgo implements ISearchAlgo {

	@Override
	public void execute(Node node) {
		// TODO Auto-generated method stub
		System.out.println(maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	public int maxValue(Node node, int alpha, int beta) {
		// Enter your code here
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MIN_VALUE;
		node.getChildren().sort(Node.LabelComparator);
		for (int i = node.getChildren().size() - 1; i >= 0; i--) {
			int temp = minValue(node.getChildren().get(i), alpha, beta);
			if (v < temp) {
				v = temp;
			}
			alpha = v;

			if (beta <= alpha) {
				return v;
			}
		}

		return v;
	}

	public int minValue(Node node, int alpha, int beta) {
		// Enter your code here
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MAX_VALUE;
		node.getChildren().sort(Node.LabelComparator);
		for (int i = node.getChildren().size() - 1; i >= 0; i--) {
			int temp = maxValue(node.getChildren().get(i), alpha, beta);
			if (v > temp) {
				v = temp;
			}
			beta = v;

			if (beta <= alpha) {
				return v;
			}
		}

		return v;
	}
}
