package com.example.saper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    String[] difficultyLevels = {"Easy", "Medium", "Hard"};
    String selectedDifficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficultyLevels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner difficultySpinner = findViewById(R.id.spinner);
        difficultySpinner.setAdapter(adapter);

        Button startButton = findViewById(R.id.button);


        difficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 selectedDifficulty = difficultyLevels[position];
                // Handle the selected difficulty level
                // You can use it in your game logic or perform any other actions
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here if not needed
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GridViewActivity.class);

                // Pass the selected difficulty as an extra to the new activity
                intent.putExtra("DIFFICULTY", selectedDifficulty);

                // Start the new activity
                startActivityForResult(intent,1);
            }
        });

    }
}