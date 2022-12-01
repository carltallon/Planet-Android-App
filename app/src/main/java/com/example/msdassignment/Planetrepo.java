package com.example.msdassignment;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Planetrepo {

    // below line is the create a variable
    // for dao and list for all courses.
    private Dao dao;
    private LiveData<List<PlanetModal>> allPlanets;

    // creating a constructor for our variables
    // and passing the variables to it.
    public Planetrepo(Application application) {
        PlanetDatabase database = PlanetDatabase.getInstance(application);
        dao = database.Dao();
        allPlanets = dao.getAllPlanets();
    }

    // creating a method to insert the data to our database.
    public void insert(PlanetModal model) {
        new InsertPlanetAsyncTask(dao).execute(model);
    }

    // creating a method to update data in database.
    public void update(PlanetModal model) {
        new UpdatePlanetAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(PlanetModal model) {
        new DeletePlanetAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the courses.
    public void deleteAllPlanets() {
        new DeleteAllPlanetsAsyncTask(dao).execute();
    }

    // below method is to read all the courses.
    public LiveData<List<PlanetModal>> getAllPlanets() {
        return allPlanets;
    }

    // we are creating a async task method to insert new course.
    private static class InsertPlanetAsyncTask extends AsyncTask<PlanetModal, Void, Void> {
        private Dao dao;

        private InsertPlanetAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PlanetModal... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdatePlanetAsyncTask extends AsyncTask<PlanetModal, Void, Void> {
        private Dao dao;

        private UpdatePlanetAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PlanetModal... models) {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeletePlanetAsyncTask extends AsyncTask<PlanetModal, Void, Void> {
        private Dao dao;

        private DeletePlanetAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PlanetModal... models) {
            // below line is use to delete
            // our course modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all courses.
    private static class DeleteAllPlanetsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;
        private DeleteAllPlanetsAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllPlanets();
            return null;
        }
    }
}

