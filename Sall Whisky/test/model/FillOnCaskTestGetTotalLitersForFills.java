package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FillOnCaskTestGetTotalLitersForFills {
    private FillOnCask fillOnCask;
    private Distillate distillate1;
    private Distillate distillate2;
    private DistillateFill df1;
    private DistillateFill df2;

    @BeforeEach
    void setup() {
        fillOnCask = new FillOnCask(LocalDate.now(), null);
        distillate1 = new Distillate("nmn12", 9, 67.4, 250, null, null, "");
        distillate2 = new Distillate("nmn23", 8, 40, 150, null,null, "");
        df1 = new DistillateFill(20, distillate1);
        df2 = new DistillateFill(13.6, distillate2);
    }

    // TODO test specifikation?
    @Test
    void testCase1() {
        // Arrange

        // Act
        double actualResult = fillOnCask.getTotalLitersForFills();

        // Assert
        assertEquals(0.0, actualResult, 0.001);
    }

    // TODO test specifikation?
    @Test
    void testCase2() {
        // Arrange
        fillOnCask.addDistillateFill(df1);
        // Act
        double actualResult = fillOnCask.getTotalLitersForFills();

        // Assert
        assertEquals(20, actualResult, 0.001);

    }

    // TODO test specifikation?
    @Test
    void testCast3() {
        // Arrange
        fillOnCask.addDistillateFill(df1);
        fillOnCask.addDistillateFill(df2);

        // Act
        double actualResult = fillOnCask.getTotalLitersForFills();

        // Assert
        assertEquals(33.6, actualResult, 0.001);
    }
}