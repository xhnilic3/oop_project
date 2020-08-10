package products.servers;

import products.Addable;
import products.Product;
import products.components.Os;
import products.components.Ram;
import products.components.Storage;
import products.databases.DatabaseServer;
import products.databases.ProductDatabase;
import products.databases.UserDatabase;
import products.domains.Domain;
import products.domains.MultiDomain;
import products.domains.NumOfDomainsExceededException;

import java.util.ArrayList;

public  abstract class Server extends Product implements Addable, Subject {
    private transient ArrayList<products.servers.Observer> observers;
    products.components.ServerComponents.Ram ram = new products.components.ServerComponents.Ram();
    Storage storage = new Storage();
    products.components.ServerComponents.Os os = new products.components.ServerComponents.Os();
    private MultiDomain multiDomain = new MultiDomain();

    private boolean state; // true is running, false is stopped

    DatabaseServer dbServer = new DatabaseServer();



    public Server(ProductDatabase db) { super(db); observers = new ArrayList<products.servers.Observer>();}


    @Override
    public void init() {

    }

    public Server(ProductDatabase db, int ramSize, Storage.TypeOfStorage storageType, int storageSize,
                  products.components.ServerComponents.Os.Distributions distro){
        super(db);
        observers = new ArrayList<products.servers.Observer>();
        createComponents(ramSize, storageSize, storageType, distro);
    }
    // partial solution (subsolution?) of setting up components of server
    private void createRam(int size) { // this will be additionally specified in derived classes (producer, model...)
        this.ram.setMemorySize(size);
    }

    // partial solution (subsolution?) of setting up components of server
    private void createStorage(int size, Storage.TypeOfStorage type){
        this.storage.setSizeOfStorage(size);
        this.storage.setType(type);
    }

    // partial solution (subsolution?) of setting up components of server
    private void createOs(products.components.ServerComponents.Os.Distributions distro){
        this.os.setDistro(distro);

        if (distro == products.components.ServerComponents.Os.Distributions.CLEAR_INSTALL)
            this.os.setDistro(distro);
        else if (distro == products.components.ServerComponents.Os.Distributions.UBUNTU) {
            this.os.setDistro(distro);
            this.os.setVersion("18.04");
            this.os.setUpgrade(false);
        }
        else if (distro == products.components.ServerComponents.Os.Distributions.DEBIAN){
            this.os.setDistro(distro);
            this.os.setVersion("10");
            this.os.setUpgrade(false);
        }
        else if(distro == products.components.ServerComponents.Os.Distributions.CENTOS){
            this.os.setDistro(distro);
            this.os.setVersion("8");
            this.os.setUpgrade(false);
        }
    }

    /**
     * Method that setup components of server (OS, RAM, STORAGE)
     * @param ramSize
     * @param storageSize
     * @param storageType SSD/HDD
     * @param distro CLEAR install and serveral linux distributions (can be found in
     *               components.ServerComponents.Os.Distributions)
     */
    private void createComponents(int ramSize, int storageSize, Storage.TypeOfStorage storageType,
                                  products.components.ServerComponents.Os.Distributions distro) {
        createRam(ramSize);
        createStorage(storageSize, storageType);
        createOs(distro);
    }


    @Override
    public void addDb(UserDatabase.DbmsType dbms, String dbName, String userName, String password) {
        System.out.println("Db server installation..");
        UserDatabase db = new UserDatabase(this.dbServer, dbms, dbName, userName, password);
        System.out.println("Successful..");
        System.out.println("Database" + dbName + " created on demand..");
    }

    @Override
    public void addDomain(String domain) {
        Domain domain1 = new Domain(domain);
        try {
            this.multiDomain.addDomain(domain1);
        } catch (NumOfDomainsExceededException e) {
            e.printStackTrace();
        }
    }



    // for setCellValueFactory

    public String getServerDistro() { return  this.os.getDistro().toString(); }
    public String getServerRam() { return String.valueOf(this.ram.getMemorySize());}
    public String getServerStorage() { return String.valueOf(this.storage.getSizeOfStorage()); }
    public String getServerTypeOfStorage() { return this.storage.getType().toString();}


    /*Os getters & setters*/

    public String getOsDistro() { return String.valueOf(this.os.getDistro()); }

    public String getOsVersion() { return this.os.getVersion(); }

    public boolean getOsUpdate() { return this.os.isUpgrade(); }





    /*state getters & setters*/

    public boolean getState() {
        return this.state;
    }
    public void setState(boolean state){
        this.state = state;
    }

    public void setOs(products.components.ServerComponents.Os.Distributions distro) {
        this.os.setDistro(distro);
    }


    @Override
    public void register(products.servers.Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void unregister(Observer deleteObserver) {
        int observerIndex = observers.indexOf(deleteObserver);

        observers.remove(observerIndex);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers){
            observer.updateState(this.isPaid());
        }
    }

    @Override
    public void setPaid(boolean paid) {
        super.setPaid(paid);
        notifyObserver();
    }
}