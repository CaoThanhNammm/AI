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
				Node x = new Node(getParentByTournamentSelection());
				Node y = new Node(getParentByTournamentSelection());

				Node child = reproduce(x, y);

				if (Math.random() < MUTATION_RATE) {
					child = mutate(child);
				}

				newPopulation.add(child);
			}

			population = newPopulation;
			j++;
		}

		Node re = population.get(0);
		for (int i = 1; i < population.size(); i++) {
			if (re.getH() > population.get(i).getH()) {
				re = population.get(i);
			}
		}

		re.displayBoard();
		System.out.println(re.getH());
		return re;
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
		int k = 10;
		Node re = population.get(rd.nextInt(population.size()));

		for (int i = 0; i < k - 1; i++) {
			Node node = population.get(rd.nextInt(population.size()));
			if (re.getH() > node.getH()) {
				re = node;
			}
		}

		return re;
	}

	// Select a random parent from the population
	public Node getParentByRandomSelection() {
		return population.get(rd.nextInt(population.size()));
	}
}
