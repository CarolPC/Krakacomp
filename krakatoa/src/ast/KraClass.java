package ast;
/*
 * Krakatoa Class
 */
public class KraClass extends Type {

   public KraClass( String name ) {
      KraClass(name, false);
   }

	 public KraClass( String name, boolean isFinal ) {
      super(name);

			this.isFinal = isFinal;
   }

   public String getCname() {
      return getName();
   }

   private String name;
   private KraClass superclass;
	 private boolean isFinal;
   private InstanceVariableList instanceVariableList;
   private MethodList publicMethodList;
	 private MethodList privateMethodList;
   private ClassVariableList classVariableList;
   private ClassMethodList publicClassMethodList;
	 private ClassMethodList privateClassMethodList;
   // m�todos p�blicos get e set para obter e iniciar as vari�veis acima,
   // entre outros m�todos

   public KraClass getSuperclass() {
	   return this.superclass;
   }

   public void setSuperclass(KraClass superclass) {
	   this.superclass = superclass;
   }

	 public boolean isFinal() {
		 return this.isFinal;
	 }

	 // TODO Precisamos ver se esses Lists precisam de getter ou ainda se seria
	 // interessante ter um método add
   public void setInstanceVariableList(InstanceVariableList instanceVariableList) {
	   this.instanceVariableList = instanceVariableList;
   }

	 public void setPublicMethodList(MethodList publicMethodList) {
		 this.publicMethodList = publicMethodList;
	 }

	 public void setPrivateMethodList(MethodList privateMethodList) {
		 this.privateMethodList = privateMethodList;
	 }

   public void setClassVariableList(ClassVariableList classVariableList) {
		 this.classVariableList = classVariableList;
	 }

   public void setClassMethodList(ClassMethodList publicClassMethodList) {
		 this.publicClassMethodList = publicClassMethodList;
	 }

	 public void setClassMethodList(ClassMethodList privateClassMethodList) {
		 this.privateClassMethodList = privateClassMethodList;
	 }

}
