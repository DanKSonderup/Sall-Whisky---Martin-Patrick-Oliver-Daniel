package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public double getWaterInLiters() {
        return waterInLiters;
    }

    public String getDescription() {
        return description;
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
        for (WhiskyFill whiskyFill : whiskyFills) {
            alcoholPercentage += (whiskyFill.getAmountofDistilateFillInLiters() *
                    (whiskyFill.getAlcoholPercentage() / 100.0));
        }
        return alcoholPercentage / totalFluidsInWhisky() * 100;
    }

    /** Calculates the total amount of Whisky with water included. */
    public double totalFluidsInWhisky() {
        double totalFluids = waterInLiters;
        for (WhiskyFill whiskyfill : whiskyFills) {
            totalFluids += whiskyfill.getAmountofDistilateFillInLiters();
        }
        return totalFluids;
    }

    /** HashMap which returns a specific amount per whiskyFill */
    public Map<WhiskyFill, Double> caskShare() {
        Map<WhiskyFill, Double> map = new HashMap();

        for (WhiskyFill whiskyFill : whiskyFills) {
            double share = whiskyFill.getAmountofDistilateFillInLiters() / totalFluidsInWhisky();
            DecimalFormat df = new DecimalFormat("##.##");
            share = Double.parseDouble(df.format(share));
            map.put(whiskyFill, share);
        }
        return map;
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
