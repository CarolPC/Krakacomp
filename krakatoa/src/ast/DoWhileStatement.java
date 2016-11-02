package ast;

public class DoWhileStatement extends WhileStatement {

	public DoWhileStatement(Expr expr, Statement statement) {
		super(expr, statement);
	}
	
	@Override
	public void genKra(PW pw) {
		pw.print("do "); 		

		super.getStatement().genKra(pw);
				
		pw.print("while(");
		super.getExpr().genKra(pw);
		pw.println(");");
	}

}
