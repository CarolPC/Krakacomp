package ast;

public class IfStatement extends Statement {

	private Expr expr;
	private Statement statement;

	public IfStatement(Expr expr, Statement statement) {
		this.expr = expr;
		this.statement = statement;
	}
	
	public Expr getExpr() {
		return expr;
	}
	
	public Statement getStatements() {
		return statement;
	}

	@Override
	public void genC(PW pw) {

	}

	@Override
	public void genKra(PW pw) {
		pw.print("if (");
		expr.genKra(pw);
		pw.print(") ");

		pw.add();
		
		if(!(statement instanceof StatementList))
		{
			pw.println("");
			pw.printIdent("")
		}
	   		
		statement.genKra(pw);
		
		pw.sub();
	  
	}

}
