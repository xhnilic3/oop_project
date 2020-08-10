package products.servers;

/**
 * Implementation of Observer which observers if the payment for product (server) was realized
 */
public class ServerObserver implements Observer, NestedVisitor.Visitable {
    private boolean isPaid = false;
    private static int observerIDTracker = 0;
    private int observerID;

    private Subject server;

    public ServerObserver(Subject server, NestedVisitor.Visitor visitor) {
        this.server = server;
        this.observerID = ++observerIDTracker;
        server.register(this);
        visitor.visit((Server) server);
    }
    @Override
    public void updateState(boolean isPaid) {
        this.isPaid = isPaid;
        printIsPaidStatus();
    }

    public void printIsPaidStatus() {
        System.out.println("Paid status of this server is: " + isPaid);
    }

    @Override
    public void accept(NestedVisitor.Visitor visitor) {
        visitor.visit((Server) this.server);
    }
}
