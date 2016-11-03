/*
Primeiro Trabalho de Laboratório de Compiladores
 
Segundo Semestre de 2016.
Campus de Sorocaba da UFSCar
Prof. José de Oliveira Guimarães
 
Grupo:
Nome: Guilherme José Carvalho Gois
Nome: Henrique Manoel de Lima Sebastião
 */


package comp;

public class CompilerOptions {
    
   public CompilerOptions() {
      count = false;
      outputInterface = false;
      extractClass = false;
   }
   
   public void setCount(boolean count) {
      this.count = count;
   }
   public boolean getCount() {
      return count;
   }
   
   public void setOutputInterface( boolean outputInterface ) {
      this.outputInterface = outputInterface;
   }
   
   public boolean getOutputInterface() {
      return outputInterface;
   }
   
   public void setExtractClass( boolean extractClass ) {
      this.extractClass = extractClass;
   }
   
   public boolean getExtractClass() {
      return extractClass;
   }
   
   private boolean count, outputInterface, extractClass;
   
}
