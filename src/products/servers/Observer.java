package products.servers;

import products.components.Os;
import products.components.Storage;
import products.databases.ProductDatabase;

public interface Observer  {
    public void updateState(boolean isPaid);
}
