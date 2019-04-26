package dev.schaer.vaadin;

import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator {
    private Stack<Operator> ops = new Stack<>();

    public Calculator() {
        Object o = new Object();
    }

    public void push(Operator op) {
        ops.push(op);
    }

    public void reset() {
        ops.clear();
    }

    public String getOperations() {
        return "ops[" + ops.stream().map(Operator::name).collect(Collectors.joining(",")) + "]";
    }

    public double calc() {
        String operations = getOperations();
        StringBuilder num2 = new StringBuilder();
        StringBuilder num1 = new StringBuilder();
        Operator action = null;
        while (!ops.empty()) {
            Operator op = ops.pop();
            if (isNumber(op)) {
                if (action == null) {
                    num2.append(op.getLabel());
                } else {
                    num1.append(op.getLabel());
                }
            } else {
                action = op;
            }
        }
        num1.reverse();
        num2.reverse();

        double a = Double.parseDouble(num1.toString());
        double b = Double.parseDouble(num2.toString());
        if (action == null) {
            throw new IllegalArgumentException("Invalid ops: " + operations);
        }

        switch (action) {
            case ADD:
                return a + b;
            case SUBTRACT:
                return a - b;
            case MULTIPLY:
                return a * b;
            case DIVIDE:
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid ops: " + operations);
        }
    }

    private boolean isNumber(Operator op) {
        return !(op.equals(Operator.ADD) ||
                op.equals(Operator.SUBTRACT) ||
                op.equals(Operator.MULTIPLY) ||
                op.equals(Operator.DIVIDE) ||
                op.equals(Operator.COMMA));
    }

    public static String format(double result) {
        if ((int) result == result) {
            return String.format("%d", (int) result);
        } else {
            return String.format("%.3f", result);
        }
    }
}
