package Spring2024.CS220.Assignments.Assign05;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * A class for allowing a user to color a picture by choosing a row/col value
 * and then recoloring all the adjacent positions using either a stack,
 * a queue, or recursion.
 *
 * @author asauppe, Marcus Clements
 */
public class Filling {

    public static void main(String[] args) {

        // reads in the "picture" from the file
        int[][] picture = readPicture("Spring2024/CS220/Assignments/Assign05/image.pgm");

        // array with enums representing each of the three techniques you will implement
        Technique[] techniques = {Technique.STACK, Technique.RECURSIVE, Technique.QUEUE};

        // for each technique...
        for (Technique t : techniques) {
            // print the technique
            System.out.println(t);

            // make a deep copy of the array
            int[][] toUse = deepCopy(picture);

            // color the pixel at 2, 2 and all contiguous pixels of the same color to 9
            colorPicture(toUse, new Coordinate(2, 2), 9, t);

            printPicture(toUse);

            // write picture to file, prepended with the technique
            writePicture(toUse, t + ".pgm");
        }
    }

    /**
     * Colors the picture starting at the specified coordinate, exploring all adjacent coordinates
     *
     * @param picture  The int[][] picture to color
     * @param coor     The coordinate that was "clicked"
     * @param newColor The new color
     * @param t        The technique to use
     * @throws IndexOutOfBoundsException
     */
    public static void colorPicture(int[][] picture, Coordinate coor, int newColor, Technique t) throws IndexOutOfBoundsException {

        if (coor.getRow() < 0 || coor.getCol() < 0 || coor.getRow() > picture.length - 1 || coor.getCol() > picture[0].length - 1) {
            throw new IndexOutOfBoundsException();
        }

        if (t == Technique.STACK) {
            stackColor(picture, coor, picture[coor.getRow()][coor.getCol()], newColor);
        } else if (t == Technique.RECURSIVE) {
            recursiveColor(picture, coor, picture[coor.getRow()][coor.getCol()], newColor);
        } else if (t == Technique.QUEUE) {
            queueColor(picture, coor, picture[coor.getRow()][coor.getCol()], newColor);
        }

    }

    /**
     * A method that will color the given pixel (i.e., coor) and all surrounding pixels that are of coloring to newColor using a stack.
     *
     * @param picture  The pixels that make up the picture, represented as a 2D array of ints
     * @param coor     The row/column pixel that was "clicked"
     * @param coloring The original color of the pixel that was "clicked"
     * @param newColor The color we are coloring to
     */
    private static void stackColor(int[][] picture, Coordinate coor, int coloring, int newColor) {

        int height = picture.length;
        int width = picture[0].length;

        Stack<Coordinate> stack = new Stack<>();
        stack.push(coor);

        while (!stack.isEmpty()) {
            Coordinate current = stack.pop();
            int row = current.getRow();
            int col = current.getCol();

            picture[row][col] = newColor;

            // Check neighbors
            if (row - 1 >= 0 && picture[row - 1][col] == coloring) {
                stack.push(new Coordinate(row - 1, col));
            }
            if (row + 1 < height && picture[row + 1][col] == coloring) {
                stack.push(new Coordinate(row + 1, col));
            }
            if (col - 1 >= 0 && picture[row][col - 1] == coloring) {
                stack.push(new Coordinate(row, col - 1));
            }
            if (col + 1 < width && picture[row][col + 1] == coloring) {
                stack.push(new Coordinate(row, col + 1));
            }
        }
    }

