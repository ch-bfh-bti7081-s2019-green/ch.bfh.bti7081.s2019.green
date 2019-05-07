package ch.bfh.bti7081.s2019.green.model;

public enum Unit {
    PILL("pill", "pcs"),
    TABLE_SPOON("table spoon", "tbsp"),
    PUFF("puff", "pcs"), // inhaler
    DROP("drop", "gtt"),
    GRAM("gram", "g"),
    MILLI_GRAM("milligram", "mg");

    private String name;
    private String shorthand;

    Unit(String name, String shorthand) {
        this.name = name;
        this.shorthand = shorthand;
    }
}
