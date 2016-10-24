package ast;


public class MessageSendToSelf extends MessageSend {
 
	private KraClass type;
	
	public MessageSendToSelf(KraClass type) {
		this.type = type;
	}
    public Type getType() { 
        return this.type;
    }
    
    public void genC( PW pw, boolean putParenthesis ) {
    }

	@Override
	public void genKra(PW pw) {
		// TODO Auto-generated method stub
		
	}
    
    
}