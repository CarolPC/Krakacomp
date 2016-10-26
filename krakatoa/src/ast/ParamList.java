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
		
		if (getSize() == 0)
			return;
		
		while (i < getSize() - 1) {
			param = paramList.get(i++);
			pw.print(param.getType().getName() + " " + param.getName() + ", ");
		}
		param = paramList.get(i);
		pw.print(param.getType().getName() + " " + param.getName());
	}
	
	@Override
    public boolean equals(Object obj) {
        
        ParamList pList = (ParamList)obj;
        
        if(pList == null && pList == obj)
        	return true;
        
        if(pList == null && pList != obj)
        	return false;
        
        for(int i = 0; i < pList.getSize(); i++)
        {
        	Parameter thisParameter = this.getList().get(i);
        	Parameter thatParameter = pList.getList().get(i);
        	try {
        	if(thisParameter.getType() == null || thatParameter.getType() == null || 
        			!thisParameter.getType().getName().equals(thatParameter.getType().getName()))
        		return false;
        	} catch (NullPointerException npe) {
        		return true;
        	}
        }
        
        return true;
	}

}
