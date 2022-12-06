package com.example.msdassignment;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

//PLANET REPOSITORY CLASS
public class Planetrepo {

    //initialising DAO
    private Dao dao;

    //CREATING ALL PLANET LIST which is a live read of planets in ROOM database.
    private LiveData<List<PlanetModal>> allPlanets;

    // creating a constructor for our variables
    // and passing the variables to it.
    public Planetrepo(Application application) {
        PlanetDatabase database = PlanetDatabase.getInstance(application);
        dao = database.Dao();
        allPlanets = dao.getAllPlanets();
    }

    // METHOD CREATION TO INSERT PLANET DATA IN PLANET DAO INSTANCE
    public void insert(PlanetModal model) {
        new InsertPlanetAsyncTask(dao).execute(model);
    }

    // METHOD CREATION TO UPDATE SELECTED PLANET IN DAO INSTANCE
    public void update(PlanetModal model) {
        new UpdatePlanetAsyncTask(dao).execute(model);
    }

    // method creation to delete SELECTED planet from DAO instance
    public void delete(PlanetModal model) {
        new DeletePlanetAsyncTask(dao).execute(model);
    }

    // method creation to delete all planets from DAO instance
    public void deleteAllPlanets() {
        new DeleteAllPlanetsAsyncTask(dao).execute();
    }

    // method to return all planets in List format
    public LiveData<List<PlanetModal>> getAllPlanets() {
        return allPlanets;
    }

    // creating static method insert a planet into PLANET DAO INSTANCE
    private static class InsertPlanetAsyncTask extends AsyncTask<PlanetModal, Void, Void> {
        private Dao dao;

        private InsertPlanetAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PlanetModal... model) {
            // calling method to insert planet into planet modal
            dao.insert(model[0]);
            return null;
        }
    }

    // static class to update selected planet in PLANET DAO INSTANCE
    private static class UpdatePlanetAsyncTask extends AsyncTask<PlanetModal, Void, Void> {
        private Dao dao;

        private UpdatePlanetAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PlanetModal... models) {
            // update planet info in planet modal
            dao.update(models[0]);
            return null;
        }
    }

    //create a static method to delete ONE planet from planet DAO instance
    private static class DeletePlanetAsyncTask extends AsyncTask<PlanetModal, Void, Void> {
        private Dao dao;

        private DeletePlanetAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PlanetModal... models) {
            //delete selected planet from modal
            dao.delete(models[0]);
            return null;
        }
    }

    //create a static class to delete all planets from planet DAO instance
    private static class DeleteAllPlanetsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;
        private DeleteAllPlanetsAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            //call method to delete all planets from DAO instance
            dao.deleteAllPlanets();
            return null;
        }
    }
}

