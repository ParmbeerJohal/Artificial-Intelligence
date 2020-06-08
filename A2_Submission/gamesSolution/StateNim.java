/**
* CSC 421
* A.I. A2 Q3
* Parm Johal
* V00787710
* StateNim.java
**/

public class StateNim extends State {
	
	private int totalCoins;

    public StateNim() {
		this.totalCoins = 13;
	}

    public StateNim(StateNim state) {
        this.totalCoins = state.totalCoins;
        player = state.player;
    }
	
	public int getTotalCoins() {
		return this.totalCoins;
	}
	
	public void subtractOne() {
		this.totalCoins --;
	}
	
	public void subtractTwo() {
		this.totalCoins -= 2;
	}
	
	public void subtractThree() {
		this.totalCoins -= 3;
	}

    public String toString() {
		String ret = "";
		ret += getTotalCoins();
        return ret;
    }
}