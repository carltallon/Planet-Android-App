package com.example.msdassignment;
import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

// Adding annotation
// to our Dao class
@androidx.room.Dao
public interface Dao {

    // below method is use to
    // add data to database.
    @Insert
    void insert(PlanetModal model);

    // below method is use to update
    // the data in our database.
    @Update
    void update(PlanetModal model);

    // below line is use to delete a
    // specific course in our database.
    @Delete
    void delete(PlanetModal model);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM planet_table")
    void deleteAllPlanets();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM planet_table ORDER BY planetName ASC")
    LiveData<List<PlanetModal>> getAllPlanets();
}

