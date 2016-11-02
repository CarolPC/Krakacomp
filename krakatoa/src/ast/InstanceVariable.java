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

		pw.printlnIdent(getType().getName() + " " + getName());

	}

}