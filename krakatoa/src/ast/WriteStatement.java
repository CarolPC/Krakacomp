package ast;

public class WriteStatement extends Statement {

	private ExprList exprList;

	public WriteStatement(ExprList exprList) {
		this.exprList = exprList;
	}

	@Override
	public void genC(PW pw) {
		// TODO Auto-generated method stub

	}

}
