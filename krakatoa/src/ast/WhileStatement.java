package ast;

public class WhileStatement extends Statement {

	private Expr expr;
	private Statement statement;

	public WhileStatement(Expr expr, Statement statement) {
		this.expr = expr;
		this.statement = statement;
	}

	@Override
	public void genC(PW pw) {
		// TODO Auto-generated method stub

	}

}
