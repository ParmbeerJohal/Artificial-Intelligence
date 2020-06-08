/**
* CSC 421
* A.I. A2 Q4
* Parm Johal
* V00787710
* CSPZebra.java
**/

import java.util.*;
import java.math.*;

public class CSPZebra extends CSP {
	static Set<Object> varCol = new HashSet<Object>(Arrays.asList(new String[]{"blue","green","ivory","red","yellow"}));
	static Set<Object> varDri = new HashSet<Object>(Arrays.asList(new String[]{"coffee","milk","orange-juice","tea","water"}));
	static Set<Object> varNat = new HashSet<Object>(Arrays.asList(new String[]{"englishman","japanese","norwegian","spaniard","ukrainian"}));
	static Set<Object> varPet = new HashSet<Object>(Arrays.asList(new String[]{"dog","fox","horse","snails","zebra"}));
	static Set<Object> varCig = new HashSet<Object>(Arrays.asList(new String[]{"chesterfield","kools","lucky-strike","old-gold","parliament"}));
	
	public boolean isGood(Object X, Object Y, Object x, Object y) {
		if(!C.containsKey(X)) {
			return true;
		}
		
		if(!C.get(X).contains(Y)) {
			return true;
		}
		
		//rule 1
		if(X.equals("englishman") && Y.equals("red") && !x.equals(y)) {
			return false;
		}
		
		//rule 2
		if(X.equals("spaniard") && Y.equals("dog") && !x.equals(y)) {
			return false;
		}
		
		//rule 3
		if(X.equals("coffee") && Y.equals("green") && !x.equals(y)) {
			return false;
		}
		
		//rule 4
		if(X.equals("ukrainian") && Y.equals("tea") && !x.equals(y)) {
			return false;
		}
		
		//rule 5
		if(X.equals("green") && Y.equals("ivory") && (Integer)x - (Integer)y != 1) {
			return false;
		}
		
		//rule 6
		if(X.equals("old-gold") && Y.equals("snails") && !x.equals(y)) {
			return false;
		}
		
		//rule 7
		if(X.equals("kools") && Y.equals("yellow") && !x.equals(y)) {
			return false;
		}
		
		//rule 8 == unary constraint
		if(X.equals("milk") && (Integer)x != 3) {
			return false;
		}
		
		//rule 9 == unary constraint
		if(X.equals("norwegian") && (Integer)x != 1) {
			return false;
		}
		
		//rule 10
		if(X.equals("chesterfield") && Y.equals("fox") && Math.abs((Integer)x - (Integer)y) != 1) {
			return false;
		}
		
		//rule 11
		if(X.equals("kools") && Y.equals("horse") && Math.abs((Integer)x - (Integer)y) != 1) {
			return false;
		}
		
		//rule 12
		if(X.equals("lucky-strike") && Y.equals("orange-juice") && !x.equals(y)) {
			return false;
		}
		
		//rule 13
		if(X.equals("japanese") && Y.equals("parliament") && !x.equals(y)) {
			return false;
		}
		
		//rule 14
		if(X.equals("norwegian") && Y.equals("blue") && Math.abs((Integer)x - (Integer)y) != 1) {
			return false;
		}
		
		
		
		if(varCol.contains(X) && varCol.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		if(varDri.contains(X) && varDri.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		if(varNat.contains(X) && varNat.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		if(varPet.contains(X) && varPet.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		if(varCig.contains(X) && varCig.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		return true;
		
	}
	
	public static void main(String[] args) {
		
		CSPZebra csp = new CSPZebra();
		
		//possible domain values
		Integer[] dom = {1,2,3,4,5};
		
		Integer[] newDomDri_milk = {3};
		
		Integer[] newDomNat_Norweg = {1};
		
		//adding domains
		//also adding unary constraints where applicable
		for(Object X: varCol)
			csp.addDomain(X, dom);
		
		//unary constraint

		for(Object X: varDri) {
			if(X.equals("milk")) {
				csp.addDomain(X, newDomDri_milk);
				continue;
			}
			csp.addDomain(X, dom);
		}
		
		for(Object X: varPet)
			csp.addDomain(X, dom);
		
		//unary constraint
		for(Object X: varNat) {
			if(X.equals("norwegian")) {
				csp.addDomain(X, newDomNat_Norweg);
				continue;
			}
			csp.addDomain(X, dom);
		}
		
		for(Object X: varCig)
			csp.addDomain(X, dom);
		
		csp.addBidirectionalArc("englishman","red");
		csp.addBidirectionalArc("spaniard","dog");
		csp.addBidirectionalArc("coffee","green");
		csp.addBidirectionalArc("ukrainian","tea");
		csp.addBidirectionalArc("green","ivory");
		csp.addBidirectionalArc("old-gold","snails");
		csp.addBidirectionalArc("kools","yellow");
		csp.addBidirectionalArc("chesterfield","fox");
		csp.addBidirectionalArc("lucky-strike","orange-juice");
		csp.addBidirectionalArc("japanese","parliament");
		csp.addBidirectionalArc("norwegian","blue");
		csp.addBidirectionalArc("kools","horse");
		
		for(Object X : varCol)
			for(Object Y : varCol)
				csp.addBidirectionalArc(X,Y);
			
		for(Object X : varDri)
			for(Object Y : varDri)
				csp.addBidirectionalArc(X,Y);
			
		for(Object X : varPet)
			for(Object Y : varPet)
				csp.addBidirectionalArc(X,Y);
			
		for(Object X : varNat)
			for(Object Y : varNat)
				csp.addBidirectionalArc(X,Y);
			
		for(Object X : varCig)
			for(Object Y : varCig)
				csp.addBidirectionalArc(X,Y);
			
		Search search = new Search(csp);
		System.out.println(search.BacktrackingSearch());
	}
}