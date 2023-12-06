package controller;

import model.Cask;
import model.Distillate;
import model.DistillateFill;
import model.FillOnCask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTestCreateFillOnCask {

    private Cask cask;
    private Distillate distillate;

    @BeforeEach
    void setup() {
        cask = new Cask("Frankrig", 50, "Whisky");
        distillate = new Distillate("nmn23", 8, 40, 150, null, null);
    }

    @Test
    void TestCase1() {
        // Arrange
        DistillateFill distillateFill = new DistillateFill(49, distillate);
        ArrayList<DistillateFill> distillateFills = new ArrayList<>();
        distillateFills.add(distillateFill);
        FillOnCask fillOnCask = Controller.createFillOnCask(LocalDate.of(2023, 12, 3), cask, distillateFills);

        // Act
        cask.addFillOnCask(fillOnCask);
        distillateFill.setFillOnCask(fillOnCask);

        // Assert
        assertTrue(cask.getFillOnCasks().contains(fillOnCask));
        assertTrue(fillOnCask.getDistillateFills().contains(distillateFill));
        assertTrue(distillateFill.getFillOnCask().equals(fillOnCask));
    }

    @Test
    void TestCase2() {
        // Arrange
        DistillateFill distillateFill = new DistillateFill(49, distillate);
        ArrayList<DistillateFill> distillateFills = new ArrayList<>();
        distillateFills.add(distillateFill);

        // Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            Controller.createFillOnCask(LocalDate.of(2024, 12, 4), cask, distillateFills);
        });
    }

    @Test
    void TestCase3() {
        // Arrange
        DistillateFill distillateFill = new DistillateFill(50, distillate);
        ArrayList<DistillateFill> distillateFills = new ArrayList<>();
        distillateFills.add(distillateFill);
        FillOnCask fillOnCask = Controller.createFillOnCask(LocalDate.of(2023, 12, 3), cask, distillateFills);

        // Act
        cask.addFillOnCask(fillOnCask);
        distillateFill.setFillOnCask(fillOnCask);

        // Assert
        assertTrue(cask.getFillOnCasks().contains(fillOnCask));
        assertTrue(fillOnCask.getDistillateFills().contains(distillateFill));
        assertTrue(distillateFill.getFillOnCask().equals(fillOnCask));
    }

    @Test
    void TestCase4() {
        // Arrange
        DistillateFill distillateFill = new DistillateFill(51, distillate);
        ArrayList<DistillateFill> distillateFills = new ArrayList<>();
        distillateFills.add(distillateFill);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Controller.createFillOnCask(LocalDate.of(2023, 12, 1), cask, distillateFills);
        });
    }


}