import java.util.Set;

public class Search {
	Game game;
	
	public Search(Game game) { this.game = game; }
	
	public State bestSuccessorState(int depth) {
		
		double max = Double.NEGATIVE_INFINITY;
		State max_state = null;
		
		for(State state : game.getSuccessors(game.currentState)) {
			double min_val = MinValue(state,depth);
			if(max < min_val) {
				max = min_val;
				max_state = state;
			}
		}
			
		return max_state;
	}
	
	double MaxValue(State state, int depth) {
		Set<State> successors = game.getSuccessors(state);
		
		if (depth==0 || successors==null)
			return game.eval(state);
		
		double v = Double.NEGATIVE_INFINITY;
		
		for(State s : game.getSuccessors(state)) 
			v = Math.max(v, MinValue(s,depth-1));
		return v;
	}
	
	double MinValue(State state, int depth) {
		Set<State> successors = game.getSuccessors(state);
		
		if (depth==0 || successors==null)
			return game.eval(state);
		
		double v = Double.POSITIVE_INFINITY;
		
		for(State s : game.getSuccessors(state)) 
			v = Math.min(v, MaxValue(s,depth-1));
		return v;
	}	
}
