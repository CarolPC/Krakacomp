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

import java.util.ArrayList;
import java.util.Iterator;

public class MethodList {

	public MethodList() {
		methodList = new ArrayList<MethodDec>();
	}

	public boolean addElement(MethodDec m) {
		if (methodList.contains(m))
			return false;

		methodList.add(m);

		return true;
	}

	public Iterator<MethodDec> elements() {
		return methodList.iterator();
	}

	public int getSize() {
		return methodList.size();
	}

	public MethodDec searchMethod(MethodDec m) {
		for (MethodDec mSearch : this.methodList) {
			if (m.equals(mSearch))
				return mSearch;
		}

		return null;
	}

	private ArrayList<MethodDec> methodList;

	public void genKra(PW pw) {
		for (Iterator<MethodDec> iterator = elements(); iterator.hasNext();)
			iterator.next().genKra(pw);
	}
}
