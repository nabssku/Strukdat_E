package demo.modul3.task1;

import java.util.*;

public class InfixPostfix {

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


            if (c == ' ') continue;

            // kalau angka (bisa lebih dari 1 digit)
            if (Character.isDigit(c)) {
                while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
                    result.append(exp.charAt(i));
                    i++;
                }
                result.append(" ");
                i--;
            }

            // kalau minus tapi jadi tanda negatif
            else if (c == '-' && (i == 0 || exp.charAt(i - 1) == '(')) {
                result.append(c); // gabung ke angka
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

    // eval postpik
    public static double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            // kalau angka (termasuk negatif)
            if (Character.isDigit(token.charAt(0)) ||
                    (token.length() > 1 && token.charAt(0) == '-')) {
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