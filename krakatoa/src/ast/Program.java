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

import java.util.*;
import comp.CompilationError;

public class Program {

	public Program(ArrayList<KraClass> classList, ArrayList<MetaobjectCall> metaobjectCallList, 
			       ArrayList<CompilationError> compilationErrorList) {
		this.classList = classList;
		this.metaobjectCallList = metaobjectCallList;
		this.compilationErrorList = compilationErrorList;
	}


	public void genKra(PW pw) {
		for (KraClass kraClass : classList)
			kraClass.genKra(pw);
	}

	public void genC(PW pw) {
		pw.println("#include <malloc.h>");
		pw.println("#include <stdlib.h>");
		pw.println("#include <stdio.h>");
		pw.println();
		pw.println("typedef int boolean;");
		pw.println("#define TRUE 1");
		pw.println("#define FALSE 0");
		pw.println();
		pw.println("typedef\n  void (*Func)();");
		pw.println();
		
		for (KraClass kraClass : classList)
			kraClass.genC(pw);
		
		pw.printlnIdent("int main() {");
		pw.add();
		pw.printlnIdent("_class_program *program;");
		pw.printlnIdent("program = new_Program()");
		//tava no pdf que nem sempre a run vai ser indice 0
		pw.printlnIdent("( ( void (*)(_class_Program *) ) program->vt[0] )(program)");
		pw.printlnIdent("return 0;");
		pw.sub();
		pw.printIdent("}");
		pw.println();
	}
	
	public ArrayList<KraClass> getClassList() {
		return classList;
	}


	public ArrayList<MetaobjectCall> getMetaobjectCallList() {
		return metaobjectCallList;
	}
	

	public boolean hasCompilationErrors() {
		return compilationErrorList != null && compilationErrorList.size() > 0 ;
	}

	public ArrayList<CompilationError> getCompilationErrorList() {
		return compilationErrorList;
	}

	
	private ArrayList<KraClass> classList;
	private ArrayList<MetaobjectCall> metaobjectCallList;
	
	ArrayList<CompilationError> compilationErrorList;

	
}
