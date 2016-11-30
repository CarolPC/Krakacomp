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

public class LiteralString extends Expr {
    
    public LiteralString( String literalString ) { 
        this.literalString = literalString;
    }
    
    public void genC( PW pw, boolean putParenthesis ) {
    	if (putParenthesis)
    		pw.print("(");
    	
    	pw.print(literalString);
    	
    	if (putParenthesis)
    		pw.print(")");
    }
    
    public Type getType() {
        return Type.stringType;
    }
    
    private String literalString;

	@Override
	public void genKra(PW pw) {
		pw.print("\"" + literalString + "\"");
	}
}
