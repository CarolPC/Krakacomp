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

public class AssignStatement extends Statement {
	
	private Expr left;
	private Expr right;

	public AssignStatement(Expr left, Expr right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void genC(PW pw) {
		pw.printIdent("");
		
		if(this.left.getType() instanceof TypeString)
		{
			pw.print("strcpy(");
			left.genC(pw,false);
			pw.print(",");
			right.genC(pw,false);
			pw.print(")");
		}
		else
		{
			left.genC(pw, false);
			pw.print(" = ");
			
			if(left.getType() != right.getType())
			{
				KraClass c = (KraClass)left.getType();
				pw.print("("+c.getCTypeName()+" *) ");
			}
			
			right.genC(pw, true);
		}
		pw.println(";");
	}	

	@Override
	public void genKra(PW pw) {
		pw.printIdent("");
		left.genKra(pw);
		pw.print(" = ");
		right.genKra(pw);
		pw.println(";");
	}

}
