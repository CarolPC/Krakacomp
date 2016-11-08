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

public class MessageSendStatement extends Statement {

	public MessageSendStatement(MessageSend messageSend) {
		this.messageSend = messageSend;
	}

	public void genC( PW pw ) {
	      pw.printIdent("");
	      // messageSend.genC(pw);
	      pw.println(";");
	   }

	private MessageSend messageSend;

	@Override
	public void genKra(PW pw) {
		pw.printIdent("");
		messageSend.genKra(pw);
		pw.println(";");
	}

}
