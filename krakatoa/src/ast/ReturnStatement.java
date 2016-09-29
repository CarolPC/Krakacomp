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

	@Override
	public void genKra(PW pw) {
		pw.printIdent("return ");
		expr.genKra(pw);
		pw.println(";");
	}

}
