package Spring2024.CS220.Labs.Lab07;

import java.util.Stack;

public class RPNCalculator {
    public static String evaluateRPN(String expression) {
        String[] tokens = expression.split("\\s+");
        Stack<String> equationBuilderStack = new Stack<>();
        Stack<Integer> evaluationStack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                String operand2 = equationBuilderStack.pop();
                String operand1 = equationBuilderStack.pop();
                int evalOperand2 = evaluationStack.pop();
                int evalOperand1 = evaluationStack.pop();
                String result = "(" + operand1 + " " + token + " " + operand2 + ")";
                int evalResult = evaluate(token, evalOperand1, evalOperand2);
                equationBuilderStack.push(result);
                evaluationStack.push(evalResult);
            } else if (isOperand(token)) {
                equationBuilderStack.push(token);
                evaluationStack.push(Integer.parseInt(token));
            } else {
                throw new NumberFormatException("Invalid RPN formatting. Foreign token: " + token);
            }
        }

        int end = equationBuilderStack.peek().length() - 1;
        return equationBuilderStack.pop().substring(1, end) + " = " + evaluationStack.pop();
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static boolean isOperand(String token) {
        return token.matches("-?\\d+");
    }

    private static int evaluate(String operator, int operand1, int operand2) {
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