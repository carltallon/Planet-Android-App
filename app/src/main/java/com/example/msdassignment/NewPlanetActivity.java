package com.example.msdassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewPlanetActivity extends AppCompatActivity {

    //
    private EditText planetNameEdt, planetsizeEdt, planetDistancetoSunEdt;
    private Button planetBtn;
    private ImageView planetIMG1,planetIMG2,planetIMG3,planetIMG4,planetIMG5,planetIMG6;
    private String planetIMAGE;


    private static final int CHOOSE_IMAGE_REQUEST = 1;

    //
    public static final String EXTRA_ID = "com.example.msdassignment.EXTRA_ID";
    public static final String EXTRA_PLANET_NAME = "com.example.msdassignment.EXTRA_PLANET_NAME";
    public static final String EXTRA_PLANETSIZE = "com.example.msdassignment.EXTRA_PLANETSIZE";
    public static final String EXTRA_PLANETDISTANCE = "com.example.msdassignment.EXTRA_PLANETDISTANCE";
    public static final String EXTRA_PLANETIMG = "com.example.msdassignment.EXTRA_PLANETIMG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_planet);

        //
        planetNameEdt = findViewById(R.id.idEdtPlanetName);
        planetsizeEdt = findViewById(R.id.idEdtPlanetSize);
        planetDistancetoSunEdt = findViewById(R.id.idEdtPlanetDistancetoSun);
        planetBtn = findViewById(R.id.idBtnSavePlanet);

        //
        planetIMG1 = findViewById(R.id.planet1IMG);
        planetIMG2 = findViewById(R.id.planet2IMG);
        planetIMG3 = findViewById(R.id.planet3IMG);
        planetIMG4 = findViewById(R.id.planet4IMG);
        planetIMG5 = findViewById(R.id.planet5IMG);
        planetIMG6 = findViewById(R.id.planet6IMG);


        //
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            planetNameEdt.setText(intent.getStringExtra(EXTRA_PLANET_NAME));
            planetsizeEdt.setText(intent.getStringExtra(EXTRA_PLANETSIZE));
            planetDistancetoSunEdt.setText(intent.getStringExtra(EXTRA_PLANETDISTANCE));
        }


        //
        planetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                String planetName = planetNameEdt.getText().toString();
                String planetSize = planetsizeEdt.getText().toString();
                String planetDistancetoSun = planetDistancetoSunEdt.getText().toString();

                if (planetName.isEmpty() || planetSize.isEmpty() || planetDistancetoSun.isEmpty() || planetIMAGE.isEmpty()) {
                    Toast.makeText(NewPlanetActivity.this, "Please enter the valid planet details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //
                savePlanet(planetName, planetSize, planetDistancetoSun,planetIMAGE);
                finish();
            }
        });

        //adding a click listener for choose image button
        planetIMG1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetIMAGE = "1";
                planetIMG1.setBackgroundResource(R.color.teal_200);
            }
        });

        //adding a click listener for choose image button
        planetIMG2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetIMAGE = "2";
                planetIMG2.setBackgroundResource(R.color.teal_200);
            }
        });


        //adding a click listener for choose image button
        planetIMG3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetIMAGE = "3";
                planetIMG3.setBackgroundResource(R.color.teal_200);
            }
        });


        //adding a click listener for choose image button
        planetIMG4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetIMAGE = "4";
                planetIMG4.setBackgroundResource(R.color.teal_200);
            }
        });


        //adding a click listener for choose image button
        planetIMG5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetIMAGE = "5";
                planetIMG5.setBackgroundResource(R.color.teal_200);
            }
        });


        //adding a click listener for choose image button
        planetIMG6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetIMAGE = "6";
                planetIMG6.setBackgroundResource(R.color.teal_200);
            }
        });

    }

    private void savePlanet(String planetName, String planetSize, String planetDistancetoSun, String planetIMAGE) {
        //
        Intent data = new Intent();

        //
        data.putExtra(EXTRA_PLANET_NAME, planetName);
        data.putExtra(EXTRA_PLANETSIZE, planetSize);
        data.putExtra(EXTRA_PLANETDISTANCE, planetDistancetoSun);
        data.putExtra(EXTRA_PLANETIMG, planetIMAGE);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        //
        setResult(RESULT_OK, data);

        //
        Toast.makeText(this, "Planet " + planetName + "has been added. ", Toast.LENGTH_SHORT).show();
    }


}