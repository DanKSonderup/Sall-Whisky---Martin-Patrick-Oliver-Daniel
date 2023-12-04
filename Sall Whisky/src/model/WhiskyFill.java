package model;

public class WhiskyFill {
    private double amountOfCask;
    private Cask cask;

    public WhiskyFill(double amountOfCask, Cask cask) {
        this.amountOfCask = amountOfCask;
        this.cask = cask;
    }

    public double getAmountOfCask() {
        return amountOfCask;
    }

    public Cask getCask() {
        return cask;
    }

}
