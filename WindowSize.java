package lifegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowSize extends JDialog implements ActionListener{
	  JTextField text1;
	  JTextField text2;	
	
	public WindowSize(JFrame owner) {
		super(owner);
		getContentPane().setLayout(new FlowLayout());
		JPanel panel = new JPanel();
	    text1 = new JTextField("height", 10);
	    text2 = new JTextField("width", 10);		
		JButton btn = new JButton("SizeChange");
		btn.addActionListener(this);
	    panel.add(text1, BorderLayout.EAST);
	    panel.add(text2, BorderLayout.WEST);
	    getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(btn, BorderLayout.SOUTH);
		setTitle("Change Window Size.");
		setSize(200, 150);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
