package dev.schaer.vaadin.calculations;

import dev.schaer.vaadin.Operator;

public class OperatorNode extends Node {
    private Operator data;

    public OperatorNode(Operator operator) {
        this.data = operator;
    }

    public Operator getData() {
        return this.data;
    }

    public NumberNode calculate() {
        if (isValid()) {
            double a = extractNumber(this.getLeft());
            double b = extractNumber(this.getRight());
            switch (data) {
                case ADD:
                    return new NumberNode(a + b);
                case SUBTRACT:
                    return new NumberNode(a - b);
                case MULTIPLY:
                    return new NumberNode(a * b);
                case DIVIDE:
                    return new NumberNode(a / b);
                default:
                    throw new IllegalArgumentException(String.format("Illegal Operation a=%.3f, b=%.3f, op=%s", a, b, this.getData()));
            }
        } else {
            throw new IllegalArgumentException("Not enough operands");
        }
    }

    private double extractNumber(Node node) {
        return node instanceof NumberNode
                ? ((NumberNode) node).getData()
                : ((OperatorNode) node).calculate().getData();
    }

    private boolean isValid() {
        return (this.getLeft() != null && this.getRight() != null);
    }
}
