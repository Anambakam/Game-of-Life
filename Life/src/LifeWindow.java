import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;
import java.awt.*;

public class LifeWindow extends javax.swing.JComponent{
	
	private javax.swing.JPanel [][]board; 
	
	public LifeWindow (JFrame frame, int [][]lifeboard) {
		
		int length = lifeboard.length;
		int width = lifeboard[0].length;
		javax.swing.JPanel mainPanel = new javax.swing.JPanel(new GridLayout(length,width));
		board = new javax.swing.JPanel[length][width];
		//border for grid containing lifeboard
		Border blackline = BorderFactory.createLineBorder(Color.black);
		//iterates through lifeboard and sets colors according to layout
		for (int i = 0; i < lifeboard.length; i++) {
			for (int j = 0; j < lifeboard[i].length; j++) {
				board[i][j]= new javax.swing.JPanel(new GridLayout(length,width,1, 1));
				if (lifeboard[i][j] == 0) {
					board[i][j].setBackground(java.awt.Color.white);
				} else {
					board[i][j].setBackground(java.awt.Color.blue);
				}
				mainPanel.add(board[i][j]);
				board[i][j].setBorder(blackline);					
			}
		}
		frame.add(mainPanel);
	}	
		
	public void updateWindow(int[][]lifeboard) {
		//goes through lifeboard and looks for 0's or 1's
		for (int i = 0; i < lifeboard.length; i++) {
			for (int j = 0; j < lifeboard[i].length; j++) {
					//dead cell
				if (lifeboard[i][j] == 0) {
					board[i][j].setBackground(java.awt.Color.white);
					//alive cell
				} else {
					board[i][j].setBackground(java.awt.Color.blue);
				}				
			}
		}
	}
	
}
