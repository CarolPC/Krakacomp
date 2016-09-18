package ast;

import java.util.ArrayList;
import java.util.Iterator;

public class MethodList {

		public MethodList() {
	       methodList = new ArrayList<MethodDec>();
	    }

	    public void addElement(MethodDec v) {
	       methodList.add(v);
	    }

	    public Iterator<MethodDec> elements() {
	        return methodList.iterator();
	    }

	    public int getSize() {
	        return methodList.size();
	    }

	    private ArrayList<MethodDec> methodList;
}
