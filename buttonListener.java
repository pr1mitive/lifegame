package lifegame;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;

public class buttonListener extends JPanel implements ActionListener {
	private BoardModel model;
	private BoardView view;
	public Main main;
	private JButton nextButton = new JButton();
	public JButton undoButton = new JButton();
	private JButton newButton = new JButton();
	private JButton autoButton = new JButton();
	private boolean autoFlag = false;
	
	public buttonListener(BoardModel Model, BoardView View, Main Main) {
		model = Model;
		view = View;
		main = Main;
		nextButton.addActionListener(this);
		nextButton.setText("Next");
		undoButton.addActionListener(this);
		undoButton.setText("Undo");
		undoButton.setEnabled(false);
		newButton.addActionListener(this);
		newButton.setText("New Game");
		autoButton.addActionListener(this);
		autoButton.setText("Auto");
		this.add(nextButton);
		this.add(undoButton);
		this.add(autoButton);
		this.add(newButton);
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == nextButton){
			model.next();
			view.repaint();
			undoButton.setEnabled(true);
		}
		else if(e.getSource() == undoButton){
			model.undo();
			view.repaint();
			if(!model.isUndoable()) {
				undoButton.setEnabled(false);
			}
		}
		else if(e.getSource() == newButton){
			Main main = new Main();
			main.run();
		}
		else if(e.getSource() == autoButton){
			if(!autoFlag) {
				main.auto.main();
				autoFlag = true;
			}
			else {
				autoFlag = false;
				main.auto.thread.stop();	
			}
		}
	}
}
