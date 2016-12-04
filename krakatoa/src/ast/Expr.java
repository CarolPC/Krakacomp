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

abstract public class Expr {
    abstract public void genC( PW pw, boolean putParenthesis );
      // new method: the type of the expression
    abstract public Type getType();
    
    
	abstract public void genKra(PW pw);
}
