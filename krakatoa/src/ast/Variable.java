package ast;


public class Variable {

    public Variable( String name, Type type ) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }

    public Type getType() {
        return type;
    }
    
    private String name;
    private Type type;
    
	public void genKra(PW pw) {
		pw.print(type.getName() + " " + name);
	}
	
	 @Override
	 public boolean equals(Object obj) {
		 if (obj == null) {
			 throw new RuntimeException("parameter is not Variable Type");
	     }
	     if (!Variable.class.isAssignableFrom(obj.getClass())) {
	         throw new RuntimeException("parameter is not InstanceVariable Type");
	     }
	        
	     Variable v = (Variable)obj;
	        
	     return v.getName() == this.getName();
	 }
}