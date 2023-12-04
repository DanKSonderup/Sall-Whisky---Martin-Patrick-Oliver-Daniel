package model;

import java.util.ArrayList;
import java.util.List;

public class Whisky {
    private String name;
    private final double alcoholPercentage;
    private List<WhiskyFill> whiskyFills = new ArrayList<>();
    private final List<WhiskyBottle> whiskyBottles = new ArrayList<>();

    /** Without whiskyFills */
    public Whisky(String name, double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
        this.name = name;
    }

    /** With whiskyFills*/
    public Whisky(String name, double alcoholPercentage, List<WhiskyFill> whiskyFills) {
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.whiskyFills = whiskyFills;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public String getName() {
        return name;
    }

    public List<WhiskyFill> getWhiskyFills() {
        return whiskyFills;
    }

    public List<WhiskyBottle> getWhiskyBottles() {
        return whiskyBottles;
    }

    public void addWhiskyFill(WhiskyFill whiskyFill) {
        whiskyFills.add(whiskyFill);
    }

    public void addWhiskyBottle(WhiskyBottle whiskyBottle) {
        whiskyBottles.add(whiskyBottle);
    }
}
