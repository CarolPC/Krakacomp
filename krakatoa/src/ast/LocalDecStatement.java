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

public class LocalDecStatement extends Statement {

	private Type type;

	public LocalDecStatement(Type type) {
		localList = new HashMap<Variable, Expr>();
		this.type = type;
	}

	public void addElement(Variable v, Expr preValue) {
		localList.put(v, preValue);
	}

	public void addElement(Variable v) {
		localList.put(v, null);
	}

	public int getSize() {
		return localList.size();
	}

	private HashMap<Variable, Expr> localList;

	@Override
	public void genC(PW pw) {
		if(this.type instanceof KraClass)
			pw.printIdent(((KraClass)this.type).getCTypeName() + " ");
		else
			pw.printIdent(type.getCname() + " ");
		
		Iterator<Variable> iterator = localList.keySet().iterator();
		while (iterator.hasNext()) {
			Variable v = iterator.next();
			Expr e = localList.get(v);
		
			if(this.type instanceof KraClass)
				pw.print("*");
			pw.print(v.getCName());
			
			if (e != null) {
				pw.print(" = ");
				
				e.genC(pw, false);
			}
			
			if (iterator.hasNext())
				pw.print(", ");
		}
		
		pw.println(";");
	}

	@Override
	public void genKra(PW pw) {

		pw.printIdent(type.getName() + " ");
		
		Iterator<Variable> iterator = localList.keySet().iterator();
		while (iterator.hasNext()) {
			Variable v = iterator.next();
			Expr e = localList.get(v);
			
			if (e != null)
				e.genKra(pw);
			else 
				pw.print(v.getName());
			
			if (iterator.hasNext())
				pw.print(", ");
		}
		
		pw.println(";");
	}

}
