package lab6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
	public static final int POP_SIZE = 100;// Population size
	public static final double MUTATION_RATE = 0.03;
	public static final int MAX_ITERATIONS = 1000;
	List<Node> population = new ArrayList<Node>();
	Random rd = new Random();

	// initialize the individuals of the population
	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Node ni = new Node();
			ni.generateBoard();
			population.add(ni);
		}
	}

	public Node execute() {
		int j = 0;
		while (j < MAX_ITERATIONS) {
			List<Node> newPopulation = new ArrayList<>();
			for (int i = 0; i < population.size(); i++) {
				Node x = new Node(getParentByRandomSelection());
				Node y = new Node(getParentByRandomSelection());

				Node child = reproduce(x, y);

				if (Math.random() < MUTATION_RATE) {
					System.out.println(child.getH());
					child = mutate(child);
				}

				newPopulation.add(child);
			}

			population = newPopulation;
			j++;
		}

//		System.out.println(population.size());
//		for (Node node : population) {
//			System.out.println();
//			node.displayBoard();
//		}

		return null;
	}

	// Mutate an individual by selecting a random Queen and
	// move it to a random row.
	public Node mutate(Node node) {
		return node.selectNextRandomCandidate();
	}

	// Crossover x and y to reproduce a child
	public Node reproduce(Node x, Node y) {
		return x.reproduce(y);
	}

	// Select K individuals from the population at random and
	// select the best out of these to become a parent.
	public Node getParentByTournamentSelection() {
		int k = 5;
		List<Node> allCandidates = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			allCandidates.set(i, population.get(rd.nextInt()));
		}

		Node re = null;
		for (int i = 0; i < k; i++) {
			re = allCandidates.get(i).getBestCandidate();
		}

		return re;
	}

	// Select a random parent from the population
	public Node getParentByRandomSelection() {
		return population.get(rd.nextInt(8));
	}
}
