package products.databases;

import products.Product;

import java.io.Serializable;
import java.util.HashMap;

public class DatabaseServer implements Serializable {

    private HashMap<Database, String> map = new HashMap<>();

    public DatabaseServer(){
        System.out.println("DB server successfully created!");
    }

    public void insertNewProduct(Database database){
        this.map.put(database, database.getUuid());
    }

    /**
     * Looking for match of uuid across database
     * @param uuid
     * @return int
     */
    public int findMatch(String uuid){
        for (String i : map.values()) {
            if (i == uuid)
                return 1;
        }
        return 0;
    }



}
