package lab2.task6;

public class Test {
	public static void main(String[] args) {
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");
		Node nodeG = new Node("G");
		Node nodeH = new Node("H");
		Node nodeI = new Node("I");
		Node nodeJ = new Node("J");
		Node nodeK = new Node("K");
		Node nodeL = new Node("L");
		Node nodeM = new Node("M");
		Node nodeN = new Node("N");
		Node nodeO = new Node("O");
		Node nodeP = new Node("P");
		Node nodeR = new Node("R");
		Node nodeS = new Node("S");

		nodeA.addEdge(nodeB);
		nodeA.addEdge(nodeC);
		nodeA.addEdge(nodeD);

		nodeB.addEdge(nodeF);
		nodeH.addEdge(nodeE);

		nodeC.addEdge(nodeG);

		nodeD.addEdge(nodeH);

		nodeE.addEdge(nodeI);

		nodeF.addEdge(nodeJ);
		nodeF.addEdge(nodeK);

		nodeG.addEdge(nodeL);

		nodeH.addEdge(nodeM);
		nodeH.addEdge(nodeN);

		nodeK.addEdge(nodeO);
		nodeK.addEdge(nodeP);

		nodeL.addEdge(nodeR);

		nodeN.addEdge(nodeS);

		DepthLimitSerchAlgo dls = new DepthLimitSerchAlgo();

		System.out.println(dls.execute(nodeA, "R", 4));
		System.out.println(dls.execute(nodeA, "R", 3));
		
	}
}
