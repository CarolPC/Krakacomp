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

import lexer.Symbol;

public class MessageSendToMethod extends MessageSend {

	private MessageSend sender;
	private MethodDec method;
	private ExprList params;
	
	public MessageSendToMethod(MessageSend sender, MethodDec method, ExprList params) {
		this.sender = sender;
		this.method = method;
		this.params = params;
	}
	
	@Override
	public void genC(PW pw, boolean putParenthesis) {
		KraClass kraClass = (KraClass) this.sender.getType();
		
		if(this.method.getQualifier() == Symbol.PRIVATE)
		{
			pw.print(kraClass.getCname()+this.method.getCName());
			pw.print("(this");
		}
		else
		{
			pw.print("( ("+this.method.getType().getCname()+"(*)("+kraClass.getCTypeName()+" *");
			
			if(this.params != null)
			{
				pw.print(", ");
				this.method.getParamList().genCTypeList(pw);
			}
			pw.print(")) ");
			
			this.sender.genC(pw,false);
			if(!(this.sender instanceof MessageSendToSuper))
				pw.print("->");
			
			pw.print("vt["+kraClass.searchPublicMethodIndex(method)+"](");
			
			this.sender.genC(pw,false);
			
		}
		
		
		if(this.params != null)
		{
			pw.print(",");
			params.genC(pw);
		}
		pw.print(")");
		
	}

	@Override
	public Type getType() {
		return this.method.getType();
	}

	@Override
	public void genKra(PW pw) {
		sender.genKra(pw);

		pw.print(".");
		pw.print(method.getName());
		pw.print("(");
		if(params != null)
			params.genKra(pw);
		pw.print(")");

	}

}
