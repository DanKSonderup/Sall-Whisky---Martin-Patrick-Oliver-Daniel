package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FillOnCask {
    private LocalDate timeOfFill;
    private Cask cask;
    private List<DistillateFill> distillateFills = new ArrayList<>();

    public FillOnCask(LocalDate timeOfFill, Cask cask) {
        this.timeOfFill = timeOfFill;
        this.cask = cask;
    }

}
