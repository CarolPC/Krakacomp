package ast;

public class IfStatement extends Statement {

	private Expr expr;
	private Statement statement;

	public IfStatement(Expr expr, Statement statement) {
		this.expr = expr;
		this.statement = statement;
	}

	@Override
	public void genC(PW pw) {
		// TODO Auto-generated method stub

	}

}
