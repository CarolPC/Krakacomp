package ast;

public class AssignExpr extends Expr {
	
	private Expr left;
	private Expr right;

	public AssignExpr(Expr left, Expr right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void genC(PW pw, boolean putParenthesis) {
		// TODO Auto-generated method stub

	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return left.getType();
	}

	@Override
	public void genKra(PW pw) {
		pw.printIdent("");
		left.genKra(pw);
		pw.print(" = ");
		right.genKra(pw);
		pw.println(";");
	}

}
