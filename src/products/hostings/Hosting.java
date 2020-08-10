package products.hostings;

import products.Addable;
import products.Product;
import products.databases.DatabaseServer;
import products.databases.ProductDatabase;
import products.databases.UserDatabase;
import products.domains.Domain;

import java.io.Serializable;

public class Hosting extends Product implements Addable, Serializable {

    Domain domain;
    UserDatabase db;

    private static DatabaseServer userDbServer = null; // db's on hostings share dbserver

    public Hosting(ProductDatabase db){
        super(db);
    }

    @Override
    public void init() {

    }


    @Override
    public void addDb(UserDatabase.DbmsType dbms, String dbName, String userName, String password) {
        this.db = new UserDatabase(Hosting.getUserDbServer(), dbms, dbName, userName, password);
    }


    public void addDomain(String domain) {
        this.domain = new Domain(domain);
    }

    public enum Cms{
        WORDPRESS,
        JOOMLA,
        DRUPAL
    }

    Cms cms;

    public void installCms(Cms cms) {
        this.cms = cms;
    }

    // enforce that here will be just one db server
    public static DatabaseServer getUserDbServer() {
        if (userDbServer == null)
            userDbServer = new DatabaseServer();
        return userDbServer;
    }

    public String getHostingDomain() { return this.domain.getDomainName(); }
    public String getHostingDbmsType() { return  this.db.getDbmsType(); }
    public String getHostingDbName() {return  this.db.getDbName(); }
}
