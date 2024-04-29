package Spring2024.CS220.Labs.Lab07;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        // Program 1: Implementing Queue using Circular Array

        double startTime = System.currentTimeMillis();
        // Create a CircularArrayQueue with initial capacity of 5
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(5);

        // Enqueue elements
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        // Dequeue elements
        System.out.println("Dequeued element: " + queue.dequeue());
        System.out.println("Dequeued element: " + queue.dequeue());

        // Enqueue more elements
        queue.enqueue(60);
        queue.enqueue(70);

        // Dequeue remaining elements
        while (!queue.isEmpty()) {
            System.out.println("Dequeued element: " + queue.dequeue());
        }
        double duration = (System.currentTimeMillis() - startTime);
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("Execution time: " + formatter.format((duration) / 1000d) + " seconds\n");

        // Program 2: Evaluating RPN Expressions with Stack
        System.out.println("RPN Expression Calculator\nDivision in this program follows integer division for simplicity.");
        System.out.println("Enter an RPN expression or 'exit' to quit: ");

        Scanner scan = new Scanner(System.in);
        String expression = scan.nextLine();
        while (!expression.equals("exit")) {
            String result;
            try {
                startTime = System.currentTimeMillis();
                result = RPNCalculator.evaluateRPN(expression);
                duration = (long) (System.currentTimeMillis() - startTime);
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
                break;
            }
            System.out.println(expression.trim() + " -> " + result);
            System.out.println("Evaluation time: " + formatter.format((duration) / 1000d) + " seconds\n");
            System.out.println("Enter another RPN expression or 'exit' to quit: ");
            expression = scan.nextLine();
        }
        scan.close();
    }
}