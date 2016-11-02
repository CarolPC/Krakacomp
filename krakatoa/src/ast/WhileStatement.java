package ast;

public class WhileStatement extends Statement {

	protected Expr expr;
	protected Statement statement;

	public WhileStatement(Expr expr, Statement statement) {
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

	}

	@Override
	public void genKra(PW pw) {
		pw.printIdent("while (");
		expr.genKra(pw);
		pw.println(") {");
		
		pw.add();
		statement.genKra(pw);
		pw.sub();
		
		pw.printlnIdent("}");
	}

}
