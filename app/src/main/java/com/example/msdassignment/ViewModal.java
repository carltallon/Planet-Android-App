package com.example.msdassignment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {

    // creating a new variable for course repository.
    private Planetrepo repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<PlanetModal>> allPlanets;

    // constructor for our view modal.
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new Planetrepo(application);
        allPlanets = repository.getAllPlanets();
    }

    // below method is use to insert the data to our repository.
    public void insert(PlanetModal model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(PlanetModal model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(PlanetModal model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllPlanets() {
        repository.deleteAllPlanets();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<PlanetModal>> getAllPlanets() {
        return allPlanets;
    }
}

