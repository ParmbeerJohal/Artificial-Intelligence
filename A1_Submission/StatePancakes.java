public class StatePancakes 
{   
	int N = 10; //number of pancakes; can be changed to some other number
	
    public int pancakesArray[];
    
    public StatePancakes(int pancakesArray[]) {
    	this.pancakesArray = pancakesArray;
    	this.N = pancakesArray.length;
    }
    
    public StatePancakes(StatePancakes state) {
    	this.N = state.pancakesArray.length;
    	this.pancakesArray = new int[N];
    	
        for(int i=0; i<N; i++) 
            this.pancakesArray[i] = state.pancakesArray[i];
    }
    
    public boolean equals(Object o)
    {
    	StatePancakes state = (StatePancakes) o;
        
        for (int i=0; i<N; i++)
            if (this.pancakesArray[i] != state.pancakesArray[i])
                return false;
        
        return true;
    }
    
    public int hashCode() {
    	int result = 0;
    	for (int i=0; i<N; i++) {
    		result += pancakesArray[i] * Math.pow(10, i);
    	}
    	
        return result;
    }    
    
    public String toString()
    {
        String ret = "";
        for (int i=0; i<N; i++)
            ret += " " + pancakesArray[i];
        return ret;
    }
}