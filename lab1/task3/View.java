package lab1.task3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class View extends JFrame {
	public static final String CLEAN_IMG = "src\\lab1\\task3\\clean.png";
	public static final String DIRTY_IMG = "src\\lab1\\task3\\dirty.png";
	public static final String AGENT_IMG = "src\\lab1\\task3\\cleaner.png";
	public static final String WALL_IMG = "src\\lab1\\task3\\wall.png";

	public static final String MOVE_LEFT = "LEFT";
	public static final String MOVE_RIGHT = "RIGHT";
	public static final String MOVE_UP = "UP";
	public static final String MOVE_DOWN = "DOWN";
	public static final String AC_DIRTY = "DIRTY";

	private JPanel panelSquares;

	private JButton btnStart;
	private JSpinner spinnerCol;
	private JSpinner spinnerRow;
	private int row;
	private int col;

	private JLabel[][] squares;

	public View() {
		init();
		addElements();
	}

	public void init() {
		btnStart = new JButton("Start");
		spinnerRow = new JSpinner();
		spinnerCol = new JSpinner();
	}

	public void addElements() {
		Random random = new Random();
		JPanel panelOpe = new JPanel();
		panelSquares = new JPanel();

		panelOpe.add(spinnerRow);
		panelOpe.add(spinnerCol);
		panelOpe.add(btnStart);

		ImageIcon img = new ImageIcon();

		row = (int) spinnerRow.getValue();
		col = (int) spinnerCol.getValue();

		squares = new JLabel[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				JLabel square = new Square(i, j);
				panelSquares.setLayout(new GridLayout(row, col));

				int xs = random.nextInt(10);
				if (xs == 0 || xs == 1) {
					img = new ImageIcon(DIRTY_IMG);
					((Square) square).setDirty(true);
				} else if (xs == 2) {
					img = new ImageIcon(WALL_IMG);
					((Square) square).setWall(true);
					((Square) square).setDirty(false);
				} else {
					img = new ImageIcon(CLEAN_IMG);
					((Square) square).setDirty(false);
				}

				square.setIcon(img);
				((Square) square).setActive(false);

				squares[i][j] = square;

				panelSquares.add(square);
			}
		}

		Dimension screenSize = this.getToolkit().getScreenSize();
		int w = (int) screenSize.getWidth();
		int h = (int) screenSize.getHeight() - 100;

		this.setTitle("Vaccum cleaner");
		this.setSize(w, h);
		this.setLayout(new BorderLayout());
		this.add(panelOpe, BorderLayout.NORTH);
		this.add(panelSquares, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// random trai phai len xuong, neu ban thi cap nhap trang thai
	public String action(int i, int j) {
		Random random = new Random();
		String[] go = { MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT, MOVE_UP };

		boolean isDirty = ((Square) squares[i][j]).isDirty();

		int goIndex = random.nextInt(go.length);

		String res = "";

		if (isDirty) {
			res = AC_DIRTY;
		} else {
			res = go[goIndex];
		}

		return res;
	}

	// thuc hien di trai phai len xuong
	// va cap nhap lai moi truong
	public void move(int i, int j, int maxWitdth, int maxHeight) throws InterruptedException {
		String action = action(i, j);

		System.out.println(i + " " + j + ": " + action);
		if (action.equals(MOVE_LEFT)) {
			if (j == 0 || square(i, j - 1).isWall()) {
				square(i, j).setIcon(new ImageIcon(AGENT_IMG));
			} else {
				square(i, j).setActive(false);
				square(i, j).setIcon(new ImageIcon(CLEAN_IMG));

				j = j - 1;
				square(i, j).setActive(true);
				square(i, j).setIcon(new ImageIcon(AGENT_IMG));
			}
		} else if (action.equals(MOVE_RIGHT)) {
			if (j == maxWitdth || square(i, j + 1).isWall()) {
				square(i, j).setIcon(new ImageIcon(AGENT_IMG));
			} else {
				square(i, j).setActive(false);
				square(i, j).setIcon(new ImageIcon(CLEAN_IMG));

				j = j + 1;
				square(i, j).setActive(true);
				square(i, j).setIcon(new ImageIcon(AGENT_IMG));
			}
		} else if (action.equals(MOVE_UP)) {
			if (i == 0 || square(i - 1, j).isWall()) {
				square(i, j).setIcon(new ImageIcon(AGENT_IMG));
			} else {
				square(i, j).setActive(false);
				square(i, j).setIcon(new ImageIcon(CLEAN_IMG));

				i = i - 1;
				square(i, j).setActive(true);
				square(i, j).setIcon(new ImageIcon(AGENT_IMG));
			}
		} else if (action.equals(MOVE_DOWN)) {
			if (i == maxHeight || square(i + 1, j).isWall()) {
				square(i, j).setIcon(new ImageIcon(AGENT_IMG));
			} else {
				square(i, j).setActive(false);
				square(i, j).setIcon(new ImageIcon(CLEAN_IMG));

				i = i + 1;
				square(i, j).setActive(true);
				square(i, j).setIcon(new ImageIcon(AGENT_IMG));
			}
		} else if (action.equals(AC_DIRTY)) {
			square(i, j).setDirty(false);
		}
	}

	// o vuong o vi tri dong i cot j
	public Square square(int i, int j) {
		return (Square) squares[i][j];
	}

	// agent bat dau chay tu vi tri [0, 0]
	// khi nao sach het thi ngung
	public void step() throws InterruptedException {
		int row = (int) spinnerRow.getValue();
		int col = (int) spinnerCol.getValue();

		int[] agentLocation = agentLocation();
		move(agentLocation[0], agentLocation[1], col - 1, row - 1);

	}

	// kiem tra tat ca cac o deu sach
	public boolean isClean() {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				if (((Square) squares[i][j]).isDirty()) {
					return false;
				}
			}
		}
		return true;
	}

	// tra ve vi tri robot dang dung
	public int[] agentLocation() {
		int[] res = new int[2];

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				// neu vi tri do cua agent thi tra ve mang [i, j]
				if (((Square) squares[i][j]).isActive()) {
					res[0] = i;
					res[1] = j;
					return res;
				}
			}
		}

		return res;
	}

	// tao cac vi tri
	public void createSquare(int row, int col) {
		this.row = row;
		this.col = col;
		addElements();
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public JSpinner getSpinnerCol() {
		return spinnerCol;
	}

	public JSpinner getSpinnerRow() {
		return spinnerRow;
	}

	public JLabel[][] getSquares() {
		return squares;
	}

}
