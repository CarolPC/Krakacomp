package ast;

public class ReadStatement extends Statement {
	
	private ExprList exprList;

	public ReadStatement(ExprList exprList) {
		this.exprList = exprList;
	}
	
	public ExprList getExprList() {
		return exprList;
	}

	@Override
	public void genC(PW pw) {

	}

	@Override
	public void genKra(PW pw) {
		
	}

}
