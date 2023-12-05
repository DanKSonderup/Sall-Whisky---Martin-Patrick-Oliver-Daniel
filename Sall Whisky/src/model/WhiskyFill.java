package model;

import java.time.LocalDate;

public class WhiskyFill {
    private double amountofDistilateFillInLiters;
    private FillOnCask fillOnCask;
    private LocalDate timeOfFill;
    private double alcoholPercentage;

    public WhiskyFill(double amountofDistilateFillInLiters, FillOnCask fillOnCask, LocalDate timeOfFill, double alcoholPercentage) {
        this.amountofDistilateFillInLiters = amountofDistilateFillInLiters;
        this.fillOnCask = fillOnCask;
        this.timeOfFill = timeOfFill;
        this.alcoholPercentage = alcoholPercentage;
    }

    public double getAmountofDistilateFillInLiters() {
        return amountofDistilateFillInLiters;
    }

    public FillOnCask getFillOnCask() {
        return fillOnCask;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public LocalDate getTimeOfFill() {
        return timeOfFill;
    }
}
