package lab1.task3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller implements ActionListener, ChangeListener {
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		view.getBtnStart().addActionListener(this);
		view.getSpinnerRow().addChangeListener(this);
		view.getSpinnerCol().addChangeListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();

		if (view.getBtnStart().equals(src)) {
			try {
				if(!view.isClean()) {
					view.step();
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		int row = (int) view.getSpinnerRow().getValue();
		int col = (int) view.getSpinnerCol().getValue();

		if (row > 0 && col > 0) {
			view.createSquare((int) row, (int) col);
			setCleanerStart();
		}
	}

	public void setCleanerStart() {
		view.getSquares()[0][0].setIcon(new ImageIcon(View.AGENT_IMG));
		((Square) view.getSquares()[0][0]).setActive(true);
	}
}
