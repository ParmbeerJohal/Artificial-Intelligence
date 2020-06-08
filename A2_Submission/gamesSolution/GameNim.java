/**
* CSC 421
* A.I. A2 Q3
* Parm Johal
* V00787710
* GameNim.java
**/

import java.io.*;
import java.util.*;

public class GameNim extends Game {
    int pile = 13;    

    int WinningScore = 10;
    int LosingScore = -10;
    int NeutralScore = 0;    
    
    public GameNim() {
    	currentState = new StateNim();
    }

    public boolean isWinState(State state)
    {
        StateNim nimState = (StateNim) state;
		if(nimState.getTotalCoins() == 1){
            	return true;
        }
        return false;
    }

	//this game will never have a tie
    public boolean isStuckState(State state){
        return false;
    }

	public Set<State> getSuccessors(State state)
    {
		if(isWinState(state) || isStuckState(state))
			return null;
		
		Set<State> successors = new HashSet<State>();
        StateNim nimState = (StateNim) state;
		
		StateNim successor_state;
		
        //Removing 1 coin
        successor_state = new StateNim(nimState);
        successor_state.subtractOne();
        successor_state.player = (state.player==0 ? 1 : 0);
        isValid(successor_state);
        successors.add(successor_state);

        //Removing 2 coins
        successor_state = new StateNim(nimState);
        successor_state.subtractTwo();
        successor_state.player = (state.player==0 ? 1 : 0);
        isValid(successor_state);
        successors.add(successor_state);

        //Removing 3 coins
        successor_state = new StateNim(nimState);
        successor_state.subtractThree();
        successor_state.player = (state.player==0 ? 1 : 0);
        isValid(successor_state);
        successors.add(successor_state);

        return successors;
    }

	//Valid only if number of coins is not negative
    public boolean isValid(State state){
        StateNim nimState = (StateNim) state;
		if(nimState.getTotalCoins() < 0) {
			return false;
		}
		return true;
	}

    public double eval(State state) 
    {   
    	if(isWinState(state)) {
    		//player who made last move
    		int previous_player = (state.player==0 ? 1 : 0);
    	
	    	if (previous_player==0) //computer wins
	            return WinningScore;
	    	else //human wins
	            return LosingScore;
    	}

        return NeutralScore;
    }
    
    
    public static void main(String[] args) throws Exception {
        
        Game game = new GameNim(); 
        Search search = new Search(game);
        int depth = 13;
        
        //needed to get human's move
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
        	
        	StateNim nextState = null;
        	
            switch ( game.currentState.player ) {
              case 1: //Human
                  
            	  //get human's move
				  
                  System.out.print("Enter your *valid* move> ");
                  int coinsToTake = Integer.parseInt( in.readLine() ); //number of coins the user will take
				  
            	  while (coinsToTake != 1 && coinsToTake != 2 && coinsToTake != 3) {
					  System.out.println("You must take 1, 2, or 3 coins. Try again.");
					  System.out.print("Enter your *valid* move> ");
					  coinsToTake = Integer.parseInt( in.readLine() );
				  }
					
                  nextState = new StateNim((StateNim)game.currentState);
                  nextState.player = 1;
				  
				  if (coinsToTake == 1) nextState.subtractOne();
				  if (coinsToTake == 2) nextState.subtractTwo();
				  if (coinsToTake == 3) nextState.subtractThree();
                  System.out.println("Human: \n" + nextState);
                  break;
                     
              case 0: //Computer
            	  
            	  nextState = (StateNim)search.bestSuccessorState(depth);
            	  nextState.player = 0;
            	  System.out.println("Computer: \n" + nextState);
                  break;
            }
                        
            game.currentState = nextState;
            //change player
            game.currentState.player = (game.currentState.player==0 ? 1 : 0);
            
            //Who wins?
            if ( game.isWinState(game.currentState) ) {
            
            	if (game.currentState.player == 1) //i.e. last move was by the computer
            		System.out.println("Computer wins!");
            	else
            		System.out.println("You win!");
            	
            	break;
            }
            
            if ( game.isStuckState(game.currentState) ) { 
            	System.out.println("Cat's game!");
            	break;
            }
        }
    }
}