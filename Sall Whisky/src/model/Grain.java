package model;

public class Grain {
    private String grainType;
    private GrainSupplier grainSupplier;

    public Grain(String grainType, GrainSupplier grainSupplier) {
        this.grainType = grainType;
        this.grainSupplier = grainSupplier;
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
