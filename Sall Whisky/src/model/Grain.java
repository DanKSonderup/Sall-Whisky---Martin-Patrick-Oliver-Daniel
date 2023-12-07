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

    // TODO getGrainType aldrig brugt
    public String getGrainType() {
        return grainType;
    }

    public void setGrainType(String grainType) {
        this.grainType = grainType;
    }

    // TODO getGrainSupplier aldrig brugt
    public GrainSupplier getGrainSupplier() {
        return grainSupplier;
    }

    // TODO setGrainSupplier aldrig brugt
    public void setGrainSupplier(GrainSupplier grainSupplier) {
        this.grainSupplier = grainSupplier;
    }

    // TODO getCultivationDescription aldrig brugt
    public String getCultivationDescription() {
        return cultivationDescription;
    }

    public void setCultivationDescription(String cultivationDescription) {
        this.cultivationDescription = cultivationDescription;
    }

    public Field getField() {
        return field;
    }

    // TODO setField aldrig brugt
    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return grainType + " | " + cultivationDescription;
    }
}
