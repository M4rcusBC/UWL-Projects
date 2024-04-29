import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * A class for allowing a user to color a picture by choosing a row/col value
 *   and then recoloring all the adjacent positions using either a stack,
 *   a queue, or recursion.
 * 
 * @author asauppe
 */
public class Filling {
	
	public static void main(String[] args) {
		
		// reads in the "picture" from the file
		int[][] picture = readPicture("/workspaces/UWL-Projects/Spring2024/CS220/Assignments/Assign05/image.pgm");
		
		// array with enums representing each of the three techniques you will implement
		Technique[] techniques = {Technique.STACK, Technique.RECURSIVE, Technique.QUEUE};
		
		// for each technique...
		for(Technique t : techniques) {
			// print the technique
			System.out.println(t);
			
			// make a deep copy of the array
			int[][] toUse = deepCopy(picture);
			
			// color the pixel at 2, 2 and all contiguous pixels of the same color to 9
			colorPicture(toUse, new Coordinate(2, 2), 9, t);
			
			// write picture to file, prepended with the technique
			writePicture(toUse, t+".pgm");
		}
	}
	
	/**
	 * Colors the picture starting at the specified coordinate, exploring all adjacent coordinates
	 * 
	 * @param picture The int[][] picture to color
	 * @param coor The coordinate that was "clicked"
	 * @param newColor The new color
	 * @param t The technique to use
	 * @throws IndexOutOfBoundsException
	 */
	public static void colorPicture(int[][] picture, Coordinate coor, int newColor, Technique t) throws IndexOutOfBoundsException {
		
		if(coor.getRow() < 0 || coor.getCol() < 0 || coor.getRow() > picture.length-1 || coor.getCol() > picture[0].length-1) {
			throw new IndexOutOfBoundsException();
		}
		
		if(t == Technique.STACK) {
			stackColor(picture, coor, picture[coor.getRow()][coor.getCol()], newColor);
		} else if(t == Technique.RECURSIVE) {
			recursiveColor(picture, coor, picture[coor.getRow()][coor.getCol()], newColor);
		} else if(t == Technique.QUEUE) {
			queueColor(picture, coor, picture[coor.getRow()][coor.getCol()], newColor);
		}
		
	}
	
	/**
	 * A method that will color the given pixel (i.e., coor) and all surrounding pixels that are of coloring to newColor using a stack.
	 * 
	 * @param picture The pixels that make up the picture, represented as a 2D array of ints
	 * @param coor The row/column pixel that was "clicked"
	 * @param coloring The original color of the pixel that was "clicked"
	 * @param newColor The color we are coloring to
	 */
	private static void stackColor(int[][] picture, Coordinate coor, int coloring, int newColor) {
		
		
		
	}
	
	/**
	 * A method that will color the given pixel (i.e., coor) and all surrounding pixels that are of coloring to newColor using a queue.
	 * 
	 * @param picture The pixels that make up the picture, represented as a 2D array of ints
	 * @param coor The row/column pixel that was "clicked"
	 * @param coloring The original color of the pixel that was "clicked"
	 * @param newColor The color we are coloring to
	 */
	private static void queueColor(int[][] picture, Coordinate coor, int coloring, int newColor) {
		
		
		
	}
	
	/**
	 * A method that will color the given pixel (i.e., coor) and all surrounding pixels that are of coloring to newColor using recursion.
	 * 
	 * @param picture The pixels that make up the picture, represented as a 2D array of ints
	 * @param coor The row/column pixel that was "clicked"
	 * @param coloring The original color of the pixel that was "clicked"
	 * @param newColor The color we are coloring to
	 */
	private static void recursiveColor(int[][] picture, Coordinate coor, int coloring, int newColor) {
		
		
		
	}
	
	/**
	 * A helper method you may find useful to print your picture as your program execute for debugging purposes.
	 * 
	 * @param picture The pixels that make up the picture, represented as a 2D array of ints
	 */
	private static void printPicture(int[][] picture) {
		System.out.print(" ");
		
		for(int i = 0; i < picture[0].length; i++) {
			if(i < 10) {
				System.out.print(" ");
			}
			System.out.print("  " + i);
		}
		
		System.out.println();
		
		for(int i = 0; i < 1 + picture[0].length*4; i++) {
			if(i < 2) {
				System.out.print(" ");
			} else {
				System.out.print("-");
			}
		}
		
		System.out.println();
		
		for(int i = 0; i < picture.length; i++) {
			if(i < 10) {
				System.out.print(" ");
			}
			System.out.print(i + "| ");
				
			for(int j = 0; j < picture[i].length; j++) {
				if(picture[i][j] < 10) {
					System.out.print(" ");
				}
				System.out.print(picture[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Creates a deep copy of the given 2D array, i.e., a new 2D array of values not related to the argument
	 * 
	 * DO NOT USE THIS METHOD
	 * 
	 * @param toCopy The array to copy.
	 * @return
	 */
	private static int[][] deepCopy(int[][] toCopy) {
		int[][] copy = new int[toCopy.length][toCopy[0].length];
		
		for(int i = 0; i < toCopy.length; i++) {
			for(int j = 0; j < toCopy[i].length; j++) {
				copy[i][j] = toCopy[i][j];
			}
		}
		
		return copy;
	}

	/**
	 * Reads in the given file to a 2D array of ints, i.e., the picture.
	 * 
	 * @param file The text file to read from.
	 * @return
	 */
	private static int[][] readPicture(String file) {
		int[][] picture = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String firstLine = br.readLine().trim();
			String[] secondLine = br.readLine().trim().split(" ");
			int width = Integer.parseInt(secondLine[0]);
			int height = Integer.parseInt(secondLine[1]);
			final int maxVal = Integer.parseInt(br.readLine().trim());
			picture = new int[height][width];

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					picture[i][j] = (br.read());
				}
				br.readLine();
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return picture;

	}
	
	/**
	 * Writes the given picture (i.e., 2D array) to the given text file. The format is as follows:
	 * 
	 * P2
	 * width height
	 * 18
	 * pixels written out, row by row
	 * 
	 * @param picture The pixels that make up the picture, represented as a 2D array of ints
	 * @param file The file to write to
	 */
	private static void writePicture(int[][] picture, String file) {
		
	}
	
}
