package com.example.msdassignment;
import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

//DAO CLASS
@androidx.room.Dao
public interface Dao {

    // method to add data to planets.
    @Insert
    void insert(PlanetModal model);

    // method to update the data in planets.
    @Update
    void update(PlanetModal model);

    // delete a planet from the planets
    @Delete
    void delete(PlanetModal model);

    //delete all planets from planets using SQL query
    @Query("DELETE FROM planet_table")
    void deleteAllPlanets();

    // display all planets from planets in order of planet size
    @Query("SELECT * FROM planet_table ORDER BY planetsize ASC")
    LiveData<List<PlanetModal>> getAllPlanets();

    //search function

}

