package com.example.saper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GridViewActivity extends AppCompatActivity {

    Boolean[][] mineField;
    private boolean gameOver = false;
    private int gridSize;
    private boolean[][] uncoveredFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity);
        // Retrieve the selected difficulty from the Intent
        Intent intent = getIntent();
        String selectedDifficulty = intent.getStringExtra("DIFFICULTY");
        Toast.makeText(GridViewActivity.this, "XD PRZEGRALES LMAO", Toast.LENGTH_LONG).show();
        GridView gridView = findViewById(R.id.gridView);
        // Use the selected difficulty to generate the grid view
        int gridSize;
        if ("Easy".equals(selectedDifficulty)) {
            gridSize = 5;
        } else if ("Medium".equals(selectedDifficulty)) {
            gridSize = 7;
        } else if ("Hard".equals(selectedDifficulty)) {
            gridSize = 9;
        } else {
            gridSize = 5; // Default to 5 for simplicity
        }


        gridView.setNumColumns(gridSize);
        mineField = createMineField(gridSize, 5);
        uncoveredFields = new boolean[gridSize][gridSize];

        // Create and set the custom adapter to the GridView
        ImageAdapter adapter = new ImageAdapter(this, gridSize);
        gridView.setAdapter(adapter);




        // Now, use gridSize to create your grid view with images
        // Implement your grid view generation logic here
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!gameOver) {
                    int row = position / gridSize;
                    int col = position % gridSize;
                    uncoverField(row, col);
                    boolean isUncovered = isFieldUncovered(row, col);
                    int image;
                    ImageView imageView = (ImageView) view;

                    if (mineField[row][col]) {
                        image = R.drawable.bomb;
                        Toast.makeText(gridView.getContext(), "XD PRZEGRALES LMAO", Toast.LENGTH_LONG).show();
                        gameOver = true;
                    } else
                        image = R.drawable.ok;

                    imageView.setImageResource(image);
                } else {
                    // Obsługa kliknięć po zakończeniu gry
                    Toast.makeText(gridView.getContext(), "Gra zakończona", Toast.LENGTH_SHORT).show();
                    // Tutaj możesz dodać dodatkową logikę lub działania po zakończeniu gry
                }
            }
        });

    }


    public Boolean[][] createMineField(int size, int bombCount) {
        Random random = new Random();
        Boolean[][] newMineField = new Boolean[size][size];


        for (int i = 0; size > i; i++)
            for (int j = 0; size > j; j++) {
                newMineField[i][j] = false;
            }

        for (int i = 0; bombCount > i; i++) {
            newMineField[random.nextInt(size)][random.nextInt(size)] = true;
        }


        return newMineField;
    }

    public void setMineField(Boolean[][] mineField) {
        this.mineField = mineField;
    }

    public boolean isGameOver() {
        return gameOver;


    }

    public boolean GameOver() {
        return !gameOver;
    }

    public Boolean[][] getMineField() {
        return mineField;
    }

    public void setGameOver(boolean isGameOver) {
        this.gameOver = isGameOver;
    }
    public void uncoverField(int row, int col) {
        // Sprawdź, czy pole na planszy nie zostało już wcześniej odsłonięte
        if (!isFieldUncovered(row, col)) {
            // Ustaw wartość pola na planszy jako odsłonięte
            uncoveredFields[row][col] = true;
        }
    }

    public boolean isFieldUncovered(int row, int col) {
        // Sprawdź, czy pole na planszy jest odsłonięte
        return uncoveredFields[row][col];
    }

    // Metoda wywoływana w onCreate() do inicjalizacji tablicy odsłoniętych pól
    private void initializeUncoveredFields(int gridSize) {
        uncoveredFields = new boolean[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                uncoveredFields[i][j] = false; // Inicjalizacja wszystkich pól jako nieodsłoniętych
            }
        }
    }
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < gridSize && col >= 0 && col < gridSize;
    }



}