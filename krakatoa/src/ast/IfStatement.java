package ast;

public class IfStatement extends Statement {

	private Expr expr;
	private Statement statement;

	public IfStatement(Expr expr, Statement statement) {
		this.expr = expr;
		this.statement = statement;
	}
	
	public Expr getExpr() {
		return expr;
	}
	
	public Statement getStatement() {
		return statement;
	}

	@Override
	public void genC(PW pw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void genKra(PW pw) {
		pw.printIdent("if (");
		expr.genKra(pw);
		pw.println(") {");
		
		pw.add();
		statement.genKra(pw);
		pw.sub();
		
		pw.printlnIdent("}");
	}

}
