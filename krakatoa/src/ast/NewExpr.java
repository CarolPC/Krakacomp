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
		pw.print(classType.getCNew() + "()");
	}

	@Override
	public void genKra(PW pw) {
		pw.print("new " + classType.getName() + "()");
	}
}
