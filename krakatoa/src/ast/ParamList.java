package ast;

import java.util.*;

public class ParamList {

    public ParamList() {
       paramList = new ArrayList<Parameter>();
    }

    public void addElement(Parameter v) {
       paramList.add(v);
    }

    public Iterator<Parameter> elements() {
        return paramList.iterator();
    }

    public ArrayList<Parameter> getList() {
    	return this.paramList;
    }
    public int getSize() {
        return paramList.size();
    }
    
    

    private ArrayList<Parameter> paramList;

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
	
	@Override
    public boolean equals(Object obj) {
		if (obj == null) {
        	throw new RuntimeException("parameter is not Variable Type");
        }
		
        if (!ParamList.class.isAssignableFrom(obj.getClass())) {
            throw new RuntimeException("parameter is not Parameter Type");
        }
        
        ParamList pList = (ParamList)obj;
        
        for(int i = 0; i < pList.getSize(); i++)
        {
        	if(this.getList().get(i).getType() != pList.getList().get(i).getType())
        		return false;
        }
        
        return true;
	}

}
