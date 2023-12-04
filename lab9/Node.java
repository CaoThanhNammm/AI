package lab9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Node {
	private List<Integer> data = new ArrayList<Integer>();

	public void add(Integer val) {
		this.data.add(val);
	}

	public void addAll(List<Integer> data) {
		this.data.addAll(data);
	}

	// Get children of the current nodes
	public List<Node> getSuccessors() {
		List<Node> res = new ArrayList<>();

		for (int i = 0; i < data.size(); i++) {
			res = getSuccessors(data.get(i) - 1, 1, res);

//			List<Node> temp = new ArrayList<>();
//			temp = getSuccessors(data.get(i) - 1, 1, temp);
//			if (temp.size() != 0) {
//				for (Node node : temp) {
//					res.add(node);
//				}
//				break;
//			}
		}

		return res;
	}

	public List<Node> getSuccessors(int a, int b, List<Node> res) {
		if (a - b < 1) {
			return res;
		}

		Node node = new Node();
		node.add(a);
		node.add(b);
		if (!res.contains(node)) {
			res.add(node);
		}

		getSuccessors(a - 1, b + 1, res);
		return res;
	}

	// Check whether a node is terminal or not
	public boolean isTerminal() {
		List<Node> data = getSuccessors();
		return data.size() == 0;
	}

	public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	@Override
	public String toString() {
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(data);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return Objects.equals(data, other.data);
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

}
