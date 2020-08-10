package products.servers;

import products.databases.ProductDatabase;
/*TODO porozmyslat nad visitorom ohladom cien komponent*/
public class DedicatedServer extends Server {
    private int price;

    public DedicatedServer(ProductDatabase db) {
        super(db);
    }

    @Override
    public void init() {
        super.init();
        this.setPrice(this.ram.getPrice() + this.storage.getPrice());
        this.setPaid(false);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
