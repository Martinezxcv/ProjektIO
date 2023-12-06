package com.example.saper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GridViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity);

        // Retrieve the selected difficulty from the Intent
        Intent intent = getIntent();
        String selectedDifficulty = intent.getStringExtra("DIFFICULTY");
        Toast.makeText(GridViewActivity.this,selectedDifficulty,Toast.LENGTH_LONG).show();
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

        // Create and set the custom adapter to the GridView
        ImageAdapter adapter = new ImageAdapter(this, gridSize);
        gridView.setAdapter(adapter);

        // Now, use gridSize to create your grid view with images
        // Implement your grid view generation logic here


    }

}
