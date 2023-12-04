package model;

import java.util.ArrayList;
import java.util.List;

public class Whisky {
    private String name;
    private List<WhiskyFill> whiskyFills = new ArrayList<>();

    /** Without whiskyFills */
    public Whisky(String name) {
        this.name = name;
    }

    /** With whiskyFills*/
    public Whisky(String name, List<WhiskyFill> whiskyFills) {
        this.name = name;
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

    public double calculateAlcoholPercentage() {
        double alcoholPercentage = 0;
        double totalFluids = 0;
        for (WhiskyFill whiskyFill : whiskyFills) {
            alcoholPercentage += (whiskyFill.getAmountOfCaskInLiters() *
                    (whiskyFill.getAlcoholPercentage() / 100.0));

            totalFluids += whiskyFill.getAmountOfCaskInLiters();
        }
        return alcoholPercentage / totalFluids * 100;
    }
}
