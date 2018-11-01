package lifegame;

import java.util.*;

public class BoardModel {
	private int cols;
	private int rows;
	public Boolean[][] cells;
	public ArrayList<ArrayList<List<Boolean>>> cellsLists = new ArrayList<ArrayList<List<Boolean>>>();
	
	public BoardModel(int c, int r) {
		cols = c;
		rows = r;
		cells = new Boolean[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				cells[i][j] = false;
			}
		}
	}
	
	public int getCols() {
		return cols;
	}
	
	public int getRows() {
		return rows;
	}
	
	public void printForDebug() {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[0].length; j++) {
				if(cells[i][j] == true) System.out.print("*");
				else System.out.print(".");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public void changeCellState(int x, int y) {
		if(cells[y][x] == true) cells[y][x] = false;
		else cells[y][x] = true;
	}
	
	
	public synchronized void next() {
		ArrayList<List<Boolean>> colsCells = new ArrayList<List<Boolean>>();
		for(int i = 0; i < cells.length; i++) {
			List<Boolean> rowsCells = new ArrayList<Boolean>(Arrays.asList(cells[i]));
			colsCells.add(rowsCells);
		}
		if(cellsLists.size() >= 32) cellsLists.remove(0);
		cellsLists.add(colsCells);
		boolean[][] bigCells;
		bigCells = new boolean[cells.length + 2][cells[0].length + 2];
		for(int i = 0; i < cells.length + 2; i++) {
			for(int j = 0; j < cells[0].length + 2; j++) {
				if(i == 0 || j == 0 || i == cells.length + 1 || j == cells[0].length + 1) bigCells[i][j] = false;
				else bigCells[i][j] = cells[i-1][j-1];
			}
		}
		for(int i = 1; i < cells.length + 1; i++) {
			for(int j = 1; j < cells[0].length + 1; j++) {
				int trueCount = 0;
				for(int k = -1; k < 2; k++) {
					for(int l = -1; l < 2; l++) {
						if(bigCells[i + k][j + l]) trueCount++;
					}
				}
				if(!bigCells[i][j] && trueCount == 3) cells[i-1][j-1] = true;
				else if(bigCells[i][j] && (trueCount > 4 || trueCount < 3)) cells[i-1][j-1] = false;
			}
		}
	}
	
	public void undo() {
		ArrayList<List<Boolean>> colsCells = new ArrayList<List<Boolean>>();
		colsCells = cellsLists.get(cellsLists.size() - 1);
		for(int i = 0; i < cells.length; i++) {
			cells[i] = colsCells.get(i).toArray(new Boolean[colsCells.get(i).size()]);
		}
		cellsLists.remove(cellsLists.size() - 1);
	}
	
	public boolean isUndoable() {
		if(cellsLists.size() < 1) return false;
		else return true;
	}
}
