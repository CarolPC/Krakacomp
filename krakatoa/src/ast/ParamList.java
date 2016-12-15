/*
Primeiro Trabalho de Laboratório de Compiladores
 
Segundo Semestre de 2016.
Campus de Sorocaba da UFSCar
Prof. José de Oliveira Guimarães
 
Grupo:
Nome: Guilherme José Carvalho Gois
Nome: Henrique Manoel de Lima Sebastião
 */

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

	public void genC(PW pw, String cname) {
		pw.print("_class" + cname + " *this");
		
		Variable param = null;
		int i = 0;

		if (getSize() == 0)
			return;

		pw.print(",");
		while (i < getSize() - 1) {
			param = paramList.get(i++);
			if(param.getType() instanceof KraClass)
				pw.print(((KraClass)param.getType()).getCTypeName() + "* " + param.getCName() + ", ");
			else
				pw.print(param.getType().getCname() + " " + param.getCName() + ", ");
		}
		param = paramList.get(i);

		if(param.getType() instanceof KraClass)
			pw.print(((KraClass)param.getType()).getCTypeName() + "* " + param.getCName());
		else
			pw.print(param.getType().getCname() + " " + param.getCName());
	}
	
	 public void genCTypeList(PW pw)
	 {
	    	int size = paramList.size();
	    	for ( Parameter p : paramList ) {
	    		
	    		if(p.getType() instanceof KraClass)
	    			pw.print(((KraClass)p.getType()).getCTypeName()+"*");
	    		
	    		else
	    			pw.print(p.getType().getCname());
	    		
	    		if ( --size > 0 )
	                pw.print(", ");
	    	}
	 }

	@Override
	public boolean equals(Object obj) {

		ParamList pList = (ParamList) obj;

		if (pList == null && pList == obj)
			return true;

		if (pList == null && pList != obj)
			return false;

		for (int i = 0; i < pList.getSize(); i++) {
			Parameter thisParameter = this.getList().get(i);
			Parameter thatParameter = pList.getList().get(i);

			if (thatParameter.getType() == Type.nullType)
				continue;

			if (!isTypeOrSupertype(thisParameter.getType(), thatParameter.getType()))
				return false;

		}

		return true;
	}

	private boolean isTypeOrSupertype(Type a, Type b) {

		if (a == b)
			return true;

		KraClass c = null;
		if (b instanceof KraClass)
			c = (KraClass) b;

		if ((a instanceof KraClass) && b == Type.nullType)
			return true;

		if (c == null)
			return false;

		while ((c = c.getSuperclass()) != null)
			if (a == c)
				return true;

		return false;
	}

}
