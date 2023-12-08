package model;

import java.time.LocalDate;

public class FillOnCask {
    private LocalDate timeFill;
    private TapFromDistillate tapFromDistillate;
    private Cask cask;

    public FillOnCask(LocalDate timeFill, TapFromDistillate tapFromDistillate, Cask cask) {
        this.timeFill = timeFill;
        this.tapFromDistillate = tapFromDistillate;
        this.cask = cask;
    }

    public LocalDate getTimeFill() {
        return timeFill;
    }

    public TapFromDistillate getFillOnCask() {
        return tapFromDistillate;
    }

    public Cask getCask() {
        return cask;
    }

    public void setTimeFill(LocalDate timeFill) {
        this.timeFill = timeFill;
    }

    public void setFillOnCask(TapFromDistillate tapFromDistillate) {
        this.tapFromDistillate = tapFromDistillate;
    }

    public void setCask(Cask cask) {
        this.cask = cask;
    }
}
