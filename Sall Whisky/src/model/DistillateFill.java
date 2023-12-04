package model;

public class DistillateFill {
    private double amountOfDistillateInLiters;
    private Distillate distillate;
    private FillOnCask fillOnCask;

    public DistillateFill(double amountOfDistillateInLiters, Distillate distillate) {
        this.amountOfDistillateInLiters = amountOfDistillateInLiters;
        this.distillate = distillate;
        if (distillate.getAmountInLiters() - amountOfDistillateInLiters < 0) {
            throw new IllegalArgumentException();
        }
        distillate.setAmountInLiters(distillate.getAmountInLiters() - amountOfDistillateInLiters);
    }

    public FillOnCask getFillOnCask() {
        return fillOnCask;
    }

    public void setFillOnCask(FillOnCask fillOnCask) {
        this.fillOnCask = fillOnCask;
    }

    public double getAmountOfDistillateInLiters() {
        return amountOfDistillateInLiters;
    }

    public void setAmountOfDistillateInLiters(double amountOfDistillateInLiters) {
        this.amountOfDistillateInLiters = amountOfDistillateInLiters;
    }

    public Distillate getDistillate() {
        return distillate;
    }

    public void setDistillate(Distillate distillate) {
        this.distillate = distillate;
    }
}
