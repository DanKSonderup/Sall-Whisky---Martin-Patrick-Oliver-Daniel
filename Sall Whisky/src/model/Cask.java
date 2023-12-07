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
    // TODO setCountryOfOrigin aldrig brugt
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public double getSizeInLiters() {
        return sizeInLiters;
    }
    // TODO setSizeInLiters aldrig brugt
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
    // TODO setPreviousContent aldrig brugt
    public void setPreviousContent(String previousContent) {
        this.previousContent = previousContent;
    }

    public Position getPosition() {
        return position;
    }

    // TODO metode specifikation
    public double getLitersAvailable() {
        double sum = 0.0;
        for (FillOnCask fillOnCask: fillOnCasks) {
            sum += fillOnCask.getTotalLitersForFills();
        }
        return sizeInLiters - sum;
    }

    // TODO getTotalLitersOfFills aldrig brugt
    public double getTotalLitersOfFills() {
        double totalLiters = 0;
        for (FillOnCask fillOnCask: fillOnCasks) {
            totalLiters += fillOnCask.getTotalLitersForFills();
        }
        return totalLiters;
    }

    // TODO getTotalAlcoholPercentage aldrig brugt
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

    // TODO setPosition aldrig brugt
    public void setPosition(Position position) {
        this.position = position;
    }

    public CaskSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(CaskSupplier supplier) {
        this.supplier = supplier;
    }

    // TODO metode specifikation
    public void addFillOnCask(FillOnCask fillOnCask) {
        fillOnCasks.add(fillOnCask);
        currentContentInLiters += fillOnCask.getTotalLitersForFills();
    }

    public List<FillOnCask> getFillOnCasks() {
        return fillOnCasks;
    }

    // TODO 4 getters aldrig brugt
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

    // TODO metode specifikation
    public FillOnCask getYoungestFillOnCask() {
        if (fillOnCasks.size() == 0) {
            return null;
        }
        LocalDate youngestDate = fillOnCasks.get(0).getTimeOfFill();
        for (FillOnCask fillOnCask: fillOnCasks) {
            if (fillOnCask.getTimeOfFill().isAfter(youngestDate)) {
                return fillOnCask;
            }
        }
        return fillOnCasks.get(0);
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
