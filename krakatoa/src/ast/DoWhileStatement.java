package ast;

public class DoWhileStatement extends WhileStatement {

	public DoWhileStatement(Expr expr, Statement statement) {
		super(expr, statement);
	}
	
	@Override
	public void genKra(PW pw) {
		pw.printlnIdent("do {");
		
		pw.add();
		statement.genKra(pw);
		pw.sub();
		
		pw.printIdent("} while (");
		expr.genKra(pw);
		pw.println(");");
	}

}
