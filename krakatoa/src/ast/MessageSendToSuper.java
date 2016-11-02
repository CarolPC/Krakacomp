package ast;

public class MessageSendToSuper extends MessageSend { 

	private KraClass type;
	
	public MessageSendToSuper(KraClass superClass) {
		this.type = superClass;
	}
	
    public Type getType() { 
        return this.type;
    }

    public void genC( PW pw, boolean putParenthesis ) {
        
    }

	@Override
	public void genKra(PW pw) {
		pw.print("super");
	}
    
}