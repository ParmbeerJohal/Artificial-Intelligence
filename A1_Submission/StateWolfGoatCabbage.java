public class StateWolfGoatCabbage 
{    
    int sarray[];
    int N = 4;
    
    public StateWolfGoatCabbage(int[] sarray) { this.sarray = sarray; }
    
    //It has to be a copy of values not reference because we will 
    //create many states and don't want to overwrite the same array.
    public StateWolfGoatCabbage(StateWolfGoatCabbage state) {
    	sarray = new int[N];
        for(int i=0; i<N; i++) 
            this.sarray[i] = state.sarray[i];
    }
    
    public boolean equals(Object o)
    {
        StateWolfGoatCabbage state = (StateWolfGoatCabbage) o;
        
        for (int i=0; i<N; i++)
            if (this.sarray[i] != state.sarray[i])
                return false;
        
        return true;
    }

    public int hashCode() {
    	int result = 0;
    	for (int i=0; i<N; i++) {
    		result += sarray[i] * Math.pow(10, i);
    	}
    	
        return result;
    } 
    
    public String toString()
    {
        String ret = "";
        for (int i=0; i<N; i++)
            ret += " " + this.sarray[i];
        return ret;
    }
}