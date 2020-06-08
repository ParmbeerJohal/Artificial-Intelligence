public class StateJugsPuzzle 
{
    public static int capacityArray[] = {12, 8, 3};
    
    public int jugArray[] = {0,0,0};
    
    public StateJugsPuzzle() {}
    
    public StateJugsPuzzle(StateJugsPuzzle state) {
        for(int i=0; i<3; i++) 
            this.jugArray[i] = state.jugArray[i];
    }
    
    public boolean equals(Object o)
    {
    	StateJugsPuzzle state = (StateJugsPuzzle) o;
        
        for (int i=0; i<3; i++)
            if (this.jugArray[i] != state.jugArray[i])
                return false;
        
        return true;
    }
    
    public int hashCode() {
        return jugArray[0]*100 + jugArray[1]*10 + jugArray[2];
    }    
    
    public String toString()
    {
        String ret = "";
        for (int i=0; i<jugArray.length; i++)
            ret += " " + jugArray[i];
        return ret;
    }
}