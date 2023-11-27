package lab8;

import java.util.Comparator;
import java.util.List;

public class MiniMaxSearchAlgo implements ISearchAlgo {

	// function MINIMAX-DECISION(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		// Enter your code here
		System.out.println(maxValue(node));
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node) {
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MIN_VALUE;

		node.getChildren().sort(Node.LabelComparator);

		for (Node child : node.getChildren()) {
			int temp = minValue(child);
			if (v < temp) {
				v = temp;
			}
		}

		return v;
	}
	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v

	public int minValue(Node node) {
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MAX_VALUE;
		node.getChildren().sort(Node.LabelComparator);
		
		for (Node child : node.getChildren()) {
			int temp = maxValue(child);
			if (v > temp) {
				v = temp;
			}
		}

		return v;
	}
}
