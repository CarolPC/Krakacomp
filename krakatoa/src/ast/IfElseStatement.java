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
		
		pw.print("else ");
		pw.add();
		
		if(!(elseStatement instanceof StatementList))
		{
			pw.println("");
			pw.printIdent("");
		}
		
		elseStatement.genKra(pw);
		
		pw.sub();
		
	}

}
