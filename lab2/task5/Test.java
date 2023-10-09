package lab2.task5;

public class Test {
	public static void main(String[] args) {
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node F = new Node("F");
		Node H = new Node("H");
		Node R = new Node("R");
		Node p = new Node("p");
		Node q = new Node("q");
		Node start = new Node("start");
		Node goal = new Node("goal");

		B.addEdge(A, 2);

		C.addEdge(A, 2);

		D.addEdge(B, 1);
		D.addEdge(C, 8);
		D.addEdge(E, 2);

		E.addEdge(H, 1);
		E.addEdge(R, 9);

		F.addEdge(C, 5);
		F.addEdge(goal, 5);

		H.addEdge(q, 4);
		H.addEdge(p, 4);

		R.addEdge(F, 5);

		q.addEdge(R, 3);

		p.addEdge(q, 15);

		start.addEdge(D, 3);
		start.addEdge(E, 9);
		start.addEdge(p, 1);

		ISearchAlgo ucs = new UniformCostSearchAlgo();

		System.out.println(ucs.execute(start, "D", "C"));
	}
}
