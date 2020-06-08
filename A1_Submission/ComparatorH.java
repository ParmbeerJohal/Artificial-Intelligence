public class ComparatorH implements java.util.Comparator<Node> {
	Problem problem;
	public ComparatorH(Problem problem) { this.problem = problem; }
	
	public int compare(Node x, Node y) {
		return Double.compare( problem.h(x.state), problem.h(y.state) );		
	}
}