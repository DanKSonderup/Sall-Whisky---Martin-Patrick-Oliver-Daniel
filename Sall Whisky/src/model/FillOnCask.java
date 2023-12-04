package model;

import java.time.LocalDate;
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
     * @return
     */
    public double calculateAlcoholPercentage() {
        // TODO
        return 0;
    }
}
