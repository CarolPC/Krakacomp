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

	}

	@Override
	public void genKra(PW pw) {

		pw.print("write(");

		exprList.genKra(pw);
		pw.println(");");
	}

}
