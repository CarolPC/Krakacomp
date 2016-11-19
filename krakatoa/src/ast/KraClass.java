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

import java.util.Iterator;
import java.util.Stack;

/*
 * Krakatoa Class
 */
public class KraClass extends Type {

	public KraClass(String name) {
		super(name);

		superclass = null;
		instanceVariableList = null;
		publicMethodList = null;
		privateMethodList = null;
	}

	public String getCname() {
		return "_" + getName();
	}
	
	public String getCTypeName() {
		return "_class" + getCname();
	}
	
	public String getCStructName() {
		return "_St" + getCname();
	}
	
	public String getCNew() {
		return "new" + getCname();
	}
	
	public String getVtClass() {
		return "VTClass" + getCname();
	}

	private KraClass superclass;
	private InstanceVariableList instanceVariableList;
	private MethodList publicMethodList, privateMethodList;
	// métodos públicos get e set para obter e iniciar as variáveis acima,
	// entre outros métodos

	public MethodDec searchPublicMethod(MethodDec m) {
		MethodDec mSearch;

		if (this.publicMethodList != null)
			mSearch = this.publicMethodList.searchMethod(m);
		else
			mSearch = null;

		if (mSearch == null && getSuperclass() != null)
			return this.getSuperclass().searchPublicMethod(m);

		return mSearch;
	}

	public Variable searchInstanceVariable(String name) {
		Variable vSearch = (this.instanceVariableList == null) ? null : this.instanceVariableList.searchVariable(name);

		if (vSearch == null && this.getSuperclass() != null)
			return this.getSuperclass().searchInstanceVariable(name);

		return vSearch;
	}

	public MethodDec searchPrivateMethod(MethodDec m) {
		MethodDec mSearch;

		if (this.privateMethodList != null)
			mSearch = this.privateMethodList.searchMethod(m);
		else
			mSearch = null;

		if (mSearch == null && getSuperclass() != null)
			return this.getSuperclass().searchPrivateMethod(m);

		return mSearch;
	}

	public MethodDec searchMethod(MethodDec m) {
		MethodDec returnMethod;

		returnMethod = this.searchPublicMethod(m);

		if (returnMethod != null)
			return returnMethod;

		return this.searchPrivateMethod(m);
	}

	public InstanceVariableList getInstanceVariableList() {
		return instanceVariableList;
	}

	public void setInstanceVariableList(InstanceVariableList instanceVariableList) {
		this.instanceVariableList = instanceVariableList;
	}

	public KraClass getSuperclass() {
		return superclass;
	}

	public void setSuperclass(KraClass superclass) {
		this.superclass = superclass;
	}

	public MethodList getPublicMethodList() {
		return publicMethodList;
	}

	public void setPublicMethodList(MethodList publicMethodList) {
		this.publicMethodList = publicMethodList;
	}

	public MethodList getPrivateMethodList() {
		return privateMethodList;
	}

	public void setPrivateMethodList(MethodList privateMethodList) {
		this.privateMethodList = privateMethodList;
	}

	public void genKra(PW pw) {
		pw.printIdent("class " + getName());
		if (superclass != null)
			pw.print(" extends " + superclass.getName());
		pw.println(" {");

		if (instanceVariableList != null && instanceVariableList.getSize() > 0) {
			pw.add();
			instanceVariableList.genKra(pw);
			pw.sub();
		}

		if (privateMethodList != null && privateMethodList.getSize() > 0) {
			pw.add();
			privateMethodList.genKra(pw);
			pw.sub();
		}

		if (publicMethodList != null && publicMethodList.getSize() > 0) {
			pw.add();
			publicMethodList.genKra(pw);
			pw.sub();
		}

		pw.printlnIdent("}");

		pw.println();
	}
	
	public MethodDec getMethodByName(String name) {
		MethodDec methodDec;
		
		Iterator<MethodDec> iterator = publicMethodList.elements();
		while (iterator.hasNext()) {
			methodDec = iterator.next();
			if (name.equals(methodDec.getName()))
				return methodDec;
		}
		
		iterator = privateMethodList.elements();
		while (iterator.hasNext()) {
			methodDec = iterator.next();
			if (name.equals(methodDec.getName()))
				return methodDec;
		}
		
		return null;
	}

