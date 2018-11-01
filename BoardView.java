package lifegame;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Point;

public class BoardView extends JPanel implements MouseListener, MouseMotionListener{
	private BoardModel model;
	private int[] beforeChange = new int[2];
	
	public BoardView(BoardModel Model) {
		model = Model;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public boolean isAlive(int x, int y) {
		if(model.cells[y][x]) return true;
		else return false; 
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); 
		int xInterval = this.getWidth()/model.cells[0].length;
		int yInterval = this.getHeight()/model.cells.length;
		g.drawLine(0, 0, model.cells[0].length*xInterval, 0);
		g.drawLine(0, 0, 0, model.cells.length*yInterval);		
		for(int i = 1; i <= model.cells.length; i++) {
			g.drawLine(0, i*yInterval, model.cells[0].length*xInterval, i*yInterval);
		}
		for(int i = 1; i <= model.cells[0].length; i++) {
			g.drawLine(i*xInterval, 0, i*xInterval, model.cells.length*yInterval);			
		}
		for(int i = 0; i < model.cells.length; i++) {
			for(int j = 0; j < model.cells[0].length; j++) {
				if(isAlive(j, i)) g.fillRect(j*xInterval, i*yInterval, xInterval, yInterval);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	
	public void mouseExited(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
		int xInterval = this.getWidth()/model.cells[0].length;
		int yInterval = this.getHeight()/model.cells.length;
		int x = e.getX() / xInterval;
		int y = e.getY() / yInterval;
		if(e.getX() >= model.cells[0].length*xInterval|| e.getY() >= model.cells.length*yInterval) {
			return;
		}
		else if(e.getX() < 0 || e.getY() < 0) {
			return;
		}
		beforeChange[0] = x;
		beforeChange[1] = y;
		model.changeCellState(x, y);
		repaint();
	}
	
	public void mouseReleased(MouseEvent e) {
	}
	
	public void mouseDragged(MouseEvent e) {
	    Point point = e.getPoint();
		int xInterval = this.getWidth()/model.cells[0].length;
		int yInterval = this.getHeight()/model.cells.length;
		int x = point.x / xInterval;
		int y = point.y / yInterval;
		if(point.x >= model.cells[0].length*xInterval|| point.y >= model.cells.length*yInterval) {
			return;
		}
		if(point.x <= 0 || point.y <= 0) {
			return;
		}
		if(beforeChange[0] != x || beforeChange[1] != y) {
			beforeChange[0] = x;
			beforeChange[1] = y;
			model.changeCellState(x, y);			
		}
		repaint();
	}
	
	public void mouseMoved(MouseEvent e) {
	}

}
