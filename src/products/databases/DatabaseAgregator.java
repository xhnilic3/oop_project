package products.databases;

import java.io.Serializable;

public class DatabaseAgregator implements Serializable {
    static DatabaseServer dbServer = new DatabaseServer();
    static ProductDatabase vpsDb = new ProductDatabase(dbServer);
    static ProductDatabase hostingDb = new ProductDatabase(dbServer);


    private static DatabaseAgregator agregator = null;

    // singleton - (we want one and only one instance)
    public static DatabaseAgregator getAgregator() {
        if (agregator == null)
            agregator = new DatabaseAgregator();
        return agregator;
    }

    public static ProductDatabase getVpsDb(){
        return DatabaseAgregator.vpsDb;
    }
    public static ProductDatabase getHostingDb() {return  DatabaseAgregator.hostingDb; }

    public static DatabaseServer getDbServer() {
        return dbServer;
    }

    public static void setVpsDb(ProductDatabase vpsDb) {
        DatabaseAgregator.vpsDb = vpsDb;
    }

    public static void setHostingDb(ProductDatabase hostingDb) {
        DatabaseAgregator.hostingDb = hostingDb;
    }
}
