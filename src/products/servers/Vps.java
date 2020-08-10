package products.servers;

import products.components.Os;
import products.components.Storage;
import products.databases.DatabaseAgregator;
import products.databases.ProductDatabase;

import java.util.ArrayList;

public class Vps extends Server {

    public Vps(ProductDatabase db, int ramSize, Storage.TypeOfStorage storageType, int storageSize,
               products.components.ServerComponents.Os.Distributions distro) {
        super(db, ramSize, storageType, storageSize, distro);
        this.init();
    }

    public Vps(ProductDatabase db){
        super(db);
    }

    @Override
    public void init() {
        super.init();
        this.storage.createStorage();
        this.storage.setProducer("Dell");
        this.storage.setModel("T440");
        this.ram.assignMemory();
        this.ram.setProducer("Dell");
        this.ram.setModel("PowerEdge T130");
        // firstly create partition without os, os installed afterwards (in init())
        this.os.setDistro(products.components.ServerComponents.Os.Distributions.CLEAR_INSTALL);
        this.setState(false);
    }


}