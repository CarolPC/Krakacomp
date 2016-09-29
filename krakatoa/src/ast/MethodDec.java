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
	private String name;
	private Symbol qualifier;
	private ParamList paramList;
	private Type returnType;
	private StatementList stmtList;
	
	public void genKra(PW pw) {
		pw.printIdent(qualifier.name() + " " + returnType.getName() + " " + name + "(");
		paramList.genKra(pw);
		pw.println(") {");
		
		pw.add();
		stmtList.genKra(pw);
		pw.sub();
		
		pw.printlnIdent("}");
	}
	
}
