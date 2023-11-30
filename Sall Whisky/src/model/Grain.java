package model;

public class Grain {
    private String grainType;
    private GrainSupplier grainSupplier;
    private String cultivationDescription;
    private String fieldName;

    public Grain(String grainType, GrainSupplier grainSupplier, String cultivationDescription, String fieldName) {
        this.grainType = grainType;
        this.grainSupplier = grainSupplier;
        this.cultivationDescription = cultivationDescription;
        this.fieldName = fieldName;
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
