/*
Primeiro Trabalho de Laboratório de Compiladores
 
Segundo Semestre de 2016.
Campus de Sorocaba da UFSCar
Prof. José de Oliveira Guimarães
 
Grupo:
Nome: Guilherme José Carvalho Gois
Nome: Henrique Manoel de Lima Sebastião
 */


package ast;

public class ReturnStatement extends Statement {

	private Expr expr;

	public ReturnStatement(Expr expr) {
		this.expr = expr;
	}

	@Override
	public void genC(PW pw) {
		pw.printIdent("return ");
		expr.genC(pw, true);
		pw.println(";");
	}

	@Override
	public void genKra(PW pw) {
		pw.printIdent("return ");
		expr.genKra(pw);
		pw.println(";");
	}

}
