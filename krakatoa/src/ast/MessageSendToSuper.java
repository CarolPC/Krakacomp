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

public class MessageSendToSuper extends MessageSend { 

	private KraClass type;
	
	public MessageSendToSuper(KraClass superClass) {
		this.type = superClass;
	}
	
    public Type getType() { 
        return this.type;
    }

    public void genC( PW pw, boolean putParenthesis ) {
        //deve fazer nada
    	//se chamar o metodo ele vai pegar o tipo daqui e escrever o cast corretamente
    }

	@Override
	public void genKra(PW pw) {
		pw.print("super");
	}
    
}
