package ast;

public class AssignStatement extends Statement {
	
	private Expr left;
	private Expr right;

	public AssignStatement(Expr left, Expr right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void genC(PW pw) {
		// TODO Auto-generated method stub

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
