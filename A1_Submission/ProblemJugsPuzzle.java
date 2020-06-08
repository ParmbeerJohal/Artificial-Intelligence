import java.util.HashSet;
import java.util.Set;

public class ProblemJugsPuzzle extends Problem {
    
	boolean goal_test(Object state) {
        StateJugsPuzzle jstate = (StateJugsPuzzle) state;
        
        if (jstate.jugArray[0]==1 || jstate.jugArray[1]==1 || jstate.jugArray[2]==1)
            return true;
        else return false;
	}
  
    Set<Object> getSuccessors(Object state) {
    	
    	Set<Object> successors = new HashSet<Object>();
        StateJugsPuzzle jstate = (StateJugsPuzzle) state;
        
        //By filling
        for(int i=0; i<3; i++) {
            if (jstate.jugArray[i] < StateJugsPuzzle.capacityArray[i]) 
            {
                StateJugsPuzzle successor_state = 
                                new StateJugsPuzzle(jstate);
                successor_state.jugArray[i] = StateJugsPuzzle.capacityArray[i];
                successors.add(successor_state);
            }
        }

        
        //By emptying
        for(int i=0; i<3; i++) {
            if (jstate.jugArray[i] > 0) 
            {
                StateJugsPuzzle successor_state = 
                                new StateJugsPuzzle(jstate);
                successor_state.jugArray[i] = 0;
                successors.add(successor_state);
            }
        }                
                
        
        //By pouring j to i
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if (j != i) 
                {
                    int newjug_i = min(jstate.jugArray[i] + jstate.jugArray[j], 
                                        StateJugsPuzzle.capacityArray[i]);
                    int juginc_i = newjug_i - jstate.jugArray[i];
                    
                    if (juginc_i > 0) {
                    
                        StateJugsPuzzle successor_state = 
                                        new StateJugsPuzzle(jstate);
                        successor_state.jugArray[i] += juginc_i;
                        successor_state.jugArray[j] -= juginc_i;
                        successors.add(successor_state);
                    }
                }
            }
        }        
        
        return successors;
    }
        

    
    private int min(int x, int y) {
        if (x < y) return x;
        return y;
    }
    
	double step_cost(Object fromState, Object toState) {
		
        StateJugsPuzzle s1 = (StateJugsPuzzle)fromState;
        StateJugsPuzzle s2 = (StateJugsPuzzle)toState;
        
        int sum_of_diffs = 0;
        int jugs_changed = 0;
        
        for(int i=0; i<3; i++) {
        	
        	int diff = Math.abs(s1.jugArray[i] - s2.jugArray[i]);
        	
        	if (diff != 0) {
        		jugs_changed += 1;
        		sum_of_diffs += diff;
        	}
        }
        
        if(jugs_changed == 1)
        	return sum_of_diffs;
        
        //jugs_changed == 2
        return sum_of_diffs/2.0;        
	}

	public double h(Object state) { return 0; }


	public static void main(String[] args) throws Exception {
		ProblemJugsPuzzle problem = new ProblemJugsPuzzle();
		problem.initialState = new StateJugsPuzzle();
		
		Search search  = new Search(problem);
		
		System.out.println("TreeSearch------------------------");
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		
		System.out.println("\n\nGraphSearch----------------------");
		System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
		System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
		
		System.out.println("\n\nIterativeDeepening----------------------");
		System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
		System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
	}
	
}
