package dev.schaer.vaadin.calculations;

import dev.schaer.vaadin.Operator;
import org.junit.Assert;
import org.junit.Test;

public class TreeTest {
    private static final double DELTA = 0.001;

    @Test
    public void testBasicCalculation() {
        OperatorNode root = new OperatorNode(Operator.MULTIPLY);
        root.setLeft(new NumberNode(4.5));
        root.setRight(new NumberNode(2));

        Assert.assertEquals(9.0, root.calculate().getData(), DELTA);
    }

    @Test
    public void testOrderOfOperations() {
        OperatorNode root = new OperatorNode(Operator.ADD);
        root.setLeft(new NumberNode(10));
        OperatorNode multiply = new OperatorNode(Operator.MULTIPLY);
        root.setRight(multiply);
        multiply.setLeft(new NumberNode(4.5));
        multiply.setRight(new NumberNode(2));

        Assert.assertEquals(19.0, root.calculate().getData(), DELTA);
    }
}