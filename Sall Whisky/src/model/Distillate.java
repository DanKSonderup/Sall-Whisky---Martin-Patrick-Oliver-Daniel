package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Distillate {
    private int newMakeNr;
    private LocalDateTime distillationTime;
    private double alcoholPercentage;
    private double amountInLiters;
    private int employeeNumber;
    List<MaltBatch> maltBatches = new ArrayList<>();

    public Distillate(int newMakeNr, LocalDateTime distillationTime, double alcoholPercentage,
                      double amountInLiters, int employeeNumber, List<MaltBatch> maltBatches) {
        this.newMakeNr = newMakeNr;
        this.distillationTime = distillationTime;
        this.alcoholPercentage = alcoholPercentage;
        this.amountInLiters = amountInLiters;
        this.employeeNumber = employeeNumber;
        this.maltBatches = maltBatches;
    }
}
