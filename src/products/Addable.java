package products;

import products.databases.UserDatabase;

public interface Addable {

    void addDb(UserDatabase.DbmsType dbms, String dbName, String userName, String password);
    //public void createDnsRecords(); // tuto dorobit dns zaznam a dns server
    void addDomain(String domain);
    /*TODO* spravit nieco na spravu domen, mozno aj rozhranie*/
}
