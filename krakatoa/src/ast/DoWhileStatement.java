package ast;

public class DoWhileStatement extends WhileStatement {

	public DoWhileStatement(Expr expr, Statement statement) {
		super(expr, statement);
	}
	
	@Override
	public void genKra(PW pw) {
		super.genKra(pw);
	}

}
