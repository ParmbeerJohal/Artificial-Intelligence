import java.util.HashSet;
import java.util.Set;

public class ProblemNPuzzle extends Problem {
    
	boolean goal_test(Object state) {
        StateNPuzzle puzzle_state = (StateNPuzzle) state;
        
        int k=0;
        for(int i=0; i<puzzle_state.N; i++)
        	for(int j=0; j<puzzle_state.N; j++) {
        		if(puzzle_state.puzzleArray[i][j] != k)
        			return false;
        		k++;
        	}
        
        return true;
	}
  
    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
        StateNPuzzle s = (StateNPuzzle) state;
        int i0 = s.i0, j0 = s.j0;
        
        StateNPuzzle ss; //successor state
        
        //left
        ss = new StateNPuzzle(s);
        if(j0>0) {
        	ss.puzzleArray[i0][j0-1] = 0;
        	ss.puzzleArray[i0][j0]   = s.puzzleArray[i0][j0-1];
        	ss.j0--;
        	//System.out.println(ss);
        	set.add(ss);
        }

        //right
        ss = new StateNPuzzle(s);
        if(j0<s.N-1) {
        	ss.puzzleArray[i0][j0+1] = 0;
        	ss.puzzleArray[i0][j0]   = s.puzzleArray[i0][j0+1];
        	ss.j0++;
        	set.add(ss);
        }
        
        //up
        ss = new StateNPuzzle(s);
        if(i0>0) {
        	ss.puzzleArray[i0-1][j0] = 0;
        	ss.puzzleArray[i0][j0]   = s.puzzleArray[i0-1][j0];
        	ss.i0--;
        	set.add(ss);
        }

        //down
        ss = new StateNPuzzle(s);
        if(i0<s.N-1) {
        	ss.puzzleArray[i0+1][j0] = 0;
        	ss.puzzleArray[i0][j0]   = s.puzzleArray[i0+1][j0];
        	ss.i0++;
        	set.add(ss);
        }
        
        return set;
    }
	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) { 
		//number of misplaced tiles
		
        StateNPuzzle puzzle_state = (StateNPuzzle) state;
        int num = 0;
        int k=0;
        for(int i=0; i<puzzle_state.N; i++)
        	for(int j=0; j<puzzle_state.N; j++) {
        		if(puzzle_state.puzzleArray[i][j] != k)
        			num++;
        		k++;
        	}
        
        return num;
		
	}


	public static void main(String[] args) throws Exception {
		ProblemNPuzzle problem = new ProblemNPuzzle();
		int[][] puzzleArray = {{7,2,4}, {5,0,6}, {8,3,1}};
		problem.initialState = new StateNPuzzle(puzzleArray); 
		
		Search search  = new Search(problem);
			
		//TreeSearch takes too long.
		//System.out.println("TreeSearch------------------------");
		//System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		//System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		//System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		//System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
		//System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());
		
		System.out.println("\n\nGraphSearch----------------------");
		System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
		//System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
		//System.out.println("GreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
		System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());
		
		System.out.println("\n\nIterativeDeepening----------------------");
		//System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
		System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
	}
	
}
