package products.hostings;

import products.databases.ProductDatabase;

import java.io.Serializable;

public class ClassicHosting extends Hosting implements Serializable {
    public ClassicHosting(ProductDatabase db){
        super(db);
    }

    @Override
    public void init() {
        super.init();
        this.cms = null;
    }
}

