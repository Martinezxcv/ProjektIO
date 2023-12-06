package com.example.saper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;

public class GridViewActivity extends AppCompatActivity {

    Boolean[][] mineField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity);

        // Retrieve the selected difficulty from the Intent
        Intent intent = getIntent();
        String selectedDifficulty = intent.getStringExtra("DIFFICULTY");
        Toast.makeText(GridViewActivity.this, selectedDifficulty, Toast.LENGTH_LONG).show();
        GridView gridView = findViewById(R.id.gridView);
        // Use the selected difficulty to generate the grid view
        int gridSize;
        if (selectedDifficulty.equals("Easy")) {
            gridSize = 5;
        } else if (selectedDifficulty.equals("Medium")) {
            gridSize = 7;
        } else if (selectedDifficulty.equals("Hard")) {
            gridSize = 9;
        } else {
            // Handle unexpected difficulty levels
            gridSize = 5; // Default to 5 for simplicity
        }

        gridView.setNumColumns(gridSize);
        mineField = createMineField(gridSize, 5);

        // Create and set the custom adapter to the GridView
        ImageAdapter adapter = new ImageAdapter(this, gridSize);
        gridView.setAdapter(adapter);


        // Now, use gridSize to create your grid view with images
        // Implement your grid view generation logic here
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int row = position / gridSize;
                int col = position % gridSize;
                int image;
                ImageView imageView = (ImageView) view;

                if (mineField[row][col]) {
                    image = R.drawable.bomb;
                    Toast.makeText(gridView.getContext(), "XD PRZEGRALES LMAO", Toast.LENGTH_LONG).show();
                } else
                    image = R.drawable.ok;

                imageView.setImageResource(image);
            }
        });
    }


    private Boolean[][] createMineField(int size, int bombCount) {
        Random random = new Random();
        Boolean[][] newMineField = new Boolean[size][size];

        for (int i = 0; size > i; i++)
            for (int j = 0; size > j; j++) {
                newMineField[i][j] = false;
            }

        for (int i = 0; bombCount + 1 > i; i++) {
            newMineField[random.nextInt(size)][random.nextInt(size)] = true;
        }

        return newMineField;
    }

}
