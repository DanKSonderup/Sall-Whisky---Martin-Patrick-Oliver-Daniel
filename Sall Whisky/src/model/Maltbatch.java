package model;

public class Maltbatch {
    private String description;
    private Grain grain;

    public Maltbatch(String description, Grain grain) {
        this.description = description;
        this.grain = grain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Grain getGrain() {
        return grain;
    }

    public void setGrain(Grain grain) {
        this.grain = grain;
    }
}
