import java.util.Set;

public abstract class Problem {
	public Object initialState;
	abstract boolean goal_test(Object state);
	abstract Set<Object> getSuccessors(Object state);
	abstract double step_cost(Object fromState, Object toState);
	abstract public double h(Object state); 
}
