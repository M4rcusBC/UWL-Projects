package Spring2024.CS220.Labs.Lab06;

/**
 * CS 220: Lab 06 Template for Exercise 2
 * This program demonstrates the use of recursion to draw a spiral pattern.
 * The drawSpiral() method draws a spiral in the specified direction by
 * recursively calling the drawSegment() method. The drawSegment() method
 * draws a line segment in the specified direction and then calls itself
 * to draw the next line segment until the specified depth is reached.
 *
 * @author J. Hursey, J. Sauppe, M. Clements
 * Last Modified: 04-16-2024
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

        int tempX = xPos; // Temporary X position for this segment
        int tempY = yPos; // Temporary Y position for this segment
        int direction = dir; // Direction to draw this segment in;

        // Set the color based on the number of segments remaining
        Color color = (numSegmentsRemaining % 2 == 0) ? evenColor : oddColor;

        if (numSegmentsRemaining > 0) {

            switch (direction) {
                case DIR_UP:
                    tempY -= len; // Move upwards
                    direction = DIR_RIGHT;
                    break;
                case DIR_RIGHT:
                    tempX += len; // Move to the right
                    direction = DIR_DOWN;
                    break;
                case DIR_DOWN:
                    tempY += len; // Move downwards
                    direction = DIR_LEFT;
                    break;
                case DIR_LEFT:
                    tempX -= len; // Move to the left
                    direction = DIR_UP;
                    break;
            } // I realize this could be done with an enhanced switch statement, but we have not covered lambdas yet in this class

            final int THICKNESS = 5;

            Line line = new Line(xPos, yPos, tempX, tempY, THICKNESS);
            line.setBackground(color);
            window.add(line);
            drawSegment(tempX, tempY, len + 10, direction, numSegmentsRemaining - 1);

        }
    }

}
