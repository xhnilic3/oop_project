package products.components;

import java.io.Serializable;

public class Storage implements Serializable {

    public enum TypeOfStorage {
        SSD,
        HDD
    }

    TypeOfStorage type;
    String producer;
    String model;
    int sizeOfStorage; // size in GB
    private int price = 0; // for default

    public Storage(){}

    public Storage(int size){
        this.sizeOfStorage = size;
    }

    public void createStorage(){
        System.out.println("Storage created..");
    }

    public TypeOfStorage getType() {
        return type;
    }

    public void setType(TypeOfStorage type) {
        this.type = type;
    }

    public int getSizeOfStorage() {
        return sizeOfStorage;
    }

    public void setSizeOfStorage(int sizeOfStorage) {
        this.sizeOfStorage = sizeOfStorage;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
