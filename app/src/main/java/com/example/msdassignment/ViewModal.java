package com.example.msdassignment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {

    //
    private LiveData<List<PlanetModal>> allPlanets;

    //
    private Planetrepo repository;

    //
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new Planetrepo(application);
        allPlanets = repository.getAllPlanets();
    }

    // method to insert into planet modal
    public void insert(PlanetModal model) {
        repository.insert(model);
    }

    // method to delete from planet modal
    public void delete(PlanetModal model) {
        repository.delete(model);
    }

    // method to update selected planet from planet model
    public void update(PlanetModal model) {
        repository.update(model);
    }

    // method to return all live planets in list format
    public LiveData<List<PlanetModal>> getAllPlanets() {
        return allPlanets;
    }

    // method to delete all planets from model
    public void deleteAllPlanets() {
        repository.deleteAllPlanets();
    }
}

