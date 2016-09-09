package ast;

import java.util.*;

public class ClassVariableList {

    public ClassVariableList() {
       classVariableList = new ArrayList<ClassVariable>();
    }

    public void addElement(ClassVariable classVariable) {
       classVariableList.add( classVariable );
    }

    public Iterator<InstanceVariable> elements() {
    	return this.classVariableList.iterator();
    }

    public int getSize() {
        return classVariableList.size();
    }

    private ArrayList<ClassVariable> classVariableList;

}
