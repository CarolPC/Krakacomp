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

public class ExprList {

    public ExprList() {
        exprList = new ArrayList<Expr>();
    }

    public void addElement( Expr expr ) {
        exprList.add(expr);
    }
    
    public ArrayList<Expr> getList()
    {
    	return this.exprList;
    }

    public void genC( PW pw ) {

        int size = exprList.size();
        for ( Expr e : exprList ) {
        	e.genC(pw, false);
            if ( --size > 0 )
                pw.print(", ");
        }
    }
    

    private ArrayList<Expr> exprList;

	public void genKra(PW pw) {
		int size = exprList.size();
        for ( Expr e : exprList ) {
        	e.genKra(pw);
            if ( --size > 0 )
                pw.print(", ");
        }
	}

}
