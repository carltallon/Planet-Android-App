package com.example.msdassignment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

// Main activty class
public class MainActivity extends AppCompatActivity {

    // Variable declarations,
    // recyclerview is used to display list
    // final int planet and edit declarations
    private RecyclerView planetsRV;
    private static final int ALL_PLANET_REQUEST = 1;
    private static final int EDIT_PLANET_REQUEST = 2;
    private ViewModal viewmodal;


    //method to set variables on creation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout to activity main file
        setContentView(R.layout.activity_main);

        //set recyclerview to planetsRV variable
        planetsRV = findViewById(R.id.idRVPlanets);
        FloatingActionButton floatingaddplanetbutton = findViewById(R.id.addplanetbutton);

        //On click listener method to act on add planet button
        floatingaddplanetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //using intent to open new planet activity
                Intent intent = new Intent(MainActivity.this, NewPlanetActivity.class);

                //Activity Result Launcher is called here to start add planet activity
                startActivityForResult(intent, ALL_PLANET_REQUEST);
            }
        });

        //set planetsRV to linerlayout with fixedsize
        planetsRV.setLayoutManager(new LinearLayoutManager(this));
        planetsRV.setHasFixedSize(true);

        //declaring adapter variable
        final PlanetRecyclerViewAdapter adapter = new PlanetRecyclerViewAdapter();

        //setting adapter to planetsRV adapter
        planetsRV.setAdapter(adapter);
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);

        //
        viewmodal.getAllPlanets().observe(this, new Observer<List<PlanetModal>>() {
            @Override
            public void onChanged(List<PlanetModal> models) {
                //
                adapter.submitList(models);
            }
        });
        //
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //
                viewmodal.delete(adapter.getPlanetAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Planet deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                attachToRecyclerView(planetsRV);
        //
        adapter.setOnItemClickListener(new PlanetRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PlanetModal model) {
                //
                Intent intent = new Intent(MainActivity.this, NewPlanetActivity.class);
                intent.putExtra(NewPlanetActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewPlanetActivity.EXTRA_PLANET_NAME, model.getPlanetName());
                intent.putExtra(NewPlanetActivity.EXTRA_PLANETSIZE, model.getPlanetsize());
                intent.putExtra(NewPlanetActivity.EXTRA_PLANETDISTANCE, model.getPlanetDistancetoSun());
                intent.putExtra(NewPlanetActivity.EXTRA_PLANETIMG, model.getPlanetIMAGE());

                //
                startActivityForResult(intent, EDIT_PLANET_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ALL_PLANET_REQUEST && resultCode == RESULT_OK) {
            String planetName = data.getStringExtra(NewPlanetActivity.EXTRA_PLANET_NAME);
            String planetDistancetoSun = data.getStringExtra(NewPlanetActivity.EXTRA_PLANETSIZE);
            String planetSize = data.getStringExtra(NewPlanetActivity.EXTRA_PLANETDISTANCE);
            String planetIMAGE = data.getStringExtra(NewPlanetActivity.EXTRA_PLANETIMG);
            PlanetModal model = new PlanetModal(planetName, planetDistancetoSun, planetSize, planetIMAGE);
            viewmodal.insert(model);
            Toast.makeText(this, "Planet saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_PLANET_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewPlanetActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Planet can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String planetName = data.getStringExtra(NewPlanetActivity.EXTRA_PLANET_NAME);
            String planetDistancetoSun = data.getStringExtra(NewPlanetActivity.EXTRA_PLANETSIZE);
            String planetSize = data.getStringExtra(NewPlanetActivity.EXTRA_PLANETDISTANCE);
            String planetIMAGE = data.getStringExtra(NewPlanetActivity.EXTRA_PLANETIMG);
            PlanetModal model = new PlanetModal(planetName, planetDistancetoSun, planetSize, planetIMAGE);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Planet updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Planet not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
