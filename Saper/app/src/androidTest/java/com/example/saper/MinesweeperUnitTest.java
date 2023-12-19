package com.example.saper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

public class MinesweeperUnitTest {

    @Rule
    public ActivityTestRule<GridViewActivity> activityRule = new ActivityTestRule<>(GridViewActivity.class);

    private GridViewActivity gridViewActivity;

    @Before
    public void setUp() {
        gridViewActivity = activityRule.getActivity();
    }




    /////////////////Jednostkowe/////////////////
    @Test
    public void testGameEndCondition() {
        // Test sprawdzający warunek końca gry
        gridViewActivity.setGameOver(true);
        assertTrue(gridViewActivity.isGameOver());
    }

    @Test
    public void testGenerateMineField() {
        int gridSize = 5;
        int bombCount = 5;

        // Tworzenie planszy
        gridViewActivity.createMineField(gridSize, bombCount);

        // Pobieranie planszy
        Boolean[][] mineField = gridViewActivity.getMineField();

        // Sprawdzenie czy plansza została poprawnie wygenerowana
        assertEquals("Niepoprawny rozmiar planszy", gridSize, mineField.length);
        for (int i = 0; i < gridSize; i++) {
            assertEquals("Niepoprawny rozmiar planszy w wierszu " + i, gridSize, mineField[i].length);
            for (int j = 0; j < gridSize; j++) {
                assertNotNull("Pole planszy nie zostało zainicjalizowane", mineField[i][j]);
            }
        }

        // Sprawdzenie liczby bomb na planszy
        int count = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (mineField[i][j]) {
                    count++;
                }
            }
        }

        System.out.println("Liczba bomb na planszy: " + count);
        assertEquals("Niepoprawna liczba bomb na planszy", bombCount, count);
    }



    @Test
    public void testUncoverFieldWithoutBomb() {
        int gridSize = 5; // Przykładowy rozmiar planszy
        gridViewActivity.createMineField(gridSize, 5);

        // Zakładamy, że wybieramy losowe pole bez bomby
        int row = 0;
        int col = 0;
        while (gridViewActivity.getMineField()[row][col]) {
            // Losuj inne pole, dopóki nie trafisz na pole bez bomby
            row = new Random().nextInt(gridSize);
            col = new Random().nextInt(gridSize);
        }

        // Odsłania pole bez bomby
        gridViewActivity.uncoverField(row, col);

        // Sprawdź, czy pole jest teraz odsłonięte
        assertTrue(gridViewActivity.isFieldUncovered(row, col));
    }





//////////////Automatyczne//////////////////////////////

    @Test
    public void testUncoverRandomField() {
        int gridSize = 5; // Przykładowy rozmiar planszy
        gridViewActivity.createMineField(gridSize, 5);

        Random random = new Random();
        int row = random.nextInt(gridSize);
        int col = random.nextInt(gridSize);

        gridViewActivity.uncoverField(row, col);

        assertTrue(gridViewActivity.isFieldUncovered(row, col));
    }

    @Test
    public void testInitialUncoveredFields() {
        int gridSize = 5; // Przykładowy rozmiar planszy
        gridViewActivity.createMineField(gridSize, 5);

        // Sprawdź, czy wszystkie pola na planszy są początkowo nieodkryte
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                assertFalse(gridViewActivity.isFieldUncovered(row, col));
            }
        }
    }

}

