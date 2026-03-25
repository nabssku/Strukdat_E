package demo.modul3.task1;

import java.util.*;

public class InfixPostfix {

    // PRIORITY OPERATOR
    public static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    // INFIX → POSTFIX
    public static String infixToPostfix(String exp) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // angka
            if (Character.isDigit(c)) {
                result.append(c).append(" ");
            }
            // kurung buka
            else if (c == '(') {
                stack.push(c);
            }
            // kurung tutup
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop()).append(" ");
                }
                stack.pop();
            }
            // operator
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!stack.isEmpty() &&
                        precedence(stack.peek()) >= precedence(c)) {
                    result.append(stack.pop()).append(" ");
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }

        return result.toString();
    }

    // EVALUASI POSTFIX
    public static double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (Character.isDigit(token.charAt(0))) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();

                switch (token.charAt(0)) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                }
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan infix: ");
        String infix = input.nextLine();

        String postfix = infixToPostfix(infix);
        double result = evaluatePostfix(postfix);

        System.out.println("Infix   : " + infix);
        System.out.println("Postfix : " + postfix);
        System.out.println("Result  : " + result);
    }
}
