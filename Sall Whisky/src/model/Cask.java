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

    /** Constructor uden Position */

    public Cask(String countryOfOrigin, double sizeInLiters, String previousContent) {
        this.countryOfOrigin = countryOfOrigin;
        this.sizeInLiters = sizeInLiters;
        this.previousContent = previousContent;
    }

    /** Constructor with previousContent */
    public Cask(int caskId, String countryOfOrigin, double sizeInLiters, String previousContent, Position position, CaskSupplier supplier) {
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
            sum += fillOnCask.getTotalLitersForFills();
        }
        return sizeInLiters - sum;
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


    @Override
    public String toString() {
        return String.format("%d %s %.2f %s %s", caskId, countryOfOrigin, sizeInLiters, previousContent, position);
    }
}
