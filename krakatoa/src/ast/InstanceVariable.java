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

	private String classPrefix;
	
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
    
    public String getClassPrefix()
    {
    	return this.classPrefix;
    }

	public void genKra(PW pw) {
		pw.printlnIdent("private " + getType().getName() + " " + getName() + ";");
	}

	public void genC(PW pw, String classPrefix) {
		if(super.getType() instanceof KraClass) {
			KraClass kraClass = (KraClass) getType();
			
			if (kraClass.getCname().equals(classPrefix))
				pw.printlnIdent("struct " + kraClass.getCStructName() + "* " + classPrefix + getCName() + ";");
			else
				pw.printlnIdent(kraClass.getCTypeName() + "* " + classPrefix + getCName() + ";");
		} else
			pw.printlnIdent(getType().getCname() + " " + classPrefix + getCName() + ";");
		this.classPrefix = classPrefix;
	}

	public String getCName() {
		return "_" + getName();
	}

}
