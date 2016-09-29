package ast;

import java.util.*;

public class ParamList {

    public ParamList() {
       paramList = new ArrayList<Variable>();
    }

    public void addElement(Variable v) {
       paramList.add(v);
    }

    public Iterator<Variable> elements() {
        return paramList.iterator();
    }

    public int getSize() {
        return paramList.size();
    }

    private ArrayList<Variable> paramList;

	public void genKra(PW pw) {
		Variable param = null;
		int i = 0;
		while (i < getSize() - 1) {
			param = paramList.get(i++);
			pw.print(param.getType().getName() + " " + param.getName() + ", ");
		}
		param = paramList.get(i);
		pw.print(param.getType().getName() + " " + param.getName());
	}

}
