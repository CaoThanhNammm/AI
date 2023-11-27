package lab8;

public class AlphaBetaSearchAlgo implements ISearchAlgo {

	// function ALPHA-BETA-SEARCH(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state, Integer.MIN_VALUE , Integer.MAX_VALUE)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		System.out.println(maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	// function MAX-VALUE(state, alpha, beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- MIN_VALUE;
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s, alpha, beta))
	// if v >= beta then return v
	// alpha <- MAX(alpha, v)
	// return v

	public int maxValue(Node node, int alpha, int beta) {
		// Enter your code here
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MIN_VALUE;
		node.getChildren().sort(Node.LabelComparator);
		for (Node child : node.getChildren()) {
			int temp = minValue(child, alpha, beta);
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
	// function MIN-VALUE(state, alpha , beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s, alpha , beta))
	// if v <= alpha then return v
	// beta <- MIN(beta ,v)
	// return v

	public int minValue(Node node, int alpha, int beta) {
		// Enter your code here
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MAX_VALUE;
		node.getChildren().sort(Node.LabelComparator);
		
		for (Node child : node.getChildren()) {
			int temp = maxValue(child, alpha, beta);
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
