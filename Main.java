package lifegame;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;


public class Main extends WindowAdapter implements Runnable{
	public autoButton auto;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main());
	}
	
	@Override
	public void run() {
		BoardModel model = new BoardModel(10, 10);
		JFrame frame = new JFrame("Lifegame");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(this);
		JPanel base = new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(400, 300)); 
		frame.setMinimumSize(new Dimension(300, 200)); 
		base.setLayout(new BorderLayout()); 		
		BoardView view = new BoardView(model);
		base.add(view, BorderLayout.CENTER); 
		JPanel buttonPanel = new JPanel();
		buttonListener button = new buttonListener(model, view, this);
		base.add(button, BorderLayout.SOUTH);
		auto = new autoButton(model, view, button);
		buttonPanel.setLayout(new FlowLayout()); 
		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
        JMenu menu = new JMenu("File");
        menubar.add(menu);
        MenuBar menuItem = new MenuBar(model, menu);
		frame.pack(); 
		frame.setVisible(true); 
	}

	public void windowClosed(WindowEvent e){
		if(auto.flag == true) {
			auto.thread.stop();			
		}
	}

}

