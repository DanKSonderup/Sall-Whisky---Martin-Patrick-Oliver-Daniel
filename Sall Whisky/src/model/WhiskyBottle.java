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

    public Whisky getWhisky() {
        return whisky;
    }

    public void setWhisky(Whisky whisky) {
        this.whisky = whisky;
    }
}
