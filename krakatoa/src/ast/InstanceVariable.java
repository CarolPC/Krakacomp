package ast;

import javax.management.RuntimeErrorException;

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
        
        return v.getName() == this.getName();
    }

}