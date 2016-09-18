package ast;

public class NewExpr extends Expr{

	private KraClass classType;
	
	public NewExpr(KraClass classType)
	{
		this.classType = classType;
	}
	
	public Type getType()
	{
		return this.classType;
	}
	
	public void genC( PW pw, boolean putParenthesis )
	{
		//Segunda fase
	}
}
