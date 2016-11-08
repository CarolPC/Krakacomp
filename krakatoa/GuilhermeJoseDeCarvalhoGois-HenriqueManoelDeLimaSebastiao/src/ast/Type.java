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

abstract public class Type {

    public Type( String name ) {
        this.name = name;
    }

    public static Type booleanType = new TypeBoolean();
    public static Type intType = new TypeInt();
    public static Type stringType = new TypeString();
    public static Type voidType = new TypeVoid();
    public static Type undefinedType = new TypeUndefined();
    public static Type nullType = new TypeNull();
    public String getName() {
        return name;
    }

    abstract public String getCname();
    

    private String name;
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == null)
    		return false;
    	
    	if (obj instanceof Type) {
    		return name.equals(((Type) obj).getName());
    	}
    	
    	return false;
    }
}