	public KraClass searchMethodByName(String name, MethodDec methodDec) {
		Iterator<MethodDec> iterator = publicMethodList.elements();
		while (iterator.hasNext()) {
			methodDec = iterator.next();
			if (name.equals(methodDec.getName()))
				return this;
		}

		iterator = privateMethodList.elements();
		while (iterator.hasNext()) {
			methodDec = iterator.next();
			if (name.equals(methodDec.getName()))
				return this;
		}
		
		if (superclass != null)
			return superclass.searchMethodByName(name, methodDec);
		
		methodDec = null;
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean superEquals = super.equals(obj);
		
		if (!superEquals && obj instanceof KraClass) {
			KraClass kClass = (KraClass) obj;
			
			while ((kClass = kClass.superclass) != null) {
				if (getName().equals(kClass.getName()))
					return true;
			}
			
			return false;
		}
		
		return superEquals;
	}

	public void genC(PW pw) {		
		genCClassDefinition(pw);
		
		pw.printlnIdent(getCTypeName() + " *" + getCNew() + "(void)");
		pw.println();
		
		publicMethodList.genC(pw, getCname());
		privateMethodList.genC(pw, getCname());
		
		genCMethodsVector(pw);
		
		genCConstructor(pw);
	}

	private void genCClassDefinition(PW pw) {
		pw.set(0);
		
		pw.println("typedef");
		pw.add();
		pw.printlnIdent("struct " + getCStructName() + " {");
		pw.add();
		pw.printlnIdent("Func *vt;");
		
		Iterator<InstanceVariable> iteratorVarList = instanceVariableList.elements();
		while (iteratorVarList.hasNext())
			((InstanceVariable) iteratorVarList.next()).genC(pw, getCname());
			
		pw.printlnIdent("} " + getCTypeName());
		pw.set(0);
		pw.println();
	}
	
	private void genCMethodsVector(PW pw) {
		pw.set(0);
		
		pw.printlnIdent("Func " + getVtClass() + "[] = {");
		pw.add();
		
		//stackAndPrintVTSuper(pw);
		
		Iterator<MethodDec> iteratorPublicMethods = publicMethodList.elements();
		while (iteratorPublicMethods.hasNext()) {
			String cMethod = ((MethodDec) iteratorPublicMethods.next()).getCName();
			pw.printIdent("( void (*)() ) " + getCname() + cMethod);
			
			if (iteratorPublicMethods.hasNext())
				pw.println(",");
			else
				pw.println();
		}
		pw.printlnIdent("};");
		pw.set(0);
		pw.println();
	}
	
	private void stackAndPrintVTSuper(PW pw) {
		Stack<KraClass> stack = new Stack<>();
		KraClass auxClass = superclass;
		
		while (auxClass != null) {
			stack.push(auxClass);
			
			auxClass = superclass;
		}
		
		while (!stack.isEmpty()) {
			auxClass = stack.pop();
			
			auxClass.genCSuperMethodsVector(pw);
		}
	}

	private void genCSuperMethodsVector(PW pw) {
		Iterator<MethodDec> iteratorPublicMethods = publicMethodList.elements();
		while (iteratorPublicMethods.hasNext()) {
			String cMethod = ((MethodDec) iteratorPublicMethods.next()).getCName();
			pw.printIdent("( void (*)() ) " + cMethod);
			
			if (iteratorPublicMethods.hasNext())
				pw.println(",");
		}
	}

	private void genCConstructor(PW pw) {
		pw.set(0);
		
		pw.printlnIdent(getCTypeName() + " *" + getCNew() + "()");
		pw.printlnIdent("{");
		pw.add();
		pw.printlnIdent(getCTypeName() + " *t;");
		pw.println();
		pw.printlnIdent("if ( (t = malloc(sizeof(" + getCTypeName() + "))) != NULL )");
		pw.add();
		pw.printlnIdent("t->vt = " + getCTypeName() + ";");
		pw.sub();
		pw.printlnIdent("return t;");
		pw.printlnIdent("}");
		pw.println();
	}

}
