package model;

import java.util.ArrayList;
import java.util.List;

public class Whisky {
    private String name;
    private double waterInLiters;
    private List<WhiskyFill> whiskyFills = new ArrayList<>();
    private String description;

    /** Without whiskyFills */
    public Whisky(String name) {
        this.name = name;
    }

    /** With whiskyFills*/
    public Whisky(String name, double waterInLiters, List<WhiskyFill> whiskyFills, String description) {
        this.name = name;
        this.waterInLiters = waterInLiters;
        this.whiskyFills = whiskyFills;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public List<WhiskyFill> getWhiskyFills() {
        return whiskyFills;
    }

    public void setWaterInLiters(double waterInLiters) {
        this.waterInLiters = waterInLiters;
    }

    public void addWhiskyFill(WhiskyFill whiskyFill) {
        whiskyFills.add(whiskyFill);
    }

    /**
     * Pre: totalFluids > waterInLiters, totalFluids > 0
     * Pre: waterInLiters >= 0
     * @return
     */
    public double calculateAlcoholPercentage() {
        double alcoholPercentage = 0;
        double totalFluids = waterInLiters;
        for (WhiskyFill whiskyFill : whiskyFills) {
            alcoholPercentage += (whiskyFill.getAmountofDistilateFillInLiters() *
                    (whiskyFill.getAlcoholPercentage() / 100.0));

            totalFluids += whiskyFill.getAmountofDistilateFillInLiters();
        }
        return alcoholPercentage / totalFluids * 100;
    }

    @Override
    public String toString() {
        String newMakes = "";
        for (WhiskyFill whiskyFill: whiskyFills) {
            for (FillOnCask fillOnCask: whiskyFill.getFillOnCasks()) {
                for (DistillateFill distillateFill: fillOnCask.getDistillateFills()) {
                    newMakes += distillateFill.getDistillate().getNewMakeNr() + ", ";
                }
            }
        }
        return name + " (" + whiskyFills.get(0).getTimeOfFill() + "), Newmake: " + newMakes;
    }
}
