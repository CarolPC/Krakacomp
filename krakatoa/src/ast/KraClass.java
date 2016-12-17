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
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

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
	
	public String getPrintfName()
	{
		return "";
	}

	private KraClass superclass;
	private InstanceVariableList instanceVariableList;
	private MethodList publicMethodList, privateMethodList;
	SortedMap<String, String> methodToClass;
	SortedMap<Integer, String> indexToMethodToClass;
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
	private int getInitMethodIndex()
	{
		int len = 0;
		
		KraClass c = this.superclass;
		
		while(c != null)
		{
			len += c.getPublicMethodList().getSize();
			c = c.getSuperclass();
		}
		
		return len;
		
	}
	public int searchPublicMethodIndex(MethodDec m)
	{
		/*
		int idx = -1;
		int len = this.getInitMethodIndex();
		
		if(this.superclass != null)
		{
			
			idx = this.superclass.searchPublicMethodIndex(m);
			
			if(idx != -1)
				return idx;
		}
		
		return len + this.publicMethodList.searchMethodIndex(m);
		*/
		for (int i = 0; i < indexToMethodToClass.size(); i++) {
			if (indexToMethodToClass.get(i).equals(m.getCName()))
				return i;
		}
		return -1;
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
		
		pw.printlnIdent(getCTypeName() + " *" + getCNew() + "(void);");
		pw.println();
		
		methodToClass = new TreeMap<>();
		indexToMethodToClass = new TreeMap<>();
		buildMethodToClassMap(methodToClass, indexToMethodToClass);
		
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
			
		pw.printlnIdent("} " + getCTypeName()+";");
		pw.set(0);
		pw.println();
	}
	
	private void genCMethodsVector(PW pw) {
		pw.set(0);
		
		pw.printlnIdent("Func " + getVtClass() + "[] = {");
		pw.add();
		
		//stackAndPrintVTSuper(pw);
		
		Iterator<Entry<Integer, String>> iterator = indexToMethodToClass.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> entry = iterator.next();
			System.out.println(entry);
			
			pw.printIdent("( void (*)() ) " + methodToClass.get(entry.getValue()) + entry.getValue());
			
			if (iterator.hasNext())
				pw.println(",");
			else
				pw.println();
		}
		
		pw.println("};");
		pw.set(0);
		pw.println();
	}
	
	private void buildMethodToClassMap(SortedMap<String, String> methodToClass, SortedMap<Integer, String> indexToMethodToClass) {
		if (superclass != null)
			superclass.buildMethodToClassMap(methodToClass, indexToMethodToClass);
		
		Iterator<MethodDec> iteratorPublicMethods = publicMethodList.elements();
		while (iteratorPublicMethods.hasNext()) {
			MethodDec publicMethod = iteratorPublicMethods.next();
			
			if (methodToClass.containsKey(publicMethod.getCName())) {
				methodToClass.replace(publicMethod.getCName(), getCname());
				System.out.println("replace: " + getCname() + "::" + publicMethod.getCName());
			} else {
				methodToClass.put(publicMethod.getCName(), getCname());
				indexToMethodToClass.put(indexToMethodToClass.size(), publicMethod.getCName());
				System.out.println("put: " + getCname() + "::" + publicMethod.getCName());
			}
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
		pw.printlnIdent("t->vt = " + getVtClass() + ";");
		pw.sub();
		pw.printlnIdent("return t;");
		pw.println("}");
		pw.println();
	}

}
