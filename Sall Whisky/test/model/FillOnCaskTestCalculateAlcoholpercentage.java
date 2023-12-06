package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FillOnCaskTestCalculateAlcoholpercentage {
    private FillOnCask fillOnCask;
    private Distillate distillate1;
    private Distillate distillate2;
    private DistillateFill df1;
    private DistillateFill df2;


    @BeforeEach
    void setup() {
        fillOnCask = new FillOnCask(LocalDate.now(), null);
        distillate1 = new Distillate("nmn", 9, 67.4, 250,
                null, null);
        distillate2 = new Distillate("nmn23", 8, 40, 150,
                null, null);
        df1 = new DistillateFill(75, distillate1);
        df2 = new DistillateFill(60, distillate2);
    }


    @Test
    void TestCase1() {
        // Arrange
        fillOnCask.addDistillateFill(df1);

        // Act
        double actualResult = fillOnCask.calculateAlcoholPercentage();

        // Assert
        assertEquals(67.4, actualResult, 0.001);
    }

    @Test
    void TestCase2() {
        // Arrange
        fillOnCask.addDistillateFill(df1);
        fillOnCask.addDistillateFill(df2);

        // Act
        double actualResult = fillOnCask.calculateAlcoholPercentage();

        // Assert
        assertEquals(55.22, actualResult, 0.01);
    }
}