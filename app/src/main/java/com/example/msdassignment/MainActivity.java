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

public class MainActivity extends AppCompatActivity {

    // creating a variables for our recycler view.
    private RecyclerView planetsRV;
    private static final int ALL_PLANET_REQUEST = 1;
    private static final int EDIT_PLANET_REQUEST = 2;
    private ViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our variable for our recycler view and fab.
        planetsRV = findViewById(R.id.idRVPlanets);
        FloatingActionButton floatingaddplanetbutton = findViewById(R.id.addplanetbutton);

        // adding on click listener for floating action button.
        floatingaddplanetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new course
                // and passing a constant value in it.
                Intent intent = new Intent(MainActivity.this, NewPlanetActivity.class);
                startActivityForResult(intent, ALL_PLANET_REQUEST);
            }
        });

        // setting layout manager to our adapter class.
        planetsRV.setLayoutManager(new LinearLayoutManager(this));
        planetsRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final PlanetRecyclerViewAdapter adapter = new PlanetRecyclerViewAdapter();

        // setting adapter class for recycler view.
        planetsRV.setAdapter(adapter);

        // passing a data from view modal.
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);

        // below line is use to get all the courses from view modal.
        viewmodal.getAllPlanets().observe(this, new Observer<List<PlanetModal>>() {
            @Override
            public void onChanged(List<PlanetModal> models) {
                // when the data is changed in our models we are
                // adding that list to our adapter class.
                adapter.submitList(models);
            }
        });
        // below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                viewmodal.delete(adapter.getPlanetAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Planet deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView(planetsRV);
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new PlanetRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PlanetModal model) {
                // after clicking on item of recycler view
                // we are opening a new activity and passing
                // a data to our activity.
                Intent intent = new Intent(MainActivity.this, NewPlanetActivity.class);
                intent.putExtra(NewPlanetActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewPlanetActivity.EXTRA_PLANET_NAME, model.getPlanetName());
                intent.putExtra(NewPlanetActivity.EXTRA_PLANETSIZE, model.getPlanetsize());
                intent.putExtra(NewPlanetActivity.EXTRA_PLANETDISTANCE, model.getPlanetDistancetoSun());
                intent.putExtra(NewPlanetActivity.EXTRA_PLANETIMG, model.getPlanetIMAGE());

                // below line is to start a new activity and
                // adding a edit course constant.
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
