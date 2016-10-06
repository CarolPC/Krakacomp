package ast;

public class AssignStatement extends Statement {
	
	private AssignExpr assignExpr;

	public AssignStatement(AssignExpr assignExpr) {
		this.assignExpr = assignExpr;
	}

	@Override
	public void genC(PW pw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void genKra(PW pw) {
		assignExpr.genKra(pw);
	}

}
