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
		for(Expr e : exprList.getList())
		{
			this.paramList.addElement(new Parameter("", e.getType()));
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
	
	public void setStatementList(StatementList s) {
		this.stmtList = s;
	}
	public Type getType() {
		return this.returnType;
	}
	public void genKra(PW pw) {
		pw.printIdent(qualifier + " " + returnType.getName() + " " + name + "(");
		paramList.genKra(pw);
		pw.println(") {");
		
		pw.add();
		stmtList.genKra(pw);
		pw.sub();
		
		pw.printlnIdent("}");
	}
	
	@Override
    public boolean equals(Object obj) {
		
        if (obj == null) {
        	throw new RuntimeException("parameter is not MethodDec Type");
        }
        if (!MethodDec.class.isAssignableFrom(obj.getClass())) {
            throw new RuntimeException("parameter is not MethodDec Type");
        }
        
        MethodDec m = (MethodDec)obj;;
        if(!m.name.equals(this.name))
        	return false;
        
        if(!m.paramList.equals(this.paramList))
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
