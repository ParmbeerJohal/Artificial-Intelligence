import java.util.HashSet;
import java.util.Set;

public class ProblemWolfGoatCabbage extends Problem {
	
	//boat, cabbage, goat and wolf
	
    static final int b = 0;
    static final int c = 1;
    static final int g = 2;
    static final int w = 3;
    
	boolean goal_test(Object state) {
		StateWolfGoatCabbage cgw_state = (StateWolfGoatCabbage) state;
        
        if (	cgw_state.sarray[b]==0 && 
        		cgw_state.sarray[c]==0 && 
        		cgw_state.sarray[g]==0 &&
        		cgw_state.sarray[w]==0)
            return true;
        else 
        	return false;
	}
  
    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
        StateWolfGoatCabbage cgw_state = (StateWolfGoatCabbage) state;
        
        //Let's create without any constraint, then remove the illegal ones
        StateWolfGoatCabbage successor_state;
        
        
        //boat and cabbage from left to right
        successor_state = new StateWolfGoatCabbage(cgw_state);
        successor_state.sarray[b] -= 1;
        successor_state.sarray[c] -= 1;
        if (isValid(successor_state)) set.add(successor_state);

        //boat and cabbage from right to left
        successor_state = new StateWolfGoatCabbage(cgw_state);
        successor_state.sarray[b] += 1;
        successor_state.sarray[c] += 1;
        if (isValid(successor_state)) set.add(successor_state);        
        
        
        //boat and goat from left to right
        successor_state = new StateWolfGoatCabbage(cgw_state);
        successor_state.sarray[b] -= 1;
        successor_state.sarray[g] -= 1;
        if (isValid(successor_state)) set.add(successor_state);

        //boat and goat from right to left
        successor_state = new StateWolfGoatCabbage(cgw_state);
        successor_state.sarray[b] += 1;
        successor_state.sarray[g] += 1;
        if (isValid(successor_state)) set.add(successor_state);   
        
        
        //boat and wolf from left to right
        successor_state = new StateWolfGoatCabbage(cgw_state);
        successor_state.sarray[b] -= 1;
        successor_state.sarray[w] -= 1;
        if (isValid(successor_state)) set.add(successor_state);

        //boat and wolf from right to left
        successor_state = new StateWolfGoatCabbage(cgw_state);
        successor_state.sarray[b] += 1;
        successor_state.sarray[w] += 1;
        if (isValid(successor_state)) set.add(successor_state); 
        
        
        //boat from left to right
        successor_state = new StateWolfGoatCabbage(cgw_state);
        successor_state.sarray[b] -= 1;
        if (isValid(successor_state)) set.add(successor_state);    
        
        //boat from right to left
        successor_state = new StateWolfGoatCabbage(cgw_state);
        successor_state.sarray[b] += 1;
        if (isValid(successor_state)) set.add(successor_state); 
        
        //System.out.println(set);
        //System.exit(1);
        return set;
    }
    
    private boolean isValid(StateWolfGoatCabbage state)
    {   
        //Checking to see if any element of the array is negative or greater than 1
        for (int i=0; i<state.N; i++)
            if (state.sarray[i] < 0 || state.sarray[i] > 1) 
            	return false;
        
        //Now, checking if something gets eaten
        
        //cabbage and goat alone on the left
        if (state.sarray[b]==0 && state.sarray[c]==1 && state.sarray[g]==1) 
        	return false;
        
        //cabbage and goat alone on the right
        if (state.sarray[b]==1 && state.sarray[c]==0 && state.sarray[g]==0) 
        	return false;
        
        
        //goat and wolf alone on the left
        if (state.sarray[b]==0 && state.sarray[g]==1 && state.sarray[w]==1) 
        	return false;
        
        //goat and wolf alone on the right
        if (state.sarray[b]==1 && state.sarray[g]==0 && state.sarray[w]==0) 
        	return false;
        
        return true;
    }
	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) { 
		StateWolfGoatCabbage cstate = (StateWolfGoatCabbage) state;
        
        //To come up with a heuristic we solve a relaxed problem. 
        //If we remove the constraint that goat eats cabbage or wolf eats goat, 
        //we can compute how many trips are needed to take everyone across. 
        //The boat takes one item, so, the number of trips needed is 
        //the number of items on the left times 2 minus 1.
        //Why? Let the number of items on the left be n. 
        //For the first n-1 items, we need 2 trips, i.e. 2(n-1) 
        //For the last 1 item, we need only 1 trip. 
        //Total is 2(n-1)+1 = 2n-1
            
        return 2*(cstate.sarray[c]+cstate.sarray[g]+cstate.sarray[w])-1;
	}


	public static void main(String[] args) throws Exception {
		ProblemWolfGoatCabbage problem = new ProblemWolfGoatCabbage();
		problem.initialState = new StateWolfGoatCabbage(new int[] {1,1,1,1}); 
		
		Search search  = new Search(problem);
		
		System.out.println("TreeSearch------------------------");
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
		System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());
		
		System.out.println("\n\nGraphSearch----------------------");
		System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
		System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
		System.out.println("GreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
		System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());
		
		System.out.println("\n\nIterativeDeepening----------------------");
		System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
		System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
	}
	
}
