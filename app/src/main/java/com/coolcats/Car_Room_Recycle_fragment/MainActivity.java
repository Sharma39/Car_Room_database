package com.coolcats.Car_Room_Recycle_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.coolcats.Car_Room_Recycle_fragment.model.data.Car;
import com.coolcats.Car_Room_Recycle_fragment.model.db.RFGDatabase;
//import com.coolcats.Car_Room_Recycle_fragment.utli.SingletonExample;
import com.coolcats.Car_Room_Recycle_fragment.view.CarAdapter;
import com.coolcats.Car_Room_Recycle_fragment.view.InfoFragment;
import com.coolcats.Car_Room_Recycle_fragment.view.InputFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements InputFragment.CarDelegate, CarAdapter.UpdateCarDelegate {

    private InputFragment inputFragment;
    private InfoFragment infoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputFragment = (InputFragment) getSupportFragmentManager().findFragmentById(R.id.input_fragment);
        infoFragment = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.info_fragment);
        readDatabase();
    }

    @Override
    public void insertCar(Car car) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Log.d("TAG_X", "inserting car");
                RFGDatabase.getDatabase(MainActivity.this).getDAO().insertCars(car);
                Log.d("TAG_X", "reading all...");
                readDatabase();
            }
        }.start();
    }

    public void switchAvailability(int id){
        new Thread() {
            @Override
            public void run() {

                super.run();
                Log.d("TAG_X", "updating topic");
                RFGDatabase.getDatabase(MainActivity.this).getDAO().toggleAvailability(id);
                Log.d("TAG_X", "reading all...");
                readDatabase();

            }
        }.start();
    }

    private void readDatabase() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                List<Car> cars = RFGDatabase.getDatabase(MainActivity.this).getDAO().getAllCars();

                runOnUiThread(() -> {
                    infoFragment.updateList(cars);
                });
                getDisplayUser(cars);
            }
        }.start();
    }

    @Override
    public void updateCar(Car car){
        new Thread() {
            @Override
            public void run() {
                super.run();
                RFGDatabase.getDatabase(MainActivity.this).getDAO().toggleAvailability(car.getCarId());
                readDatabase();
            }
        }.start();
    }

    private void getDisplayUser(List<Car> car) {
        for(int i =0; i < car.size(); i++){
            Log.d("TAG_X", car.get(i).toString());
        }
    }
}