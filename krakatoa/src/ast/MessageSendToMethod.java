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
