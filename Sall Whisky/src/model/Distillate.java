package model;

import java.util.ArrayList;
import java.util.List;

public class Distillate {
    private String newMakeNr;
    private double distillationTimeInHours;
    private double alcoholPercentage;
    private double amountInLiters;
    private Employee employee;
    List<Maltbatch> maltbatches = new ArrayList<>();

    public Distillate(String newMakeNr, double distillationTimeInHours, double alcoholPercentage,
                      double amountInLiters, Employee employee, List<Maltbatch> maltbatches) {
        this.newMakeNr = newMakeNr;
        this.distillationTimeInHours = distillationTimeInHours;
        this.alcoholPercentage = alcoholPercentage;
        this.amountInLiters = amountInLiters;
        this.employee = employee;
        this.maltbatches = maltbatches;
    }

    public String getNewMakeNr() {
        return newMakeNr;
    }

    public void setNewMakeNr(String newMakeNr) {
        this.newMakeNr = newMakeNr;
    }

    public double getDistillationTimeInHours() {
        return distillationTimeInHours;
    }

    public void setDistillationTimeInHours(double distillationTimeInHours) {
        this.distillationTimeInHours = distillationTimeInHours;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public double getAmountInLiters() {
        return amountInLiters;
    }

    public void setAmountInLiters(double amountInLiters) {
        this.amountInLiters = amountInLiters;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Maltbatch> getMaltbatches() {
        return maltbatches;
    }

    public void addMaltbatch(Maltbatch maltBatch) {
        maltbatches.add(maltBatch);
    }

    public void removeMaltbatch(Maltbatch maltBatch) {
        maltbatches.remove(maltBatch);
    }

    @Override
    public String toString() {
        String maltbatchesString = "";
        for (Maltbatch mb: maltbatches) {
            maltbatchesString += mb.getName();
        }
        return newMakeNr + "[ " + maltbatchesString + " ]" + "(L: " + amountInLiters + ", Alc: " + alcoholPercentage + " Medarbejder: " + employee.getName() + " )";
    }
}
