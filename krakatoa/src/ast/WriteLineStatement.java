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

public class WriteLineStatement extends WriteStatement {

	public WriteLineStatement(ExprList exprList) {
		super(exprList);
	}
	
	@Override
	public void genKra(PW pw) {
		pw.printIdent("writeln (");
		getExprList().genKra(pw);
		pw.println(");");
	}
	
	@Override
	public void genC(PW pw)
	{
		for(Expr e: super.getExprList().getList())
		{
			if(e.getType() instanceof TypeString)
			{
				pw.printIdent("puts(");
				e.genC(pw,false);
				pw.println(");");
			}
			else
			{
				pw.printIdent("printf(\""+e.getType().getPrintfName()+"\\n\", ");
				e.genC(pw,false);
				pw.println(");");
				
			}
		}
	}

}
