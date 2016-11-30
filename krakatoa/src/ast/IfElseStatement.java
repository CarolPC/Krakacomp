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

public class IfElseStatement extends IfStatement {

	private Statement elseStatement;

	public IfElseStatement(Expr expr, Statement ifStatement, Statement elseStatement) {
		super(expr, ifStatement);
		this.elseStatement = elseStatement; 
	}
	
	@Override
	public void genKra(PW pw) {

		super.genKra(pw);
		
		if (elseStatement instanceof StatementList)
			pw.printIdent("else");
		else {
			pw.printlnIdent("else");
			pw.add();
		}
		
		elseStatement.genKra(pw);
		
		if (!(elseStatement instanceof StatementList))
			pw.sub();
		
		pw.println();
	}
	
	@Override
	public void genC(PW pw) {

		super.genC(pw);
		
		if (elseStatement instanceof StatementList)
			pw.printIdent("else");
		else {
			pw.printlnIdent("else");
			pw.add();
		}
		
		elseStatement.genC(pw);
		
		if (!(elseStatement instanceof StatementList))
			pw.sub();
		
		pw.println();
	}

}
