package products.hostings;

import products.databases.ProductDatabase;

import java.io.Serializable;

public class WpHosting extends Hosting implements Serializable {
    public WpHosting(ProductDatabase db){
        super(db);
    }

    @Override
    public void init() {
        super.init();
        this.installCms(Cms.WORDPRESS);
    }


}
