package lifegame;

import java.lang.Runnable;
import java.awt.event.*;

public class autoButton extends WindowAdapter implements Runnable{
	private BoardModel model;
	private BoardView view;
	private buttonListener button;
	public Thread thread;
	public boolean flag = false;
	
	public autoButton(BoardModel Model, BoardView View, buttonListener Button) {
		model = Model;
		view = View;
		button = Button;
	}
	
	public void main() {
		flag = true;
		autoButton auto = new autoButton(model, view, button);
		thread = new Thread(auto);
		thread.start();
	}	

    @Override
	public void run() {
		try{
			do {
				Thread.sleep(1000);
				model.next();
				view.repaint();
				button.undoButton.setEnabled(true);
				
			}
			while(true);
			}catch (InterruptedException e){
		}
	}
}
