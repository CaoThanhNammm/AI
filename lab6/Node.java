package lab6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Node {
	public static final int N = 8;
	private Queen[] state;

	public Node() {
		// generateBoard();
		state = new Queen[N];
	}

	public Node(Queen[] state) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
		}
	}

	public Node(Node n) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			Queen qi = n.state[i];
			this.state[i] = new Queen(qi.getRow(), qi.getColumn());
		}
	}

	public void generateBoard() {
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			state[i] = new Queen(random.nextInt(N), i);
		}
	}

	public int getH() {
		int heuristic = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (state[i].isConflict(state[j])) {
					heuristic++;
				}
			}
		}
		return heuristic;
	}

	public List<Node> generateAllCandidates() {
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < N; i++) {
			Node node = new Node(this.state);
			Queen[] stateInit = node.state;

			stateInit[i].move();
			result.add(node);
		}

		return result;
	}

	public Node selectNextRandomCandidate() {
		Random random = new Random();
		int pos = random.nextInt(N);
		int row = random.nextInt(N);

		Node re = new Node(this.state);
		re.state[pos].setRow(row);

		return re;
	}

	public void displayBoard() {
		int[][] board = new int[N][N];
		// set queen position on the board
		for (int i = 0; i < N; i++) {
			board[state[i].getRow()][state[i].getColumn()] = 1;
		}

		// print board
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}

	public Node getBestCandidate() {
		List<Node> candidates = generateAllCandidates();
		Node re = candidates.get(0);
		for (int i = 1; i < N; i++) {
			if (re.getH() > candidates.get(i).getH()) {
				re = candidates.get(i);
			}
		}
		return re;
	}

	public Node reproduce(Node that) {
		Node x = new Node(state);
		Node y = new Node(that);

		int k = (new Random()).nextInt(N);
		
		for (int i = k; i < Node.N; i++) {
			int temp = x.state[i].getRow();
			x.state[i].setRow(y.state[i].getRow());
			y.state[i].setRow(temp);
		}

		return x;
	}

}
