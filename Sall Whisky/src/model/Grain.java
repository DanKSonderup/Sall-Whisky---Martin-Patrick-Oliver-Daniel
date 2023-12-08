package model;

import java.io.Serializable;

public class Grain implements Serializable {
    private String grainType;
    private GrainSupplier grainSupplier;
    private String cultivationDescription;
    private Field field;

    public Grain(String grainType, GrainSupplier grainSupplier, String cultivationDescription, Field field) {
        this.grainType = grainType;
        this.grainSupplier = grainSupplier;
        this.cultivationDescription = cultivationDescription;
        this.field = field;
    }

    public void setGrainType(String grainType) {
        this.grainType = grainType;
    }

    public String getCultivationDescription() {
        return cultivationDescription;
    }

    public void setCultivationDescription(String cultivationDescription) {
        this.cultivationDescription = cultivationDescription;
    }

    public String getGrainType() {
        return grainType;
    }

    public GrainSupplier getGrainSupplier() {
        return grainSupplier;
    }

    public Field getField() {
        return field;
    }

    @Override
    public String toString() {
        return grainType + " | " + cultivationDescription;
    }
}
