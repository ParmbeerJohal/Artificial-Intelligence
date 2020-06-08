public class StateNPuzzle 
{    
	int N;
    int[][] puzzleArray;
    int i0, j0; //position of zero
    
    public StateNPuzzle(int[][] puzzleArray) { 
    	this.puzzleArray = puzzleArray; 
    	N = puzzleArray.length;
    	
    	//Find the position of 0
    	mainLoop:
        for(int i=0; i<N; i++)
        	for(int j=0; j<N; j++)
        		if(puzzleArray[i][j] == 0) {
        			i0 = i; j0 = j;
        			break mainLoop;
        		}
    }
    
    //It has to be a copy of values not reference because we will 
    //create many states and don't want to overwrite the same array.
    public StateNPuzzle(StateNPuzzle state) {
    	N = state.N;
    	puzzleArray = new int[N][N];
    	
        for(int i=0; i<N; i++)
        	for(int j=0; j<N; j++)
        		puzzleArray[i][j] = state.puzzleArray[i][j];
        
        i0 = state.i0; j0 = state.j0;
    }
    
    public boolean equals(Object o) {
        return java.util.Arrays.deepEquals( puzzleArray, ((StateNPuzzle) o).puzzleArray );
    }
    
    public int hashCode() {
        return java.util.Arrays.deepHashCode( puzzleArray );
    }    
    
    public String toString() {
    	return java.util.Arrays.deepToString( puzzleArray );
    }
}