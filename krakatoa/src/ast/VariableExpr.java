package ast;

public class VariableExpr extends MessageSend {
    
    public VariableExpr( Variable v ) {
        this.v = v;
    }
    
    public void genC( PW pw, boolean putParenthesis ) {
        pw.print( v.getName() );
    }
    
    public Type getType() {
        return v.getType();
    }
    
    private Variable v;

	@Override
	public void genKra(PW pw) {
		v.genKra(pw);
	}
}