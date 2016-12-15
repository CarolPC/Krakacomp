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

public class ReadStatement extends Statement {
	
	private ExprList exprList;

	public ReadStatement(ExprList exprList) {
		this.exprList = exprList;
	}
	
	public ExprList getExprList() {
		return exprList;
	}

	@Override
	public void genC(PW pw) {
		for(Expr e: this.exprList.getList())
		{
			pw.printlnIdent("{");
			pw.add();
			pw.printlnIdent("char __s[512];");
			pw.printlnIdent("gets(__s);");
						
			
			if(e.getType() instanceof TypeString)
			{
				e.genC(pw,false);
				pw.println("= malloc(strlen(__s)+1);");
				pw.printIdent("strcpy(");
				e.genC(pw,false);
				pw.println(", __s);");
			}
			else
			{
				pw.printIdent("sscanf(__s,\""+e.getType().getPrintfName()+"\",&");
				e.genC(pw,false);
				pw.println(");");
			}
			pw.sub();
			pw.printlnIdent("}");
		}
	}

	@Override
	public void genKra(PW pw) {
		pw.printIdent("read (");

		exprList.genKra(pw);

		pw.println(");");
	}

}
