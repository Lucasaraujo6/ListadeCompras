package com.jogos.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

public class Item extends AppCompatActivity {
    private int id;
    private String name;
    private int amount;
    private String brand;
    private double price;
    private String imageUrl;
    private String observations;
    private boolean isChecked;



    public Item(int id, String name, int amount, String brand, double price, String imageUrl, String observations, boolean isChecked) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.brand = brand;
        this.price = price;
        this.imageUrl = imageUrl;
        this.observations = observations;
        this.isChecked = isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = !checked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return ""+amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "Itens{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", observations='" + observations + '\'' +
                '}';
    }

}