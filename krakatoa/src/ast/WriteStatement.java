package ast;

public class WriteStatement extends Statement {

	private ExprList exprList;

	public WriteStatement(ExprList exprList) {
		this.exprList = exprList;
	}
	
	public ExprList getExprList() {
		return exprList;
	}

	@Override
	public void genC(PW pw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void genKra(PW pw) {
		pw.printIdent("write ");
		exprList.genKra(pw);
		pw.println(";");
	}

}
