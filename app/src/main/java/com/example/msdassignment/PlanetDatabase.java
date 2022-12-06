package com.example.msdassignment;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//
@Database(entities = {PlanetModal.class}, version = 3)
public abstract class PlanetDatabase extends RoomDatabase {

    //
    private static PlanetDatabase instance;

    //
    public abstract Dao Dao();

    //
    public static synchronized PlanetDatabase getInstance(Context context) {
        //
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(), PlanetDatabase.class, "planet_database")
                            .fallbackToDestructiveMigration()

                            .addCallback(roomCallback)

                            .build();
        }
        //return instance when it is created
        return instance;
    }

    //
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    //
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(PlanetDatabase instance) {
            Dao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}

