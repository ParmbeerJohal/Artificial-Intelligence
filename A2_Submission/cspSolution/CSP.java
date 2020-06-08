import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public abstract class CSP {
	//variable-variable
	Map<Object,Set<Object>> C = new TreeMap<Object,Set<Object>>();
	//variable-domain
	Map<Object,Set<Object>> D = new TreeMap<Object,Set<Object>>();
		
	public void addBidirectionalArc(Object X, Object Y) {
		addArc(X,Y);
		addArc(Y,X);
	}
	
	public void addArc(Object X, Object Y) {
		if(!C.containsKey(X))
			C.put(X, new TreeSet<Object>());
		C.get(X).add(Y);
	}
	
	public void addDomain(Object X, Object[] values) {
		Set<Object> s = new TreeSet<Object>();
		for(Object v : values)
			s.add(v);
		D.put(X, s);
	}
	
	public abstract boolean isGood(Object X, Object Y, Object x, Object y);
}
