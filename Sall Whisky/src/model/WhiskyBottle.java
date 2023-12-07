package model;

public class WhiskyBottle {
    private int bottleId;
    private int centiliterCapacity;
    private Whisky whisky;

    public WhiskyBottle(int bottleId, int centiliterCapacity, Whisky whisky) {
        this.bottleId = bottleId;
        this.centiliterCapacity = centiliterCapacity;
        this.whisky = whisky;
    }

    /** Getters and setters */
    // TODO getBottleId aldrig brugt
    public int getBottleId() {
        return bottleId;
    }

    // TODO setBottleId aldrig brugt
    public void setBottleId(int bottleId) {
        this.bottleId = bottleId;
    }

    // TODO getCentiliterCapacity aldrig brugt
    public int getCentiliterCapacity() {
        return centiliterCapacity;
    }

    // TODO setCentiliterCapacity aldrig brugt
    public void setCentiliterCapacity(int centiliterCapacity) {
        this.centiliterCapacity = centiliterCapacity;
    }

    public Whisky getWhisky() {
        return whisky;
    }

    public void setWhisky(Whisky whisky) {
        this.whisky = whisky;
    }
}
