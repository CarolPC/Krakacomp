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

public class DoWhileStatement extends WhileStatement {

	public DoWhileStatement(Expr expr, Statement statement) {
		super(expr, statement);
	}
	
	@Override
	public void genKra(PW pw) {

		pw.printIdent("do");

		super.getStatement().genKra(pw);
				
		pw.print(" while (");
		super.getExpr().genKra(pw);

		pw.println(");");
	}

}
