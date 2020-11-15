package ua.nure.khandzhian.tripiy.lab1.entity;

import java.util.Objects;

public class Phone implements Comparable<Phone> {

    private int id;
    private String model;
    private int powerOfCamera;
    private double diagonal;
    private double price;

    public Phone(int id, String model, int powerOfCamera, double diagonal, double price) {
        this.id = id;
        this.model = model;
        this.powerOfCamera = powerOfCamera;
        this.diagonal = diagonal;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPowerOfCamera() {
        return powerOfCamera;
    }

    public void setPowerOfCamera(int powerOfCamera) {
        this.powerOfCamera = powerOfCamera;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Phone phone = (Phone) o;
        return id == phone.id &&
                powerOfCamera == phone.powerOfCamera &&
                Double.compare(phone.diagonal, diagonal) == 0 &&
                Double.compare(phone.price, price) == 0 &&
                Objects.equals(model, phone.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, powerOfCamera, diagonal, price);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "ID: " + id +
                ", Model: " + model +
                ", Power Of Camera: " + powerOfCamera +
                ", Diagonal: " + diagonal +
                ", Price: " + price +
                "}\r\n";
    }

    @Override
    public int compareTo(Phone phone) {
        if (powerOfCamera > phone.getPowerOfCamera() &&
                diagonal > phone.getDiagonal() && price < phone.getPrice()) {
            return 1;
        }
        return -1;
    }
}
