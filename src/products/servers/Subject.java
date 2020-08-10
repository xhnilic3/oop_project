package products.servers;

public interface Subject {

    public void register(products.servers.Observer o);
    public void unregister(Observer o);
    public void notifyObserver();
}
