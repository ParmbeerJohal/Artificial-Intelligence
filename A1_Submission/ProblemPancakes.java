import java.util.HashSet;
import java.util.Set;

public class ProblemPancakes extends Problem {
    
	boolean goal_test(Object state) {
        StatePancakes pstate = (StatePancakes) state;
        
        boolean result = true;
        for (int i = 0; i<pstate.N; i++)
        	result &= (i==pstate.pancakesArray[i]);
        
        return result;
	}
  
    Set<Object> getSuccessors(Object state) {
    	
    	Set<Object> successors = new HashSet<Object>();
        StatePancakes pstate = (StatePancakes) state;
        //System.out.println(pstate);
        
        for(int i=pstate.N-1; i>=0; i--) {
        	StatePancakes successor_state = new StatePancakes(pstate);
        	
        	//flip having the spatula under pancake i
        	
        	for(int j=0; j < (i+1)/2; j++) {
        		int temp = successor_state.pancakesArray[j];
        		successor_state.pancakesArray[j] = successor_state.pancakesArray[i-j]; 
        		successor_state.pancakesArray[i-j] = temp;
        	}
        		
        	//System.out.println(successor_state);
        	
        	successors.add(successor_state);
        }                
        
        //System.exit(0);
        
        return successors;
    }
        
    
	double step_cost(Object fromState, Object toState) {
		return 1.0;        
	}

	public double h(Object state) {
		
		StatePancakes s = (StatePancakes) state; 
		
		int sum = 0;
		for(int i=1; i<s.N; i++)
			sum += Math.abs(s.pancakesArray[i] - s.pancakesArray[i - 1]);
		
		return sum; 
	}


	public static void main(String[] args) throws Exception {
		ProblemPancakes problem = new ProblemPancakes();
		//problem.initialState = new StatePancakes(new int[] {9,8,7,6,5,4,3,2,1,0});
		//problem.initialState = new StatePancakes(new int[] {0, 2, 1, 4, 6, 3, 5});
		problem.initialState = new StatePancakes(new int[] {0, 1, 3, 5, 7, 9, 2, 4, 6, 8});
		
		Search search  = new Search(problem);
		
		//System.out.println("TreeSearch------------------------");
		System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());
		//System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		//System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		
		//System.out.println("\n\nGraphSearch----------------------");
		System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());
		//System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		//System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
		//System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
		
		//System.out.println("\n\nIterativeDeepening----------------------");
		//System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
		//System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
	
		
		/*Notes. For this problem if N=10 only with a heuristic obe is able to find solution.
		 * With N smallers, the other algos can find solution.*/
	
	}
	
}
