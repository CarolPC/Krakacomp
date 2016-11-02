package ast;

public class BreakStatement extends Statement {

	@Override
	public void genC(PW pw) {
		pw.printlnIdent("break");
	}

	@Override
	public void genKra(PW pw) {
		pw.println("break;");
	}

}
