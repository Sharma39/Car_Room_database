package com.coolcats.Car_Room_Recycle_fragment.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "cars")
public class Car implements Parcelable {

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setCarPrice(double pricePerDay) {
        this.carPrice = pricePerDay;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setLicence(String licence) { this.licence = licence;   }

    public int getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }

    public double getCarPrice() {
        return carPrice;
    }

    public String getLicence(){ return licence;}

    public boolean getAvailable(){ return available;}

    @Override
    public String toString(){
        return "Car " +
                "name= " + carName + '\'' +
                ", id= " + carId +
                ", licence= " + licence +
                ", rent= " + carPrice +
                ", Availability =" + available +
                '}';
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "carId")
    private int carId;

    @ColumnInfo(name = "carName")
    private String carName;

    @ColumnInfo(name = "carPrice")
    private double carPrice;

    @ColumnInfo(name ="available")
    private boolean available;

    @ColumnInfo(name = "licence")
    private String licence;

    //To be used by the Room Database
    public Car(int carId, String carName, double carPrice, boolean available) {
        this.carId = carId;
        this.carName = carName;
        this.carPrice = carPrice;
        this.available = available;
    }

    //To be used by Data Entry Specialist
    @Ignore //Room database will ignore this
    public Car(String carName, double pricePerDay, String licence, boolean available) {
        this.carName = carName;
        this.carPrice = pricePerDay;
        this.licence = licence;
        this.available = available;
    }

    protected Car(Parcel in) {
        carId = in.readInt();
        carName = in.readString();
        carPrice = in.readInt();
        available = in.readByte() != 0;
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(carId);
        parcel.writeString(carName);
        parcel.writeDouble(carPrice);
        parcel.writeByte((byte) (available ? 1: 0));
    }
}
