import java.util.Set;

public interface Frontier {
	boolean isEmpty();
	Node remove();
	void insert(Node n);
	void insertAll(Set<Node> set_of_nodes);
}
