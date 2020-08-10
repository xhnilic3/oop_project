package products.databases;

import java.io.Serializable;

public class UserDatabase extends Database implements Serializable {

    public enum DbmsType{
        MYSQL,
        POSTGRES
    }

    DbmsType dbmsType;
    String dbName;
    String userName;
    String password;
    // has to be created on db server
    public UserDatabase(DatabaseServer server, DbmsType dbms, String dbName, String userName, String password){
        super(server);

        server.insertNewProduct(this);
        this.dbmsType = dbms;
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public String getDbmsType() {
        if (this.dbmsType == DbmsType.MYSQL)
            return "MySQL";
        else return "POSTGRES";
    }

    public String getDbName() { return this.dbName; }

}
