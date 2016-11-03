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
	
	public Statement getStatements() {
		return statement;
	}

	@Override
	public void genC(PW pw) {

	}

	@Override
	public void genKra(PW pw) {
		pw.printIdent("if (");
		expr.genKra(pw);
		if (statement instanceof StatementList)
			pw.print(")");
		else {
			pw.add();
			pw.println(")");
		}

		statement.genKra(pw);
		
		if (!(statement instanceof StatementList))
			pw.sub();
	  
		pw.println();
	}

}
