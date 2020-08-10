package products.servers;

public class ServerStateVisitor implements NestedVisitor.Visitor {
    @Override
    public void visit(Server serverItem) {
        serverItem.setState(true);
        System.out.println("Server state status; " + serverItem.getState());
    }
}
