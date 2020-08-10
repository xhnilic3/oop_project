package products.databases;

import products.Product;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class ProductDatabase extends Database implements Serializable {


    public ProductDatabase(DatabaseServer server){
        super(server);
        System.out.println("ProductDatabase constructed");
    }


}