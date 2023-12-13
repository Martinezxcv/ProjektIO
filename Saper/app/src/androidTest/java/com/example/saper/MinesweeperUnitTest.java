package com.example.saper;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void testGameEndCondition() {
        // Test sprawdzający warunek końca gry
        gridViewActivity.setGameOver(true);
        assertTrue(gridViewActivity.isGameOver());
    }

    @Test
    public void testGenerateMineField() {
        // Test generowania planszy w grze Saper
        int gridSize = 5;
        int bombCount = 5;

        // Tworzenie planszy
        gridViewActivity.createMineField(gridSize, bombCount);

        // Pobieranie planszy
        Boolean[][] mineField = gridViewActivity.getMineField();

        // Sprawdzenie czy plansza została poprawnie wygenerowana
        assertEquals(gridSize, mineField.length);
        for (int i = 0; i < gridSize; i++) {
            assertEquals(gridSize, mineField[i].length);
        }

        // Sprawdzenie czy liczba bomb jest zgodna z oczekiwaniami
        int count = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (mineField[i][j]) {
                    count++;
                }
            }
        }
        assertEquals(bombCount, count);
    }

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

}

