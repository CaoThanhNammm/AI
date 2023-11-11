package lab6;

public class Queen {
	private int row;
	private int column;

	public Queen(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public void move() {
		// Enter your code here
		if (row == Node.N - 1) {
			row = 0;
		} else {
			row += 1;
		}
	}

	// check whether this Queen can attack the given Queen (q)
	public boolean isConflict(Queen q) {
		// Enter your code here
		int r = Math.abs(row - q.row);
		int c = Math.abs(column - q.column);

		if (this.row == q.getRow() || this.column == q.getColumn() || r == c) {
			return true;
		}

		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
}
