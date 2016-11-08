/*
Primeiro Trabalho de Laborat�rio de Compiladores
 
Segundo Semestre de 2016.
Campus de Sorocaba da UFSCar
Prof. Jos� de Oliveira Guimar�es
 
Grupo:
Nome: Guilherme Jos� Carvalho Gois
Nome: Henrique Manoel de Lima Sebasti�o
 */


package ast;

import java.util.ArrayList;

/** This class represents a metaobject call as <code>{@literal @}ce(...)</code> in <br>
 * <code>
 * @ce(5, "'class' expected") <br>
 * clas Program <br>
 *     public void run() { } <br>
 * end <br>
 * </code>
 * 
   @author Jos�
   
 */
public class MetaobjectCall {

	public MetaobjectCall(String name, ArrayList<Object> paramList) {
		this.name = name;
		this.paramList = paramList;
	}
	
	public ArrayList<Object> getParamList() {
		return paramList;
	}
	public String getName() {
		return name;
	}


	private String name;
	private ArrayList<Object> paramList;

}
