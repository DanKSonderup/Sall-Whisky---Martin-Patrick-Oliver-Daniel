package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class FillOnCask {
    private LocalDate timeOfFill;
    private Cask cask;
    private List<DistillateFill> distillateFills = new ArrayList<>();
    private double amountOfDistillateInLiters;

    public FillOnCask(LocalDate timeOfFill, Cask cask) {
        this.timeOfFill = timeOfFill;
        this.cask = cask;
        this.amountOfDistillateInLiters = 0.0;
    }

    public LocalDate getTimeOfFill() {
        return timeOfFill;
    }

    public void setTimeOfFill(LocalDate timeOfFill) {
        this.timeOfFill = timeOfFill;
    }

    public Cask getCask() {
        return cask;
    }

    public double getAmountOfDistillateInLiters() {
        return amountOfDistillateInLiters;
    }

    /*
    public double getTotalLitersForFills() {
        double sum = 0.0;
        for (DistillateFill distillateFill: distillateFills) {
            sum += distillateFill.getAmountOfDistillateInLiters();
        }
        return sum;
    }
     */

    public void setCask(Cask cask) {
        this.cask = cask;
    }

    public void setAmountOfDistillateInLiters(double amountOfDistillateInLiters) {
        this.amountOfDistillateInLiters = amountOfDistillateInLiters;
    }

    public List<DistillateFill> getDistillateFills() {
        return distillateFills;
    }
    public void addDistillateFill(DistillateFill distillateFill) {
        distillateFills.add(distillateFill);
        amountOfDistillateInLiters += distillateFill.getAmountOfDistillateInLiters();
    }

    /**
     * calculates and returns the alcohol percentage in the fillOnCask
     */
    public double calculateAlcoholPercentage() {
        double alcoholPercentage = 0;
        double totalFluids = 0;
        for (DistillateFill distillateFill : distillateFills) {
            alcoholPercentage += (distillateFill.getAmountOfDistillateInLiters() *
                    (distillateFill.getDistillate().getAlcoholPercentage() / 100.0));
            totalFluids += distillateFill.getAmountOfDistillateInLiters();
        }
        return alcoholPercentage / totalFluids * 100;
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
        return "Liter: " +  amountOfDistillateInLiters + " | alc %:" + calculateAlcoholPercentage() + " | " + timeOfAging;
    }
}
