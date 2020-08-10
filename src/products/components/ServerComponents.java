package products.components;

import java.io.Serializable;

public class ServerComponents implements Serializable {

    public static class Os  implements Serializable {  // static because of enum

        public enum Distributions{
            UBUNTU,
            DEBIAN,
            CENTOS,
            CLEAR_INSTALL;


        }

        private products.components.ServerComponents.Os.Distributions distro;
        private String version;
        private boolean upgrade;

        public String getStringDistro() {
            return this.distro.toString();
        }

        public products.components.ServerComponents.Os.Distributions getDistro() {
            return distro;
        }

        public void setDistro(products.components.ServerComponents.Os.Distributions distro) {
            this.distro = distro;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public boolean isUpgrade() {
            return upgrade;
        }

        public void setUpgrade(boolean upgrade) {
            this.upgrade = upgrade;
        }
    }


    public static class Ram implements Serializable{
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


    public static class Storage implements Serializable {

        public enum TypeOfStorage {
            SSD,
            HDD
        }

        products.components.Storage.TypeOfStorage type;
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

        public products.components.Storage.TypeOfStorage getType() {
            return type;
        }

        public void setType(products.components.Storage.TypeOfStorage type) {
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
}
