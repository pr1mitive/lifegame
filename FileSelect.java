package lifegame;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.util.*;


public class FileSelect extends JFrame{
		
	public void SaveAsFile(BoardModel model, File saveFile) {
		try {
			FileWriter fw = new FileWriter(saveFile);
			ArrayList<List<Boolean>> colsCells = new ArrayList<List<Boolean>>();
			for(int k = 0; k < model.cellsLists.size(); k++) {
				for(int i = 0; i < model.cells.length; i++) {
					for(int j = 0; j < model.cells[0].length; j++) {
						if(model.cellsLists.get(k).get(i).get(j)) {
							fw.write("*");
						}
						else {
							fw.write("-");
						}
					}
					fw.write("\n");
				}
				fw.write("\n");
			}
			fw.close();
		}
		catch(IOException e) {
	        JLabel label = new JLabel("Error");
	        label.setForeground(Color.RED);
	        JOptionPane.showMessageDialog(this, label);
		}
	}
}
