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

import lexer.Symbol;

public class MethodDec {

	public MethodDec(Symbol qualifier,Type returnType,
			     String name, ParamList paramList, StatementList stmtList)
	{
		this.qualifier = qualifier;
		this.name = name;
		this.paramList = paramList;
		this.returnType = returnType;
		this.stmtList = stmtList;
	}
	public MethodDec(String name, ExprList exprList)
	{
		this.name = name;
		this.paramList = new ParamList();
		
		if(exprList != null)
			for(Expr e : exprList.getList())
			{
				Type t = e.getType();
				if(t == null)
					t = Type.voidType;
				
				this.paramList.addElement(new Parameter("", t));
			}
	}
	public MethodDec(Symbol qualifier,Type returnType,
		     String name, ParamList paramList)
	{
		this.qualifier = qualifier;
		this.name = name;
		this.paramList = paramList;
		this.returnType = returnType;
	}
	private String name;
	private Symbol qualifier;
	private ParamList paramList;
	private Type returnType;
	private StatementList stmtList;
	
	public String getName() {
		return this.name;
	}
	
	public ParamList getParamList()
	{
		return this.paramList;
	}
	
	public void setStatementList(StatementList s) {
		this.stmtList = s;
	}
	public Type getType() {
		return this.returnType;
	}
	public void genKra(PW pw) {
		pw.printIdent(qualifier + " " + returnType.getName() + " " + name + "(");
		paramList.genKra(pw);
		pw.print(")");
		
		stmtList.genKra(pw);
		
		pw.println("");
	}
	
	@Override
    public boolean equals(Object obj) {
		
        MethodDec m = (MethodDec)obj;;
        if(!m.getName().equals(this.getName()))
        	return false;
        
        if(this.paramList == null)
        	return m.getParamList() == null;
        
        
        if(!m.getParamList().equals(this.paramList))
        	return false;
        
        return true;
    }
	
	/*public boolean equals(MethodDec methodDec) {
		if (this.name.equals(methodDec.name)
				|| this.paramList.equals(methodDec.paramList))
			return false;
		return true;
	}*/
	
}
