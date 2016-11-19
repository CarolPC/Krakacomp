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

public class InstanceVariable extends Variable {

    public InstanceVariable( String name, Type type ) {
        super(name, type);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
        	throw new RuntimeException("parameter is not Variable Type");
        }
        if (!InstanceVariable.class.isAssignableFrom(obj.getClass())) {
            throw new RuntimeException("parameter is not InstanceVariable Type");
        }
        
        InstanceVariable v = (InstanceVariable)obj;
        
        return v.getName().equals(this.getName());
    }

	public void genKra(PW pw) {
		pw.printlnIdent("private " + getType().getName() + " " + getName() + ";");
	}

	public void genC(PW pw, String classPrefix) {
		pw.printlnIdent(getType().getCname() + " " + classPrefix + getCName() + ";");
	}

	public String getCName() {
		return "_" + getName();
	}

}
