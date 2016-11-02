package comp;

import ast.PW;
import ast.Statement;
import ast.Variable;

public class DecrementStatement extends Statement {

	private Variable variable;

	public DecrementStatement(Variable variable) {
		this.variable = variable;
	}

	@Override
	public void genC(PW pw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void genKra(PW pw) {
		pw.printlnIdent("--" + variable.getName() + ";");
	}

}
