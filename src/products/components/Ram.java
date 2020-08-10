package products.components;

import java.io.Serializable;

public class Ram implements Serializable {
    private String producer;
    private String model;
    private int memorySize; // size in MB
    private int price = 0; // for default

    public Ram(){
    }

    public Ram(int size) {
        this.memorySize = size;
    }

    public void assignMemory(){
        System.out.println("Memory assigned..");
    }
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}