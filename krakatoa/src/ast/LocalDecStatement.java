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

    public int getSize() {
        return localList.size();
    }

    private HashMap<Variable,Expr> localList;

	@Override
	public void genC(PW pw) {
		
	}

	@Override
	public void genKra(PW pw) {
		Iterator<Variable> iterator = localList.keySet().iterator();
		while (iterator.hasNext()) {
			Variable v = iterator.next();
			Expr e = localList.get(v);
			
			v.genKra(pw);
			
			if (e != null)
				e.genKra(pw);
			
			pw.println(";");
		}
	}

}
