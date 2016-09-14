package ast;

import java.util.*;

public class ClassVariableList {

    public ClassVariableList() {
       classVariableList = new ArrayList<ClassVariable>();
    }

    public boolean addElement(ClassVariable classVariable) {
      if (classVariableList.contains(classVariable))
        return false;

      classVariableList.add( classVariable );

      return true;
    }

    public Iterator<InstanceVariable> elements() {
    	return this.classVariableList.iterator();
    }

    public int getSize() {
        return classVariableList.size();
    }

    private ArrayList<ClassVariable> classVariableList;

}
