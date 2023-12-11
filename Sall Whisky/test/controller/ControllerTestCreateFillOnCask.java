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
    private DistillateFill distillateFill;
    private ArrayList<DistillateFill> distillateFills = new ArrayList<>();

    @BeforeEach
    void setup() {
        cask = new Cask("Frankrig", 50, "Whisky");
        distillate = new Distillate("nmn23", 8, 40, 150,
                null, null, "");
        distillateFills = new ArrayList<>();
    }

    /** TC1: TimeOfFill 01-12-2023, fill: 49 liters (max caskSize 50 liters) */
    @Test
    void TestCase1() {
        // Arrange
        distillateFill = new DistillateFill(49, distillate);
        distillateFills.add(distillateFill);

        // Act
        FillOnCask fillOnCask = Controller.createFillOnCask(LocalDate.of(2023, 12, 1),
                cask, distillateFills);
        distillateFill.setFillOnCask(fillOnCask);

        // Assert
        assertTrue(cask.getFillOnCasks().contains(fillOnCask));
        assertTrue(fillOnCask.getDistillateFills().contains(distillateFill));
        assertTrue(distillateFill.getFillOnCask().equals(fillOnCask));
    }

    /** TC2: TimeOfFill 04-12-2024, fill: 49 liters (max caskSize 50 liters) */
    @Test
    void TestCase2() {
        // Arrange
        distillateFill = new DistillateFill(49, distillate);
        distillateFills.add(distillateFill);

        // Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            Controller.createFillOnCask(LocalDate.of(2024, 12, 4), cask, distillateFills);
        });
    }

    /** TC3: TimeOfFill 03-12-2023, fill: 50 liters (max caskSize 50 liters) */
    @Test
    void TestCase3() {
        // Arrange
        distillateFill = new DistillateFill(50, distillate);
        distillateFills.add(distillateFill);

        // Act
        FillOnCask fillOnCask = Controller.createFillOnCask(LocalDate.of(2023, 12, 3),
                cask, distillateFills);
        distillateFill.setFillOnCask(fillOnCask);

        // Assert
        assertTrue(cask.getFillOnCasks().contains(fillOnCask));
        assertTrue(fillOnCask.getDistillateFills().contains(distillateFill));
        assertTrue(distillateFill.getFillOnCask().equals(fillOnCask));
    }

    /** TC4: TimeOfFill 01-12-2023, fill: 51 liters (max caskSize 50 liters) */
    @Test
    void TestCase4() {
        // Arrange
        distillateFill = new DistillateFill(51, distillate);
        distillateFills.add(distillateFill);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Controller.createFillOnCask(LocalDate.of(2023, 12, 1), cask, distillateFills);
        });
    }
}