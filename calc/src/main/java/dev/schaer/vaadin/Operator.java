package dev.schaer.vaadin;

public enum Operator {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    ZERO("0"),
    MULTIPLY("x"),
    DIVIDE("/"),
    ADD("+"),
    SUBTRACT("-"),
    COMMA("."),
    CALC("="),
    RESET("RST"),
    CLEAR("CLS");

    private String label;

    Operator(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}
