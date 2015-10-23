import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Simulator extends JFrame implements MouseListener, KeyListener {

	private final static int NUM_ROWS = 20;
	private final static int NUM_COLS = 20;
	private final static int NUM_VISIBLE_ROWS = NUM_ROWS - 2;
	private final static int NUM_VISIBLE_COLS = NUM_COLS - 2;

	private static final int TOP_OF_SCREEN = 22; 
	private final static int CELL_WIDTH = 30;
	private final static int CELL_HEIGHT = 30;

	private final static int WINDOW_WIDTH = (NUM_VISIBLE_COLS) * CELL_WIDTH + 1;
	private final static int WINDOW_HEIGHT = (NUM_VISIBLE_ROWS) * CELL_HEIGHT + TOP_OF_SCREEN + 1;
	private static boolean[][] aliveCells = new boolean[NUM_ROWS][NUM_COLS];

	public void Simulator()
	{
		Simulator sim = new Simulator();
		
		addMouseListener(sim);
		addKeyListener(sim);
		
		sim.addMouseListener(sim);
		sim.addKeyListener(sim);
	}

	/**
	 * Class Comment
	 * @param args
	 */
	public static void main(String[] args)
	{	
		//Set up window
		Simulator sim = new Simulator();
		sim.setBackground(Color.WHITE);
		sim.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		sim.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		sim.setVisible(true);

		//Other Stuff
		sim.addMouseListener(sim);
		sim.addKeyListener(sim);
	}




	public void performSimulation(int numGenerations)
	{
		int [][] numNeighbors = new int[NUM_ROWS][NUM_COLS];
		for (int gen = 0; gen < numGenerations; gen++)
		{

			// Simulation Logic
			numNeighbors = getNumNeighbors();

			for (int row = 1; row < NUM_ROWS - 1; row++)
			{
				for (int col = 1; col < NUM_COLS - 1; col++)
				{
					if (numNeighbors[row][col] == 3)
					{
						// Cell Becomes Alive
						aliveCells[row][col] = true;
					}
					else if (numNeighbors[row][col] == 2)
					{
						// Remains the Same
						aliveCells[row][col] = aliveCells[row][col];
					}
					else
					{
						// Cell Dies
						aliveCells[row][col] = false;
					}
				}
			}
			repaint();
		}
	}

	public static int[][] getNumNeighbors()
	{
		int[][] numNeighbors = new int[NUM_ROWS][NUM_COLS];
		for (int row = 1; row < NUM_ROWS - 1; row++)
		{
			for (int col = 1; col < NUM_COLS - 1; col++)
			{
				int count = 0;
				count += numNeighborsAbove(row, col);
				count += numNeighborsBelow(row, col);
				count += numNeighborsRight(row, col);
				count += numNeighborsLeft(row, col);

				numNeighbors[row][col] = count;
			}
		}

		return numNeighbors;
	}

	public static int numNeighborsAbove(int row, int col)
	{
		int count = 0;
		for (int cell = col - 1; cell <= col + 1; cell++)
		{
			if (aliveCells[row - 1][cell])
			{
				count++;
			}
		}
		return count;
	}

	public static int numNeighborsBelow(int row, int col)
	{
		int count = 0;
		for (int cell = col - 1; cell <= col + 1; cell++)
		{
			if (aliveCells[row + 1][cell])
			{
				count++;
			}
		}
		return count;
	}

	public static int numNeighborsRight(int row, int col)
	{
		int count = 0;

		if (aliveCells[row][col + 1])
		{
			count++;
		}

		return count;
	}

	public static int numNeighborsLeft(int row, int col)
	{
		int count = 0;

		if (aliveCells[row][col - 1])
		{
			count++;
		}

		return count;
	}

	public void paint (Graphics g)
	{
		g.clearRect(0, TOP_OF_SCREEN, WINDOW_WIDTH, WINDOW_HEIGHT);
		g.setColor(Color.BLACK);
		for (int row = 1; row < NUM_ROWS - 1; row++)
		{
			for (int col = 1; col < NUM_COLS - 1; col++)
			{
				int xPosition = (col - 1) * CELL_WIDTH;
				int yPosition = (row - 1) * CELL_HEIGHT + TOP_OF_SCREEN;
				if (aliveCells[row][col])
				{
					g.fillRect(xPosition, yPosition, CELL_WIDTH, CELL_HEIGHT);
				}
				else
				{
					g.drawRect(xPosition , yPosition , CELL_WIDTH , CELL_HEIGHT); 
				}
			}
		}
	}




	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == 'R')
		{
			System.out.println("Running...");
			performSimulation(1);
		}

	}




	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}




	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}




	@Override
	public void mouseClicked(MouseEvent e) {
		int xPos = e.getX();
		int yPos = e.getY();

		System.out.println("Mouse Clicked");

		for (int row = 1; row <= NUM_VISIBLE_ROWS; row++)
		{
			for (int col = 1; col <= NUM_VISIBLE_COLS; col++)
			{
				int xPosition = (col - 1) * CELL_WIDTH;
				int yPosition = (row - 1) * CELL_HEIGHT + TOP_OF_SCREEN;

				if (xPos >= xPosition && xPos <= xPosition + CELL_WIDTH &&
						yPos >= yPosition && yPos <= yPosition + CELL_HEIGHT)
				{
					aliveCells[row][col] = true;
					System.out.println(row + " " + col);
				}
			}
		}
		repaint();

	}




	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}




	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}




	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}




	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
