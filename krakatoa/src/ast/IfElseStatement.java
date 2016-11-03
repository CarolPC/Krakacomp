package ast;

public class IfElseStatement extends IfStatement {

	private Statement elseStatement;

	public IfElseStatement(Expr expr, Statement ifStatement, Statement elseStatement) {
		super(expr, ifStatement);
		this.elseStatement = elseStatement; 
	}
	
	@Override
	public void genKra(PW pw) {

		super.genKra(pw);
		
		if (elseStatement instanceof StatementList)
			pw.printIdent("else");
		else {
			pw.printlnIdent("else");
			pw.add();
		}
		
		elseStatement.genKra(pw);
		
		if (!(elseStatement instanceof StatementList))
			pw.sub();
		
		pw.println();
	}

}
