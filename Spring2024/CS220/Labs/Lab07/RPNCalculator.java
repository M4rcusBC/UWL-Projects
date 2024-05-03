package Spring2024.CS220.Labs.Lab07;

import java.util.Stack;

/**
 * RPNCalculator class to evaluate Reverse Polish Notation (RPN) expressions.
 * The class provides a static method evaluateRPN that takes an RPN expression
 * as a string and returns the result of the expression. The RPN expression is 
 * a string of space-separated tokens. Each token can be an operand (a number)
 * or an operator (+, -, *, /). The method evaluates the expression and returns
 * a string that shows the expression and the result.
 * 
 * @author Marcus Clements
 * @since 2024-05-01
 */
public class RPNCalculator {

    /**
     * Evaluates the given RPN expression and returns the result.
     * 
     * @param expression The RPN expression to evaluate
     * @return The result of the RPN expression
     */
    public static String evaluateRPN(String expression) {
        // Split the expression into tokens separated by at least one space
        String[] tokens = expression.split("\\s+");

        // Create stacks to store the equation and the evaluation
        Stack<String> equationBuilderStack = new Stack<>();
        Stack<Integer> evaluationStack = new Stack<>();

        // Iterate through the tokens
        for (String token : tokens) {
            if (isOperator(token)) { // If the token is an operator
                // Pop the last two operands and the last two evaluations
                String operand2 = equationBuilderStack.pop();
                String operand1 = equationBuilderStack.pop();
                int evalOperand2 = evaluationStack.pop();
                int evalOperand1 = evaluationStack.pop();
                // Build the new equation and evaluate the result
                String result = "(" + operand1 + " " + token + " " + operand2 + ")";
                int evalResult = evaluate(token, evalOperand1, evalOperand2);
                // Push the new equation and evaluation onto the stacks
                equationBuilderStack.push(result);
                evaluationStack.push(evalResult);
            } else if (isOperand(token)) {
                // Push the operand onto the stacks
                equationBuilderStack.push(token);
                evaluationStack.push(Integer.parseInt(token));
            } else {
                // Throw an exception if the token is not an operator or operand
                throw new NumberFormatException("Invalid RPN formatting. Foreign token: " + token);
            }
        }
        // Return the final equation and evaluation
        int end = equationBuilderStack.peek().length() - 1;
        return equationBuilderStack.pop().substring(1, end) + " = " + evaluationStack.pop();
    }

    /**
     * Checks if the given token is an operator.
     * 
     * @param token The token to check
     * @return true if the token is an operator, false otherwise
     */
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    /**
     * Checks if the given token is an operand.
     * 
     * @param token The token to check
     * @return true if the token is an operand, false otherwise
     */
    private static boolean isOperand(String token) {
        return token.matches("-?\\d+");
    }

    /**
     * Evaluates the given operator with the given operands.
     * 
     * @param operator The operator to evaluate
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @return The result of the operation
     */
    private static int evaluate(String operator, int operand1, int operand2) {
        // Perform the operation based on the operator
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) throw new ArithmeticException("Error: Cannot divide by zero.");
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}