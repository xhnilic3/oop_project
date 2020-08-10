package products.servers;

import products.components.Os;
import products.components.Storage;
import products.databases.ProductDatabase;
import products.domains.Domain;

import java.util.ArrayList;
import java.util.Observer;

public class Vdc extends Server {
    private Vps vps[] = new Vps[5];
    private int i = 0;
    public Vdc(ProductDatabase db) {
        super(db);


    }


    /**
     * Method which set up VPS's belonging to VDC one by one (can change setup of existing vps as well)
     * @param ramSize
     * @param storageType - SSD/HDD
     * @param storageSize
     * @param distro - CLEAR, UBUNTU, CENTOS, DEBIAN
     * @return void
     */
    private void setupVps(int ramSize, Storage.TypeOfStorage storageType, int storageSize,
                          products.components.ServerComponents.Os.Distributions distro){
        this.vps[this.i].ram.setMemorySize(ramSize);

        this.vps[this.i].storage.setType(storageType);
        this.vps[this.i].storage.setSizeOfStorage(storageSize);

        this.vps[this.i].os.setDistro(distro);
    }

    @Override
    public void init() {
        super.init();
        this.vps[this.i].setState(false);
        // vdc is prepaid service, therefore is not necessary to pay for every instance
        this.vps[this.i].setPaid(true);
        this.i++;
    }

    /**
     * @param db
     * @param ramSize
     * @param storageType
     * @param storageSize
     * @param distro
     */
    public void addVps(ProductDatabase db, int ramSize, Storage.TypeOfStorage storageType, int storageSize,
                       products.components.ServerComponents.Os.Distributions distro){
        this.vps[i] = new Vps(db);
        this.setupVps(ramSize, storageType, storageSize, distro);
        this.init();
    }

    public void getVpsDistro(int i) {
        System.out.println(this.vps[i].getOsDistro());
    }


}
