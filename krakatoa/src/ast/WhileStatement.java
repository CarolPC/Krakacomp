package ast;

public class WhileStatement extends Statement {

	private Expr expr;
	private Statement statement;

	public WhileStatement(Expr expr, Statement statement) {
		this.expr = expr;
		this.statement = statement;
	}

	@Override
	public void genC(PW pw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void genKra(PW pw) {
		pw.printIdent("while (");
		expr.genKra(pw);
		pw.println(") {");
		
		pw.add();
		statement.genKra(pw);
		pw.sub();
		
		pw.printlnIdent("}");
	}

}
