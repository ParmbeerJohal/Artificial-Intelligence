public class ComparatorF implements java.util.Comparator<Node> {
	Problem problem;	
	public ComparatorF(Problem problem) { this.problem = problem; }
	
	public int compare(Node x, Node y) {
		return Double.compare( 	x.path_cost + problem.h(x.state), 
								y.path_cost + problem.h(y.state) );		
	}
}