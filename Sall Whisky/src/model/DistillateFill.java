package model;

public class DistillateFill {
    private double amountOfDistillate;
    private Distillate distillate;

    public DistillateFill(double amountOfDistillate, Distillate distillate) {
        this.amountOfDistillate = amountOfDistillate;
        this.distillate = distillate;
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
