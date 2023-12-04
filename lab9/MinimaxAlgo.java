package lab9;

public class MinimaxAlgo {
	public void execute(Node node) {
		int v = minValue(node);
		System.out.println(v);
	}

	public int maxValue(Node node) {
		if (node.isTerminal()) {
			return 0;
		}

		int v = Integer.MIN_VALUE;
		for (Node child : node.getSuccessors()) {
			v = Math.max(v, minValue(child));
		}

		return v;
	}

	public int minValue(Node node) {
		if (node.isTerminal()) {
			return 1;
		}

		int v = Integer.MAX_VALUE;
		for (Node child : node.getSuccessors()) {
			v = Math.min(v, maxValue(child));
		}

		return v;
	}
}
