import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;

public class FrontierPriorityQueue implements Frontier {
	PriorityQueue<Node> pqueue;
	Comparator<Node> comp;
	
	public FrontierPriorityQueue(Comparator<Node> comp) {
		this.comp = comp;
		this.pqueue = new PriorityQueue<Node>(comp);
	}
	
	public boolean isEmpty() { return pqueue.isEmpty(); }
	
	public Node remove() { return pqueue.remove(); }

	public void insert(Node n) { pqueue.add(n); }

	public void insertAll(Set<Node> set_of_nodes) {
		for(Node n : set_of_nodes)
			pqueue.add(n);
	}
}
