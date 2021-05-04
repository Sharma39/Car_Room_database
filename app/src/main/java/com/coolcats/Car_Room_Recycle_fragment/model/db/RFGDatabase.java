package com.coolcats.Car_Room_Recycle_fragment.model.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.coolcats.Car_Room_Recycle_fragment.model.data.Car;

@Database(version = 1, entities = {Car.class})
public abstract class RFGDatabase extends RoomDatabase {

    private static RFGDatabase database;
    public abstract CarDAO getDAO();

    public static RFGDatabase getDatabase(Context context){
        if(database == null)
            database = Room.databaseBuilder(
                    context,
                    RFGDatabase.class,
                    "rfg.db"
            ).build();

        return database;
    }
}