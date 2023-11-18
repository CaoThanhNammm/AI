package lab6;

public class HillClimbingSearchNQueen {
	private Node stateInit;

	public HillClimbingSearchNQueen(Node stateInit) {
		super();
		this.stateInit = stateInit;
	}

	public Node execute(Node initialState) {
		Node current = initialState;
		int stepClimbed = 0;
		while (true) {
			Node neighbor = current.getBestCandidate();
			if (neighbor.getH() < current.getH()) {
				current = neighbor;
				stepClimbed++;
			} else {
				System.out.println("stepClimbed: " + stepClimbed);
				return current;
			}
		}

	}

	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		int randomRestarts = 0;
		Node current = execute(initialState);

		while (current.getH() != 0) {
			stateInit.generateBoard();

			current = stateInit;
			randomRestarts++;
		}

		System.out.println("randomRestarts: " + randomRestarts);
		return initialState;
	}

	public Node SA(Node initialState, double startingTemperature, double coolingRate) {
		Node current = new Node(initialState);

		double t = startingTemperature;
		for (int i = 0; i < 1000000; i++) {
			if (current.getH() == 0) {
				return current;
			}

			Node next = current.selectNextRandomCandidate();
			double deltaE = next.getH() - current.getH();
			if (deltaE > 0) {
				current = next;
			} else if (Math.exp(deltaE / t) < Math.random()) {
				current = next;
			}

			t *= coolingRate;
		}

		return current;
	}
}
