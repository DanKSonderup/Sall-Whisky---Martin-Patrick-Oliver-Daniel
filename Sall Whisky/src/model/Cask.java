package model;

import java.util.ArrayList;
import java.util.List;

public class Cask {
    private int caskId;
    private String countryOfOrigin;
    private double sizeInLiters;
    private String previousContent;
    private Position position;
    private CaskSupplier supplier;
    private final List<FillOnCask> fillOnCasks = new ArrayList<>();
    private final List<FillOnCask> previousFillOnCask = new ArrayList<>();

    /** Constructor uden Position */

    public Cask(String countryOfOrigin, double sizeInLiters, String previousContent) {
        this.countryOfOrigin = countryOfOrigin;
        this.sizeInLiters = sizeInLiters;
        this.previousContent = previousContent;
    }

    /** Constructor with previousContent */
    public Cask(int caskId, String countryOfOrigin, double sizeInLiters, String previousContent, Position position, CaskSupplier supplier) {
        this.caskId = caskId;
        this.countryOfOrigin = countryOfOrigin;
        this.sizeInLiters = sizeInLiters;
        this.previousContent = previousContent;
        this.position = position;
        this.supplier = supplier;
    }

    /** Constructor without previousContent */
    public Cask(int caskId, String countryOfOrigin, double sizeInLiters, Position position, CaskSupplier supplier) {
        this.countryOfOrigin = countryOfOrigin;
        this.sizeInLiters = sizeInLiters;
        this.position = position;
        this.supplier = supplier;
    }

    // ---------------------------------------------------------------------
    /** Getters and setters */
    public int getCaskId() {
        return caskId;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public List<FillOnCask> getPreviousFillOnCask() {
        return previousFillOnCask;
    }

    public void addPreviousFillOnCask(FillOnCask fillOnCask) {
        previousFillOnCask.add(fillOnCask);
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public double getSizeInLiters() {
        return sizeInLiters;
    }

    public void setSizeInLiters(double sizeInLiters) {
        this.sizeInLiters = sizeInLiters;
    }

    public String getPreviousContent() {
        return previousContent;
    }

    public void setPreviousContent(String previousContent) {
        this.previousContent = previousContent;
    }

    public Position getPosition() {
        return position;
    }
    public double getLitersAvailable() {
        double sum = 0.0;
        for (FillOnCask fillOnCask: fillOnCasks) {
            sum += fillOnCask.getAmountOfDistillateInLiters();
        }
        return sizeInLiters - sum;
    }

    public double getTotalAlcoholPercentage() {
        double alcoholPercentage = 0;
        double totalFluids = 0;
        for (FillOnCask fillOnCask : fillOnCasks) {
            alcoholPercentage += (fillOnCask.getAmountOfDistillateInLiters() *
                    (fillOnCask.calculateAlcoholPercentage() / 100.0));
            totalFluids += fillOnCask.getAmountOfDistillateInLiters();
        }
        return alcoholPercentage / totalFluids * 100;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public CaskSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(CaskSupplier supplier) {
        this.supplier = supplier;
    }
    public void addFillOnCask(FillOnCask fillOnCask) {
        fillOnCasks.add(fillOnCask);
    }

    public List<FillOnCask> getFillOnCasks() {
        return fillOnCasks;
    }

    public int getPositionId() {
        return position.getPositionId();
    }

    public int getShelfId() {
        return position.getShelf().getShelfId();
    }

    public int getRackId() {
        return position.getShelf().getRack().getRackId();
    }

    public int getWarehouseId() {
        return position.getShelf().getRack().getWarehouse().getWarehouseId();
    }

    public String getSupplierName() {
        return supplier.getName();
    }

    public void removeFillOnCask(FillOnCask fillOnCask) {
        fillOnCasks.remove(fillOnCask);
    }



    @Override
    public String toString() {
        return String.format("%-5d | %-17s | %-5.2f | %-10.2f | %-18s |%-4d |%-4d |%-4d |%-4d",
                caskId,
                countryOfOrigin,
                sizeInLiters,
                getLitersAvailable(),
                previousContent,
                position.getShelf().getRack().getWarehouse().getWarehouseId(),
                position.getShelf().getRack().getRackId(),
                position.getShelf().getShelfId(),
                position.getPositionId());
    }




}
