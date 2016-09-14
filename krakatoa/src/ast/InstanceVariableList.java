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

    public Iterator<InstanceVariable> elements() {
    	return this.instanceVariableList.iterator();
    }

    public int getSize() {
        return instanceVariableList.size();
    }

    private ArrayList<InstanceVariable> instanceVariableList;

}
