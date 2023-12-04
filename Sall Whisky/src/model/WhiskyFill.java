package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WhiskyFill {
    private double amountOfCaskInLiters;
    private Cask cask;
    private LocalDate timeOfFill;
    private double alcoholPercentage;

    public WhiskyFill(double amountOfCaskInLiters, Cask cask, LocalDate timeOfFill, double alcoholPercentage) {
        this.amountOfCaskInLiters = amountOfCaskInLiters;
        this.cask = cask;
        this.timeOfFill = timeOfFill;
        this.alcoholPercentage = alcoholPercentage;
    }

    public double getAmountOfCaskInLiters() {
        return amountOfCaskInLiters;
    }

    public Cask getCask() {
        return cask;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public LocalDate getTimeOfFill() {
        return timeOfFill;
    }
}
