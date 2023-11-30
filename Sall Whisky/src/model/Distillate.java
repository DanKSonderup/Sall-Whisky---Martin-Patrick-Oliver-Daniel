package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Distillate {
    private int newMakeNr;
    private LocalDateTime distillationTime;
    private double alcoholPercentage;
    private double amountInLiters;
    private Employee employee;
    List<MaltBatch> maltBatches = new ArrayList<>();

    public Distillate(int newMakeNr, LocalDateTime distillationTime, double alcoholPercentage,
                      double amountInLiters, Employee employee, List<MaltBatch> maltBatches) {
        this.newMakeNr = newMakeNr;
        this.distillationTime = distillationTime;
        this.alcoholPercentage = alcoholPercentage;
        this.amountInLiters = amountInLiters;
        this.employee = employee;
        this.maltBatches = maltBatches;
    }

    public int getNewMakeNr() {
        return newMakeNr;
    }

    public void setNewMakeNr(int newMakeNr) {
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

    public void addMaltbatch(MaltBatch maltBatch) {
        maltBatches.add(maltBatch);
    }

    public void removeMaltbatch(MaltBatch maltBatch) {
        maltBatches.remove(maltBatch);
    }



}
