import java.awt.FlowLayout;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
/**
 * 
 * @author Arvind Nambakam
 * @version final
 */
public class Life {
	
	private static final int row = 19;
	private static final int column = 19;
	private int [][] lifeboard =	 {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
			{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};
	
		
	@Override
	//gameboard int to string
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (lifeboard[i][j] == 0) {
					sb.append(". ");
				} else if (lifeboard[i][j] == 1) {
					sb.append("O ");
				}
			}
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	//updates generation based on 4 factors
	public int[][] updateGeneration() {
		//new copy of board to reflect changes
		int [][] boardcopy = new int [row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				boardcopy[i][j] = lifeboard[i][j];
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {				
				int count = countone(i, j);				
				if (lifeboard[i][j] == 0) {
					//dead cell
					if (count == 3) {
						//make it alive
						boardcopy[i][j]= 1;
					} 
				} else {
					//live cell
					if (count <= 1) {
						//loneliness
						boardcopy[i][j] = 0;
					} else if (count >= 4) {
						//overcrowding
						boardcopy[i][j] = 0;
					} 
				}				
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				lifeboard[i][j] = boardcopy[i][j];
			}
		}
		
		return lifeboard;
	}
	
	//counts number of ones around a cell
	public int countone(int x, int y) {
		int count = 0;
		if ((x - 1) >= 0 && (x - 1) < row) {
			//top left
			if ((y - 1) >= 0 && (y - 1) < column) {
				if (lifeboard[x-1][y-1] == 1) {
					count++;
				}
			}
			//top middle
			if ( y >= 0 && y < column) {
				if (lifeboard[x-1][y] == 1) {
					count++;
				}
			}
			//top right
			if ((y + 1) >= 0 && (y + 1) < column) {
				if (lifeboard[x-1][y+1] == 1) {
					count++;
				}
			}
		}
		//left
		if (x >= 0 && x < row && (y - 1) >= 0 && (y - 1) < column) {
			if (lifeboard[x][y-1] == 1) {
				count++;
			}
		}
		//right
		if (x >= 0 && x < row && (y + 1) >= 0 && (y + 1) < column) {
			if (lifeboard[x][y+1] == 1) {
				count++;
			}
		}
		if ((x + 1) >= 0 && (x + 1) < row) {
			//bottom left
			if ((y - 1) >=0 && (y - 1) < column) {
				if (lifeboard[x+1][y-1] == 1) {
					count++;
				}
			}
		
			//bottom
			if (y >= 0 && y  < column) {
				if (lifeboard[x+1][y] == 1) {
					count++;
				}
			}
			//bottom right
			if ((y + 1) >= 0 && (y + 1) < column) {
				if (lifeboard[x+1][y+1] == 1) {
					count++;
				}
			}
		}
		return count;
	}
	
	public int[][] getGeneration() {
		return lifeboard;
	}
		
	public static void main(String[] args) {
		Life game = new Life();
		javax.swing.JFrame frame;
		frame = new javax.swing.JFrame("Life");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		LifeWindow window = new LifeWindow(frame, game.getGeneration());
		window.setVisible(true);
		//frame.add(window);
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		while (true) {
			int input = JOptionPane.showConfirmDialog(null, "Do you want to see the next generation?");
			//0 = yes, 1 = no, 2 = cancel
			if(input != 0) {
				break;
			} 
			
			window.updateWindow(game.updateGeneration());
		}				
	}
}
