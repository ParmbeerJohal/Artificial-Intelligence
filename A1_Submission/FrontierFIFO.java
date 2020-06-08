import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

public class FrontierFIFO implements Frontier {
	Deque<Node> queue = new ArrayDeque<Node>();
	
	public boolean isEmpty() { return queue.isEmpty(); }
	
	public Node remove() { return queue.remove(); }
	
	public void insert(Node n) { queue.add(n); }
	
	public void insertAll(Set<Node> set_of_nodes) {
		for(Node n : set_of_nodes)
			queue.add(n);
	}
}