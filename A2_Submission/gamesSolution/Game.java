import java.util.Set;

public abstract class Game {
	public State currentState;

	abstract public boolean isWinState(State state);
	abstract public boolean isStuckState(State state);
	abstract public Set<State> getSuccessors(State state);
	abstract public double eval(State state); 
}

//no node class because we don't need to go back and produce a solution