package ast;

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
		// TODO Auto-generated method stub

	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return this.method.getType();
	}

	@Override
	public void genKra(PW pw) {
		// TODO Auto-generated method stub

	}

}
