package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Distillate {
    private String newMakeNr;
    private LocalDateTime distillationTime;
    private double alcoholPercentage;
    private double amountInLiters;
    private Employee employee;
    List<Maltbatch> maltbatches = new ArrayList<>();

    public Distillate(String newMakeNr, LocalDateTime distillationTime, double alcoholPercentage,
                      double amountInLiters, Employee employee, List<Maltbatch> maltbatches) {
        this.newMakeNr = newMakeNr;
        this.distillationTime = distillationTime;
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

    public LocalDateTime getDistillationTime() {
        return distillationTime;
    }

    public void setDistillationTime(LocalDateTime distillationTime) {
        this.distillationTime = distillationTime;
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

    public void addMaltbatch(Maltbatch maltBatch) {
        maltbatches.add(maltBatch);
    }

    public void removeMaltbatch(Maltbatch maltBatch) {
        maltbatches.remove(maltBatch);
    }



}
