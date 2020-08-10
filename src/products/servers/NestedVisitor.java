package products.servers;

public class NestedVisitor {

    public interface Visitable {
        public void accept(NestedVisitor.Visitor visitor);
    }

    public interface Visitor {
        public void visit(Server serverItem);
    }
}
