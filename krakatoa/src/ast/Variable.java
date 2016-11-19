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


public class Variable {

    public Variable( String name, Type type ) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    
    public String getCName() { return "_" + name; }

    public Type getType() {
        return type;
    }
    
    private String name;
    private Type type;
    
	public void genKra(PW pw) {
		pw.printIdent(type.getName() + " " + name);
	}
	
	 @Override
	 public boolean equals(Object obj) {
			        
	     Variable v = (Variable)obj;
	    
	     if(v == null)
	    	 return false;
	     
	     return v.getName().equals(this.getName());
	 }
}
