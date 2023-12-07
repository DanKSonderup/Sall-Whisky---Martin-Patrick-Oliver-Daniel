package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // TODO setTimeOfFill aldrig brugt
    public void setTimeOfFill(LocalDate timeOfFill) {
        this.timeOfFill = timeOfFill;
    }

    public Cask getCask() {
        return cask;
    }

    /** Returns the total sum of liters from distillatefills connected to this object */
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

    /** Calculates and returns the alcohol percentage in fillOnCask */
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

    /**
     * Calculates and returns a hashMap that shows how big a share each distillate has of the
     * total amount in the fillOnCask
     */
    public Map<DistillateFill, Double> distillateShare() {
        Map<DistillateFill, Double> map = new HashMap<>();
        double totalLiters = getTotalLitersForFills();

        for (DistillateFill fill : distillateFills) {
            map.put(fill, (fill.getAmountOfDistillateInLiters() / totalLiters));
        }
        return map;
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
