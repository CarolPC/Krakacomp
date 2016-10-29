package ast;

import java.util.*;

public class LocalDecStatement extends Statement {

    public LocalDecStatement() {
       localList = new HashMap<Variable,Expr>();
    }

    public void addElement(Variable v,Expr preValue) {
       localList.put(v, preValue);
    }
    
    public void addElement(Variable v) {
        localList.put(v, null);
     }
/*
    public Iterator<Variable> elements() {
        return localList.iterator();
    }*/

    public int getSize() {
        return localList.size();
    }

    private HashMap<Variable,Expr> localList;

	@Override
	public void genC(PW pw) {
		
	}

	@Override
	public void genKra(PW pw) {
		
	}

}
