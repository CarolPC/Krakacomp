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
		
		for(Expr e: this.exprList.getList())
		{
			if(e.getType() instanceof TypeString)
			{
				pw.printIdent("puts(");
				e.genC(pw,false);
				pw.println(");");
			}
			else
			{
				pw.printIdent("printf(\""+e.getType().getPrintfName()+" \", ");
				e.genC(pw,false);
				pw.println(");");
				
			}
		}
	}

	@Override
	public void genKra(PW pw) {
		pw.printIdent("write (");
		exprList.genKra(pw);
		pw.println(");");
	}

}
