package model;

import java.util.ArrayList;
import java.util.List;

public class Whisky {
    private String name;
    private double waterInLiters;
    private List<WhiskyFill> whiskyFills = new ArrayList<>();

    /** Without whiskyFills */
    public Whisky(String name) {
        this.name = name;
    }

    /** With whiskyFills*/
    public Whisky(String name, double waterInLiters, List<WhiskyFill> whiskyFills) {
        this.name = name;
        this.waterInLiters = waterInLiters;
        this.whiskyFills = whiskyFills;
    }

    public String getName() {
        return name;
    }

    public List<WhiskyFill> getWhiskyFills() {
        return whiskyFills;
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
}
