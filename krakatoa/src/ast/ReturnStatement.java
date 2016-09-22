package ast;

public class ReturnStatement extends Statement {

	private Expr expr;

	public ReturnStatement(Expr expr) {
		this.expr = expr;
	}

	@Override
	public void genC(PW pw) {
		// TODO Auto-generated method stub

	}

}
