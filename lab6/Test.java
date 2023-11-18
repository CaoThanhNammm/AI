package lab6;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
//		Node board = new Node();
//
//		board.generateBoard();
//		System.out.println("state init");
//		board.displayBoard();
//		HillClimbingSearchNQueen search = new HillClimbingSearchNQueen(board);
//
//		System.out.println("state after excute");
//		Node node = search.SA(board, 1000, 0.995);
//		node.displayBoard();

		GA_NQueenAlgo ga = new GA_NQueenAlgo();
		ga.initPopulation();
		System.out.println(ga.execute());

	}

}
