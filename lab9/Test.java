package lab9;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		Node node = new Node();
		Integer[] data = { 13 };
		node.addAll(Arrays.asList(data));

		MinimaxAlgo algo = new MinimaxAlgo();

		algo.execute(node);
	}
}