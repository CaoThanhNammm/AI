package lab5.task1;

import java.util.List;

public class TestNode {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		p.readInput("C:/Users/Nam/Desktop/ThucHanhLapTrinh/AI/src/lab5/task1/txt/PuzzleMap.txt",
				"C:/Users/Nam/Desktop/ThucHanhLapTrinh/AI/src/lab5/task1/txt/PuzzleGoalState.txt");

		Node initialState = p.getInitialState();
		
		Node goalState = p.getGoalState();

//		IPuzzleAlgo greedy = new SolutionGreedy();
//		System.out.println(greedy.execute(p));
		
		IPuzzleAlgo aStar = new SolutionAStar();
		System.out.println(aStar.execute(p));
	}
}
