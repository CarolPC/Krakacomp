package ast;

public class IfElseStatement extends IfStatement {

	private Statement elseStatement;

	public IfElseStatement(Expr expr, Statement ifStatement, Statement elseStatement) {
		super(expr, ifStatement);
		this.elseStatement = elseStatement; 
	}

}
