public class ComparatorG implements java.util.Comparator<Node> {
	public int compare(Node x, Node y) {
		return Double.compare(x.path_cost, y.path_cost);		
	}
}