package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class WhiskyFill {
    private double amountofDistilateFillInLiters;
    private List<FillOnCask> fillOnCasks = new ArrayList<>();
    private LocalDate timeOfFill;
    private double alcoholPercentage;
    private Cask cask;

    public WhiskyFill(double amountofDistilateFillInLiters, List<FillOnCask> fillOnCasks, LocalDate timeOfFill, double alcoholPercentage, Cask cask) {
        this.amountofDistilateFillInLiters = amountofDistilateFillInLiters;
        this.fillOnCasks = fillOnCasks;
        this.timeOfFill = timeOfFill;
        this.alcoholPercentage = alcoholPercentage;
        this.cask = cask;
    }

    public double getAmountofDistilateFillInLiters() {
        return amountofDistilateFillInLiters;
    }

    public List<FillOnCask> getFillOnCasks() {
        return fillOnCasks;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public LocalDate getTimeOfFill() {
        return timeOfFill;
    }

    public Cask getCask() {
        return cask;
    }

    @Override
    public String toString() {
        String distillates = "";
        for (FillOnCask fillOnCask: fillOnCasks) {
            for (DistillateFill distillateFill: fillOnCask.getDistillateFills()) {
                distillates += "NewMakeNr: " + distillateFill.getDistillate().getNewMakeNr() + ", ";
            }
        }
        return "Liter: " + amountofDistilateFillInLiters + " | Alc% :" + alcoholPercentage + "\n" + "Destillater: " + distillates;
    }
}
