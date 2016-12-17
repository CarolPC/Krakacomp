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


public class MessageSendToVariable extends MessageSend { 

	private MessageSend sender;
	private Variable v;
	
	public MessageSendToVariable(MessageSend sender, Variable v) {
		this.sender = sender;
		this.v = v;
	}
	
	
    public Type getType() { 
        return v.getType();
    }
    
    public void genC( PW pw, boolean putParenthesis ) {
    	
    	if(this.sender != null)
    	{
    		this.sender.genC(pw,false);
    		pw.print("->");
    	}
    	if(this.sender.getType() instanceof KraClass)
    		pw.print(((KraClass)this.sender.getType()).getCname() + this.v.getCName());
    	else
    		pw.print(this.v.getCName());   
    }

	@Override
	public void genKra(PW pw) {
		sender.genKra(pw);
		pw.print(".");
		pw.print(v.getName());
	}

    
}    
