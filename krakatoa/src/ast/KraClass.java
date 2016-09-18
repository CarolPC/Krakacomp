package ast;
/*
 * Krakatoa Class
 */
public class KraClass extends Type {
	
   public KraClass( String name ) {
      super(name);
   }
   
   public String getCname() {
      return getName();
   }
   
   private String name;
   private KraClass superclass;
   private InstanceVariableList instanceVariableList;
   private MethodList publicMethodList, privateMethodList;
   // métodos públicos get e set para obter e iniciar as variáveis acima,
   // entre outros métodos
   
   public void setInstanceVariableList(InstanceVariableList instanceVariableList)
   {
	   this.instanceVariableList = instanceVariableList;
   }
   
   public void setSuperclass(KraClass superclass)
   {
	   this.superclass = superclass;
   }
   
   public void setPublicMethodList(MethodList publicMethodList)
   {
	   this.publicMethodList = publicMethodList;
   }
   
   public void setPrivateMethodList(MethodList privateMethodList)
   {
	   this.privateMethodList = privateMethodList;
   }
   
}
