package ast;

public class IfElseStatement extends IfStatement {

	private Statement elseStatement;

	public IfElseStatement(Expr expr, Statement ifStatement, Statement elseStatement) {
		super(expr, ifStatement);
		this.elseStatement = elseStatement; 
	}
	
	@Override
	public void genKra(PW pw) {
		pw.printIdent("if (");
		getExpr().genKra(pw);
		pw.println(") {");
		
		pw.add();
		getStatement().genKra(pw);
		pw.sub();
		
		pw.printIdent("} else {");
		pw.add();
		elseStatement.genKra(pw);
		pw.sub();
		pw.printlnIdent("}");
	}

}
