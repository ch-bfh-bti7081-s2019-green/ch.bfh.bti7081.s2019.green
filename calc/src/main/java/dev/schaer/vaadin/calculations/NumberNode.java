package dev.schaer.vaadin.calculations;

public class NumberNode extends Node {
    private double data;

    public NumberNode(double number){
        this.data = number;
    }

    public double getData(){
        return this.data;
    }
}
