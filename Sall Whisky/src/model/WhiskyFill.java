package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

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

    @Override
    public String toString() {
        int years = Period.between(timeOfFill, LocalDate.now()).getYears();
        int months = Period.between(timeOfFill, LocalDate.now()).getMonths();
        int days = Period.between(timeOfFill, LocalDate.now()).getDays();
        String timeOfAging = "";
        if (years > 0) {
            timeOfAging += "År: " + years + " ";
        }
        if (months > 0) {
            timeOfAging += "Måneder: " + months + " ";
        }
        if (days > 0) {
            timeOfAging += "Days: " + days + " ";
        }

        return "Liter: " + amountofDistilateFillInLiters + " | alc %:" + getAlcoholPercentage() + " | " + timeOfAging;
    }
}
