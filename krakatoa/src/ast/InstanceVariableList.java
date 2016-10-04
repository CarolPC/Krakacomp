package ast;

import java.util.*;

public class InstanceVariableList {

    public InstanceVariableList() {
       instanceVariableList = new ArrayList<InstanceVariable>();
    }

    public boolean addElement(InstanceVariable instanceVariable) {
      if (instanceVariableList.contains(instanceVariable))
        return false;

      instanceVariableList.add( instanceVariable );
      
      return true;
    }
    
    public Variable searchVariable(Variable v)
    {
 	  	for(Variable vSearch: this.instanceVariableList) {
 	   		if(v == vSearch)
 	  			return vSearch;
 	   	}
 	  	
 	    return null;

    }

    public Iterator<InstanceVariable> elements() {
    	return this.instanceVariableList.iterator();
    }

    public int getSize() {
        return instanceVariableList.size();
    }

    private ArrayList<InstanceVariable> instanceVariableList;

	public void genKra(PW pw) {
		for (Iterator<InstanceVariable> iterator = elements(); iterator.hasNext(); ) {
			iterator.next().genKra(pw);
		}
	}

}
