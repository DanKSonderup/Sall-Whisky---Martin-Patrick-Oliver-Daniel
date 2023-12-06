package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class FillOnCask {
    private LocalDate timeOfFill;
    private Cask cask;
    private List<DistillateFill> distillateFills = new ArrayList<>();

    public FillOnCask(LocalDate timeOfFill, Cask cask) {
        this.timeOfFill = timeOfFill;
        this.cask = cask;
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


    /**
     * returns the total sum of liters from distillate fills connected to this object
     */
    public double getTotalLitersForFills() {
        double sum = 0.0;
        for (DistillateFill distillateFill: distillateFills) {
            sum += distillateFill.getAmountOfDistillateInLiters();
        }
        return sum;
    }


    public void setCask(Cask cask) {
        this.cask = cask;
    }


    public List<DistillateFill> getDistillateFills() {
        return distillateFills;
    }
    public void addDistillateFill(DistillateFill distillateFill) {
        distillateFills.add(distillateFill);
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
            timeOfAging += years + " År, ";
        }
        if (months > 0) {
            timeOfAging += months + " Måneder, ";
        }
        if (days > 0) {
            timeOfAging += days + " Dage";
        }
        return timeOfAging;
    }
}
