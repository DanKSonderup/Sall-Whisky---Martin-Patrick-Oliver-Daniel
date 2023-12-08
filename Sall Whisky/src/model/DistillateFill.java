package model;

public class DistillateFill {
    private double amountOfDistillateInLiters;
    private Distillate distillate;
    private TapFromDistillate tapFromDistillate;

    public DistillateFill(double amountOfDistillateInLiters, Distillate distillate) {
        this.amountOfDistillateInLiters = amountOfDistillateInLiters;
        this.distillate = distillate;
        if (distillate.getAmountInLiters() - amountOfDistillateInLiters < 0) {
            throw new IllegalArgumentException();
        }
        distillate.setAmountInLiters(distillate.getAmountInLiters() - amountOfDistillateInLiters);
    }

    public TapFromDistillate getFillOnCask() {
        return tapFromDistillate;
    }

    public void setFillOnCask(TapFromDistillate tapFromDistillate) {
        this.tapFromDistillate = tapFromDistillate;
    }

    public double getAmountOfDistillateInLiters() {
        return amountOfDistillateInLiters;
    }

    public Distillate getDistillate() {
        return distillate;
    }

    public void setDistillate(Distillate distillate) {
        this.distillate = distillate;
    }
}
