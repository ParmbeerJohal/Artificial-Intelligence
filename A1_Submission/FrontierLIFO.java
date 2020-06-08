import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

public class FrontierLIFO implements Frontier {
	Deque<Node> stack = new ArrayDeque<Node>();
	
	public boolean isEmpty() { return stack.isEmpty(); }
	
	public Node remove() { return stack.pop(); }

	public void insert(Node n) { stack.push(n); }

	public void insertAll(Set<Node> set_of_nodes) {
		for(Node n : set_of_nodes)
			stack.push(n);
	}
}