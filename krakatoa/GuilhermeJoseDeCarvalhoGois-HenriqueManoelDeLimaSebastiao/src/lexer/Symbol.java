/*
Primeiro Trabalho de Laboratório de Compiladores
 
Segundo Semestre de 2016.
Campus de Sorocaba da UFSCar
Prof. José de Oliveira Guimarães
 
Grupo:
Nome: Guilherme José Carvalho Gois
Nome: Henrique Manoel de Lima Sebastião
 */


package lexer;

public enum Symbol {

    AND("&&"),
    ASSERT("assert"),
    ASSIGN("="),
    BOOLEAN("boolean"),
    BREAK("break"),
    CLASS("class"),
    COLON(":"),
    COMMA(","),
    DIV("/"),
    DOT("."),
    ELSE("else"),
    EOF("~eof"),
    EQ("=="),
    EXTENDS("extends"),
    FALSE("false"),
    FINAL("final"),
    GE(">="),
    GT(">"),
    IDENT("~ident"),
    IF("if"),
    INT("int"),
    LE("<="),
    LEFTCURBRACKET("{"),
    LEFTPAR("("),
    LITERALINT("~number"),
    LITERALSTRING("~literalString"),
    LT("<"),
    MINUS("-"),
    MOCall("~metaobjectCall"),
    MULT("*"),
    NEQ("!="),
    NEW("new"),
    NOT("!"),
    NULL("null"),
    OR("||"),
    PLUS("+"),
    PRIVATE("private"),
    PUBLIC("public"),
    READ("read"),
    RETURN("return"),
    RIGHTCURBRACKET("}"),
    RIGHTPAR(")"),
    SEMICOLON(";"),
    STRING("String"),
    SUPER("super"),
    THIS("this"),
    TRUE("true"),
    VOID("void"),
    WHILE("while"),
    WRITE("write"),
    WRITELN("writeln"),
    DO("do");

	Symbol(String name) {
		this.name = name;
	}

	@Override public String toString() {
		return name;
	}
	private String name;
}
