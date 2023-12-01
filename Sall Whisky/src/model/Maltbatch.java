package model;

public class Maltbatch {

    private String name;
    private String description;
    private Grain grain;

    public Maltbatch(String name, String description, Grain grain) {
        this.name = name;
        this.description = description;
        this.grain = grain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
