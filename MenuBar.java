package lifegame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

public class MenuBar extends JMenu implements ActionListener{
	
	private JMenuItem save = new JMenuItem("Save As File");
    private BoardModel model;
	
	public MenuBar(BoardModel Model, JMenu menu) {
		menu.add(save);
		save.addActionListener(this);
		model = Model;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Save As File"){
			JFileChooser filechooser = new JFileChooser();
		    int selected = filechooser.showOpenDialog(this);
		    if (selected == JFileChooser.APPROVE_OPTION){
		    	File file = filechooser.getSelectedFile();
		    	FileSelect fileselect = new FileSelect();
		    	fileselect.SaveAsFile(model, file);
		    }
		    else if (selected == JFileChooser.ERROR_OPTION){
		        JLabel label = new JLabel("Message");
		        label.setForeground(Color.RED);
		        JOptionPane.showMessageDialog(this, label);
		    }
        }
	}		
}
