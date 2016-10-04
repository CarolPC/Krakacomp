package ast;

import java.util.ArrayList;
import java.util.Iterator;

public class MethodList {

		public MethodList() {
	       methodList = new ArrayList<MethodDec>();
	    }

	    public boolean addElement(MethodDec m) {
	    	 if (methodList.contains(m))
	    	        return false;

	    	      methodList.add( m );
	    	      
	    	      return true;
	    }

	    public Iterator<MethodDec> elements() {
	        return methodList.iterator();
	    }

	    public int getSize() {
	        return methodList.size();
	    }
	    
	    public MethodDec searchMethod(MethodDec m) {
	    	for(MethodDec mSearch: this.methodList) {
	    		if(m == mSearch)
	    			return mSearch;
	    	}
	    	
	    	return null;
	    }

	    private ArrayList<MethodDec> methodList;

		public void genKra(PW pw) {
			for (Iterator<MethodDec> iterator = elements(); iterator.hasNext();)
				iterator.next().genKra(pw);
		}
}
