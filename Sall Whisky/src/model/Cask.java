package model;

import java.time.LocalDate;
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
    private double currentContentInLiters;

    /** Constructor without Position */
    public Cask(String countryOfOrigin, double sizeInLiters, String previousContent) {
        this.countryOfOrigin = countryOfOrigin;
        this.sizeInLiters = sizeInLiters;
        this.previousContent = previousContent;
        this.currentContentInLiters = 0.0;
    }

    /** Constructor with previousContent */
    public Cask(int caskId, String countryOfOrigin, double sizeInLiters, String previousContent, Position position, CaskSupplier supplier) {
        this.caskId = caskId;
        this.countryOfOrigin = countryOfOrigin;
        this.sizeInLiters = sizeInLiters;
        this.previousContent = previousContent;
        this.position = position;
        this.supplier = supplier;
        this.currentContentInLiters = 0.0;
    }

    /** Constructor without previousContent */
    public Cask(int caskId, String countryOfOrigin, double sizeInLiters, Position position, CaskSupplier supplier) {
        this.countryOfOrigin = countryOfOrigin;
        this.sizeInLiters = sizeInLiters;
        this.position = position;
        this.supplier = supplier;
    }

    // ---------------------------------------------------------------------

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

    public double getCurrentContentInLiters() {
        return currentContentInLiters;
    }

    public void setCurrentContentInLiters(double currentContentInLiters) {
        this.currentContentInLiters = currentContentInLiters;
    }
    public void setPreviousContent(String previousContent) {
        this.previousContent = previousContent;
    }

    public Position getPosition() {
        return position;
    }

    /** Calculates and returns the space left in the cask in liters */
    public double getLitersAvailable() {
        return sizeInLiters - getTotalLitersOfFills();
    }

    /** Calculates and returns the amount of fluids currently in the cask */
    public double getTotalLitersOfFills() {
        double totalLiters = 0;
        for (FillOnCask fillOnCask: fillOnCasks) {
            totalLiters += fillOnCask.getTotalLitersForFills();
        }
        return totalLiters;
    }


    /** Calculates and returns the weighted average alcoholpercentage for all the fillOnCasks in the cask */
    public double getTotalAlcoholPercentage() {
        double alcoholPercentage = 0;
        double totalFluids = 0;
        for (FillOnCask fillOnCask : fillOnCasks) {
            alcoholPercentage += (fillOnCask.getTotalLitersForFills() *
                    (fillOnCask.calculateAlcoholPercentage() / 100.0));
            totalFluids += fillOnCask.getTotalLitersForFills();
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


    /** Adds a fillOnCask to the cask and updates the casks current content in liters */
    public void addFillOnCask(FillOnCask fillOnCask) {
        fillOnCasks.add(fillOnCask);
        currentContentInLiters += fillOnCask.getTotalLitersForFills();
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


    /**
     * Returns the fillOnCask that has been in the cask for the shortest amount of time
     * If the cask is empty, return null
     */
    public FillOnCask getYoungestFillOnCask() {
        if (fillOnCasks.isEmpty()) {
            return null;
        }

        FillOnCask youngestFillOnCask = fillOnCasks.get(0);
        for (FillOnCask fillOnCask: fillOnCasks) {
            if (fillOnCask.getTimeOfFill().isAfter(youngestFillOnCask.getTimeOfFill())) {
                youngestFillOnCask = fillOnCask;
            }
        }
        return youngestFillOnCask;
    }

    public void removeFillOnCask(FillOnCask fillOnCask) {
        fillOnCasks.remove(fillOnCask);
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-17s | Total (L) %-5.2f | Tilgængelig (L) %-10.2f | %-18s ",
                caskId,
                countryOfOrigin,
                sizeInLiters,
                getLitersAvailable(),
                previousContent);
    }
}
