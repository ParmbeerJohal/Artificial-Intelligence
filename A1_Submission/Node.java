public class Node {
	Object state;
	Node parent_node;
	double path_cost;
	int depth;
	int order = -1; //order of expansion; default of -1 means not expanded
}
