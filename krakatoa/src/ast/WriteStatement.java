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

public class WriteStatement extends Statement {

	private ExprList exprList;

	public WriteStatement(ExprList exprList) {
		this.exprList = exprList;
	}
	
	public ExprList getExprList() {
		return exprList;
	}

	@Override
	public void genC(PW pw) {
		pw.printIdent("printf(\"");
		// TODO Printar a flag do tipo e a variável
		this.getExprList().genCPrintf(pw);
		pw.printIdent("\",");
		this.getExprList().genC(pw);
		pw.println(");");
	}

	@Override
	public void genKra(PW pw) {
		pw.printIdent("write (");
		exprList.genKra(pw);
		pw.println(");");
	}

}
