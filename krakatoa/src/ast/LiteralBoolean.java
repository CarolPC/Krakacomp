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

public class LiteralBoolean extends Expr {

    public LiteralBoolean( boolean value ) {
        this.value = value;
    }

    @Override
	public void genC( PW pw, boolean putParenthesis ) {
       pw.print( value ? "true" : "false" );
    }

    @Override
	public Type getType() {
        return Type.booleanType;
    }

    public static LiteralBoolean True  = new LiteralBoolean(true);
    public static LiteralBoolean False = new LiteralBoolean(false);

    private boolean value;

	@Override
	public void genKra(PW pw) {
		pw.print(value ? "true" : "false");
	}
}
