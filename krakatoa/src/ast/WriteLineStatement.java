package ast;

public class WriteLineStatement extends WriteStatement {

	public WriteLineStatement(ExprList exprList) {
		super(exprList);
	}
	
	@Override
	public void genKra(PW pw) {

		pw.print("write(");
		getExprList().genKra(pw);
		pw.println(");");
	}

}
