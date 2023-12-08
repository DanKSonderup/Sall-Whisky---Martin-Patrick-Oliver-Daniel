package model;

import java.time.LocalDate;

public class PutOnCask {
    private LocalDate timeFill;
    private FillOnCask fillOnCask;
    private Cask cask;

    public PutOnCask(LocalDate timeFill, FillOnCask fillOnCask, Cask cask) {
        this.timeFill = timeFill;
        this.fillOnCask = fillOnCask;
        this.cask = cask;
    }

    public LocalDate getTimeFill() {
        return timeFill;
    }

    public FillOnCask getFillOnCask() {
        return fillOnCask;
    }

    public Cask getCask() {
        return cask;
    }

    public void setTimeFill(LocalDate timeFill) {
        this.timeFill = timeFill;
    }

    public void setFillOnCask(FillOnCask fillOnCask) {
        this.fillOnCask = fillOnCask;
    }

    public void setCask(Cask cask) {
        this.cask = cask;
    }
}
