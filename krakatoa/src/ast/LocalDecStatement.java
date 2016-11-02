package ast;

import java.util.*;

public class LocalDecStatement extends Statement {

	private Type type;
	
    public LocalDecStatement() {
       localList = new HashMap<Variable,Expr>();
    }

    public void addElement(Variable v,Expr preValue) {
    	this.type = v.getType();
    	localList.put(v, preValue);
    }
    
    public void addElement(Variable v) {
        localList.put(v, null);
     }

    public int getSize() {
        return localList.size();
    }

    private HashMap<Variable,Expr> localList;

	@Override
	public void genC(PW pw) {
		
	}

	@Override
	public void genKra(PW pw) {

		int size = localList.values().size();
		
		pw.print(this.type.getName()+" ");
		
		for(Expr e: localList.values())
		{
			e.genKra(pw);
			size--;
			if(size > 0)
				pw.print(",");
		}
		pw.println(";");
	}

}
