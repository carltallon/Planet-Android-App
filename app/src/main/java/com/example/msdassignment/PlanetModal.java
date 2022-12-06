package com.example.msdassignment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//
@Entity(tableName = "planet_table")
public class PlanetModal {

    //
    @PrimaryKey(autoGenerate = true)

    //
    private int id;

    //
    private String planetName;

    //
    private String planetsize;

    // .
    private String planetDistancetoSun;

    private String planetIMAGE;

    //
    public PlanetModal(String planetName, String planetsize, String planetDistancetoSun, String planetIMAGE) {
        this.planetName = planetName;
        this.planetsize = planetsize;
        this.planetDistancetoSun = planetDistancetoSun;
        this.planetIMAGE = planetIMAGE;
    }

    //
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

