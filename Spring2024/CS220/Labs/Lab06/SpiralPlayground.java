/**
 * CS 220: Lab 06 Template for Exercise 2
 * DESCRIPTION OF THE PROGRAM HERE
 *
 * @author J. Hursey, J. Sauppe, YOUR NAME HERE
 * Last Modified: DATE LAST MODIFIED HERE
 */
import java.awt.Color;
import javax.swing.JFrame;

public class SpiralPlayground {

    /*
     * Direction values to indicate the current drawing direction
     */
    private final int DIR_UP    = 0;
    private final int DIR_RIGHT = 1;
    private final int DIR_DOWN  = 2;
    private final int DIR_LEFT  = 3;

    // Color to draw the line segment when the numSegmentsRemaining is even or odd
    private final Color oddColor = Color.RED.brighter();
    private final Color evenColor = Color.BLUE.darker();

    /** Window to draw */
    private JFrame window;

    /**
     * Main method for kick-starting the program.
     *
     * @param args Command-line arguments (not used here)
     */
	public static void main(String[] args) {
        System.out.println("Testing Spiral():");
        System.out.println("----------------------------");
        SpiralPlayground spinny = new SpiralPlayground(500, 500);
        spinny.drawSpiral(100, 100, 5);
        spinny.drawSpiral(300, 100, 10);
        spinny.drawSpiral(250, 300, 27);
	}

    /**
     * Setup the drawing window
     * 
     * @param width Width of the window
     * @param height Height of the window
     */
    public SpiralPlayground(int width, int height) {

    	window = new JFrame("Spiral Playground");
        window.setLayout(null);
        window.getContentPane().setBackground( Color.WHITE );
        window.setResizable(false);
        window.setBounds(10, 10, width, height + window.getInsets().top);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make window visible
        window.setVisible( true );
    }

    /**
     * Clear the window
     */
    public void clear() {
        window.removeAll();
    }

    /**
     * Draw a spiral at the specified location
     *
     * @param xPos X position of the starting point
     * @param yPos Y position of the starting point
     * @param depth Number of line segments to draw spiraling outward
     */
    public void drawSpiral(int xPos, int yPos, int depth) {
        drawSegment(xPos, yPos, 10, DIR_RIGHT, depth);
    }

    /**
     * Recursive method that draws the spiral one segment at a time.
     * Each line segment is 10 pixels longer than the previous line
     * segment.
     *
     * Use the Line class to create a line object then add it to the window.
     * Below is an example of how to create a line object (with thickness 5).
     *   new Line(xStart, yStart, xEnd, yEnd, 5);
     *
     * @param xPos X position of the starting point
     * @param yPos Y position of the starting point
     * @param len Length of the line to draw
     * @param dir Direction to draw the line segment
     * @param numSegmentsRemaining Number of segments remaining to draw.
     */
    private void drawSegment(int xPos, int yPos, int len, int dir, int numSegmentsRemaining) {
        // TODO Write me
    }

}
