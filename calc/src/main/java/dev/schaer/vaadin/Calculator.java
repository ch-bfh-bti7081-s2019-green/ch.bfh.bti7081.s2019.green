package dev.schaer.vaadin;

import dev.schaer.vaadin.calculations.Node;
import dev.schaer.vaadin.calculations.NumberNode;
import dev.schaer.vaadin.calculations.OperatorNode;

import java.util.*;
import java.util.stream.Collectors;

public class Calculator {
    private List<Operator> ops = new LinkedList<>();

    public void push(Operator op) {
        ops.add(op);
    }

    public void reset() {
        ops.clear();
    }

    public String getInput(){
        return ops.stream().map(Operator::getLabel).collect(Collectors.joining());
    }

    public double calc() {
        List<NumberNode> numbers = parseNumbers(ListUtils.split(ops, op -> !isNumber(op))).stream().map(NumberNode::new).collect(Collectors.toList());
        List<OperatorNode> operators = ops.stream().filter(op -> !isNumber(op)).map(OperatorNode::new).collect(Collectors.toList());

        if((numbers.size() - operators.size()) != 1){
            throw new IllegalArgumentException("Incorrect input");
        }

        Iterator<NumberNode> numberIterator = numbers.iterator();
        Iterator<OperatorNode> operatorIterator = operators.iterator();

        List<Node> nodes = new LinkedList<>();

        while(numberIterator.hasNext() || operatorIterator.hasNext()){
            nodes.add(numberIterator.next());
            if(operatorIterator.hasNext()){
                nodes.add(operatorIterator.next());
            }
        }

        nodes = treeify(nodes, Arrays.asList(Operator.MULTIPLY, Operator.DIVIDE));
        nodes = treeify(nodes, Arrays.asList(Operator.ADD, Operator.SUBTRACT));

        if(nodes.size() > 1){
            throw new IllegalArgumentException("Couldn't parse input down to single root node");
        }

        Node root = nodes.get(0);

        if(!(root instanceof OperatorNode)){
            throw new IllegalArgumentException("Something went wrong during input parsing");
        }

        return runCalculation(((OperatorNode)root));
    }

    private List<Node> treeify(List<Node> nodes, Collection<Operator> toParse){
        for(int i = 0; i < nodes.size();){
            if(nodes.get(i) instanceof OperatorNode){
                OperatorNode opNode = (OperatorNode)nodes.get(i);
                if(toParse.contains(opNode.getData())){
                    opNode.setRight(nodes.remove(i + 1));
                    opNode.setLeft(nodes.remove(i - 1));
                    continue;
                }
            }
            i++;
        }

        return nodes;
    }

    private List<Double> parseNumbers(List<List<Operator>> rawOps){
        List<Double> numbers = new ArrayList<>(rawOps.size());
        for(List<Operator> numOps : rawOps){
            StringBuilder sb = new StringBuilder();
            for(Operator numOp : numOps){
                sb.append(numOp.getLabel());
            }
            numbers.add(Double.parseDouble(sb.toString()));
        }
        return numbers;
    }

    private double runCalculation(OperatorNode opNode){
        return opNode.calculate().getData();
    }

    private boolean isNumber(Operator op) {
        return !(op.equals(Operator.ADD) ||
                op.equals(Operator.SUBTRACT) ||
                op.equals(Operator.MULTIPLY) ||
                op.equals(Operator.DIVIDE));
    }

    public static String format(double result) {
        if ((int) result == result) {
            return String.format("%d", (int) result);
        } else {
            return String.format("%.3f", result);
        }
    }
}