    /**
     * A method that will color the given pixel (i.e., coor) and all surrounding pixels that are of coloring to newColor using a queue.
     *
     * @param picture  The pixels that make up the picture, represented as a 2D array of ints
     * @param coor     The row/column pixel that was "clicked"
     * @param coloring The original color of the pixel that was "clicked"
     * @param newColor The color we are coloring to
     */
    private static void queueColor(int[][] picture, Coordinate coor, int coloring, int newColor) {

        int height = picture.length;
        int width = picture[0].length;

        Queue<Coordinate> queue = new LinkedList<>();
        queue.offer(coor);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            int row = current.getRow();
            int col = current.getCol();

            picture[row][col] = newColor;

            if (row - 1 >= 0 && picture[row - 1][col] == coloring) {
                queue.offer(new Coordinate(row - 1, col));
            }
            if (row + 1 < height && picture[row + 1][col] == coloring) {
                queue.offer(new Coordinate(row + 1, col));
            }
            if (col - 1 >= 0 && picture[row][col - 1] == coloring) {
                queue.offer(new Coordinate(row, col - 1));
            }
            if (col + 1 < width && picture[row][col + 1] == coloring) {
                queue.offer(new Coordinate(row, col + 1));
            }

        }

    }

    /**
     * A method that will color the given pixel (i.e., coor) and all surrounding pixels that are of coloring to newColor using recursion.
     *
     * @param picture  The pixels that make up the picture, represented as a 2D array of ints
     * @param coor     The row/column pixel that was "clicked"
     * @param coloring The original color of the pixel that was "clicked"
     * @param newColor The color we are coloring to
     */
    private static void recursiveColor(int[][] picture, Coordinate coor, int coloring, int newColor) {

        int height = picture.length;
        int width = picture[0].length;

        int row = coor.getRow();
        int col = coor.getCol();

        // Base case: Check if current pixel is within bounds and has the old color
        if (row < 0 || row >= height || col < 0 || col >= width || picture[row][col] != coloring) {
            return;
        }

        // Fill the current pixel with the new color
        picture[row][col] = newColor;

        // Recursively fill the adjacent pixels
        recursiveColor(picture, new Coordinate(row - 1, col), coloring, newColor); // Up
        recursiveColor(picture, new Coordinate(row + 1, col), coloring, newColor); // Down
        recursiveColor(picture, new Coordinate(row, col - 1), coloring, newColor); // Left
        recursiveColor(picture, new Coordinate(row, col + 1), coloring, newColor); // Right
    }

    /**
     * A helper method you may find useful to print your picture as your program execute for debugging purposes.
     *
     * @param picture The pixels that make up the picture, represented as a 2D array of ints
     */
    private static void printPicture(int[][] picture) {
        System.out.print(" ");

        for (int i = 0; i < picture[0].length; i++) {
            if (i < 10) {
                System.out.print(" ");
            }
            System.out.print("  " + i);
        }

        System.out.println();

        for (int i = 0; i < 1 + picture[0].length * 4; i++) {
            if (i < 2) {
                System.out.print(" ");
            } else {
                System.out.print("-");
            }
        }

        System.out.println();

        for (int i = 0; i < picture.length; i++) {
            if (i < 10) {
                System.out.print(" ");
            }
            System.out.print(i + "| ");

            for (int j = 0; j < picture[i].length; j++) {
                if (picture[i][j] < 10) {
                    System.out.print(" ");
                }
                System.out.print(picture[i][j] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * Creates a deep copy of the given 2D array, i.e., a new 2D array of values not related to the argument
     * <p>
     * DO NOT USE THIS METHOD
     *
     * @param toCopy The array to copy.
     * @return
     */
    private static int[][] deepCopy(int[][] toCopy) {
        int[][] copy = new int[toCopy.length][toCopy[0].length];

        for (int i = 0; i < toCopy.length; i++) {
            System.arraycopy(toCopy[i], 0, copy[i], 0, toCopy[i].length);
        }

        return copy;
    }

    /**
     * Reads in the given file to a 2D array of ints, i.e., the picture.
     *
     * @param file The text file to read from.
     * @return The picture as a 2D array of ints.
     */
    private static int[][] readPicture(String file) {
        // Declare the 2D array to hold the picture
        int[][] picture = null;

        try {
            Scanner scan = new Scanner(new FileInputStream(file));

            String firstLine = scan.nextLine().trim();
            String[] secondLine = scan.nextLine().trim().split(" ");
            int width = Integer.parseInt(secondLine[0]);
            int height = Integer.parseInt(secondLine[1]);
            final int maxVal = Integer.parseInt(scan.nextLine().trim());
            picture = new int[height][width];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int val = scan.nextInt();
                    picture[i][j] = (val <= maxVal) ? val : -1;
                }
                if (i < height - 1) scan.nextLine();
                scan.reset();
            }

            scan.close();

        }
        // TODO: Handle exceptions
        catch (Exception e) {
            e.printStackTrace();
        }

        return picture;

    }

    /**
     * Writes the given picture (i.e., 2D array) to the given text file. The format is as follows:
     * <p>
     * P2
     * width height
     * 18
     * pixels written out, row by row
     *
     * @param picture The pixels that make up the picture, represented as a 2D array of ints
     * @param file    The file to write to
     */
    private static void writePicture(int[][] picture, String file) {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            int maxVal = 18;
            writer.write("P2");
            writer.newLine();
            writer.write(picture[0].length + " " + picture.length);
            writer.newLine();
            writer.write(maxVal);
            writer.newLine();

            for (int[] ints : picture) {
                for (int j = 0; j < ints.length; j++) {
                    if (ints[j] < 10) {
                        writer.write(" ");
                    }
                    writer.write(ints[j]);
                    writer.write(" ");
                }
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
