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

    public String getGrainType() {
        return grainType;
    }

    public void setGrainType(String grainType) {
        this.grainType = grainType;
    }

    public GrainSupplier getGrainSupplier() {
        return grainSupplier;
    }

    public void setGrainSupplier(GrainSupplier grainSupplier) {
        this.grainSupplier = grainSupplier;
    }
}
