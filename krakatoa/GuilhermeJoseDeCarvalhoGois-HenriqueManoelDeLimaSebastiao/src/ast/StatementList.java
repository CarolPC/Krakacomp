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

public class StatementList extends Statement {

	public StatementList() {
		stmtList = new ArrayList<Statement>();
	}

	public void addElement(Statement s) {
		stmtList.add(s);
	}

	public Iterator<Statement> elements() {
		return stmtList.iterator();
	}

	public int getSize() {
		return stmtList.size();
	}

	private ArrayList<Statement> stmtList;

	public void genKra(PW pw) {

		pw.println(" {");
		pw.add();

		for (Statement s : stmtList)
			s.genKra(pw);

		pw.sub();
		pw.printIdent("}");
	}

	@Override
	public void genC(PW pw) {

	}

}
