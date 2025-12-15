package org.example;

public enum METvalues {
    WALKING(3.5),
    RUNNING(10.0),
    CYCLING(6.5),
    COMPETITIVESWIMMING(11.0),
    SWIMMING(8.0),
    OTHER(5.0);

    private final double value;

    // Constructor
    METvalues(double value) {
        this.value = value;
    }

    // Getter
    public double getValue() {
        return value;
    }

}
