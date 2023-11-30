package model;

public class DistillateFill {
    private double amountOfDistillate;
    private Distillate distillate;
    private FillOnCask fillOnCask;

    public DistillateFill(double amountOfDistillate, Distillate distillate) {
        this.amountOfDistillate = amountOfDistillate;
        this.distillate = distillate;
    }

    public FillOnCask getFillOnCask() {
        return fillOnCask;
    }

    public void setFillOnCask(FillOnCask fillOnCask) {
        this.fillOnCask = fillOnCask;
    }

    public double getAmountOfDistillate() {
        return amountOfDistillate;
    }

    public void setAmountOfDistillate(double amountOfDistillate) {
        this.amountOfDistillate = amountOfDistillate;
    }

    public Distillate getDistillate() {
        return distillate;
    }

    public void setDistillate(Distillate distillate) {
        this.distillate = distillate;
    }
}
