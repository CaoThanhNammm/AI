package lab5.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SolutionGreedy implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		PriorityQueue<Node> frontier = new PriorityQueue<>(PuzzleUtils.HeuristicComparatorByH);
		// TODO Auto-generated method stub
		Node initState = model.getInitialState();
		Node goalState = model.getGoalState();
		List<Node> explorer = new ArrayList<>();

		frontier.add(initState);
		int step = 0;
		while (!frontier.isEmpty()) {
			List<Node> boards = new ArrayList<>();
			Node node = frontier.poll();
			
			if (node.equals(goalState)) {
				System.out.println("step: " + step);
				return node;
			}
			step++;

			Node moveLeft = model.moveWhiteTile(node, 'l');
			Node moveRight = model.moveWhiteTile(node, 'r');
			Node moveDown = model.moveWhiteTile(node, 'd');
			Node moveUp = model.moveWhiteTile(node, 'u');

			if (moveLeft != null) {
				boards.add(moveLeft);
			}
			if (moveDown != null) {
				boards.add(moveDown);
			}
			if (moveRight != null) {
				boards.add(moveRight);
			}
			if (moveUp != null) {
				boards.add(moveUp);
			}

			for (Node board : boards) {
				if (!explorer.contains(board)) {
					explorer.add(board);
					frontier.add(board);
				}
			}
		}

		return null;
	}

}
