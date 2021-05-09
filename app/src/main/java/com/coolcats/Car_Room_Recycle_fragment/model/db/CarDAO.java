package com.coolcats.Car_Room_Recycle_fragment.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.coolcats.Car_Room_Recycle_fragment.model.data.Car;

import java.util.List;

@Dao
public interface CarDAO {

    @Query("SELECT * FROM cars")
    List<Car> getAllCars();

    @Query("SELECT * FROM cars WHERE carId = :id")
    List<Car> getSingleCar(int id);

    @Query("UPDATE cars SET available = NOT available WHERE carId = :id")
    void toggleAvailability(int id);

    @Insert
    void insertCars(Car... car); //iT(t1), iT(t2, t3)

    @Delete
    void deleteCar(Car car);

    @Query("DELETE FROM cars")
    public void nukeTable();

}
