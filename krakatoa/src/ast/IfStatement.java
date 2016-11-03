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

public class IfStatement extends Statement {

	private Expr expr;
	private Statement statement;

	public IfStatement(Expr expr, Statement statement) {
		this.expr = expr;
		this.statement = statement;
	}
	
	public Expr getExpr() {
		return expr;
	}
	
	public Statement getStatements() {
		return statement;
	}

	@Override
	public void genC(PW pw) {

	}

	@Override
	public void genKra(PW pw) {
		pw.printIdent("if (");
		expr.genKra(pw);
		if (statement instanceof StatementList)
			pw.print(")");
		else {
			pw.add();
			pw.println(")");
		}

		statement.genKra(pw);
		
		if (!(statement instanceof StatementList))
			pw.sub();
	  
		pw.println();
	}

}
