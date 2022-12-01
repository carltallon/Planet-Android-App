package com.example.msdassignment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// below line is for setting table name.
@Entity(tableName = "planet_table")
public class PlanetModal {

    // below line is to auto increment
    // id for each course.
    @PrimaryKey(autoGenerate = true)

    // variable for our id.
    private int id;

    // below line is a variable
    // for course name.
    private String planetName;

    // below line is use for
    // course description.
    private String planetsize;

    // below line is use
    // for course duration.
    private String planetDistancetoSun;

    private String planetIMAGE;

    // below line we are creating constructor class.
    // inside constructor class we are not passing
    // our id because it is incrementing automatically
    public PlanetModal(String planetName, String planetsize, String planetDistancetoSun, String planetIMAGE) {
        this.planetName = planetName;
        this.planetsize = planetsize;
        this.planetDistancetoSun = planetDistancetoSun;
        this.planetIMAGE = planetIMAGE;
    }

    // on below line we are creating
    // getter and setter methods.
    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public String getPlanetsize() {
        return planetsize;
    }

    public void setPlanetsize(String planetsize) {
        this.planetsize = planetsize;
    }

    public String getPlanetDistancetoSun() {
        return planetDistancetoSun;
    }

    public void setPlanetDistancetoSun(String planetDistancetoSun) {
        this.planetDistancetoSun = planetDistancetoSun;
    }

    public String getPlanetIMAGE() {
        return planetIMAGE;
    }

    public void setPlanetIMAGE(String planetIMAGE) {
        this.planetIMAGE = planetIMAGE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

