package model;

import java.util.ArrayList;
import java.util.List;

public class Distillate {
    private String newMakeNr;
    private double distillationTimeInHours;
    private double alcoholPercentage;
    private double amountInLiters;
    private Employee employee;
    private String description;
    List<Maltbatch> maltbatches = new ArrayList<>();

    public Distillate(String newMakeNr, double distillationTimeInHours, double alcoholPercentage,
                      double amountInLiters, Employee employee, List<Maltbatch> maltbatches, String description) {
        this.newMakeNr = newMakeNr;
        this.distillationTimeInHours = distillationTimeInHours;
        this.alcoholPercentage = alcoholPercentage;
        this.amountInLiters = amountInLiters;
        this.employee = employee;
        this.maltbatches = maltbatches;
        this.description = description;
    }

    public String getNewMakeNr() {
        return newMakeNr;
    }

    // TODO setNewMakeNr aldrig brugt
    public void setNewMakeNr(String newMakeNr) {
        this.newMakeNr = newMakeNr;
    }

    // TODO getDistillationTimeInHours aldrig brugt
    public double getDistillationTimeInHours() {
        return distillationTimeInHours;
    }

    // TODO setDistillationTimeInHours aldrig brugt
    public void setDistillationTimeInHours(double distillationTimeInHours) {
        this.distillationTimeInHours = distillationTimeInHours;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    // TODO setAlcoholPercentage aldrig brugt
    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public double getAmountInLiters() {
        return amountInLiters;
    }

    public void setAmountInLiters(double amountInLiters) {
        this.amountInLiters = amountInLiters;
    }

    // TODO getEmployee aldrig brugt
    public Employee getEmployee() {
        return employee;
    }

    // TODO setEmployee aldrig brugt
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Maltbatch> getMaltbatches() {
        return maltbatches;
    }

    // TODO addMaltbatch aldrig brugt
    public void addMaltbatch(Maltbatch maltBatch) {
        maltbatches.add(maltBatch);
    }

    // TODO removeMaltbatch aldrig brugt
    public void removeMaltbatch(Maltbatch maltBatch) {
        maltbatches.remove(maltBatch);
    }

    @Override
    public String toString() {
        String maltbatchesString = "";
        for (Maltbatch mb: maltbatches) {
            maltbatchesString += mb.getName();
        }
        return newMakeNr + ", [ " + maltbatchesString + " ] - " + "(L: " + amountInLiters + ", Alc: " + alcoholPercentage + " Medarbejder: " + employee.getName() + " )";
    }
}
