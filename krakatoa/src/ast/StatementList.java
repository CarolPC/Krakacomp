package ast;

import java.util.ArrayList;
import java.util.Iterator;

public class StatementList extends Statement {
	
		public StatementList() {
	       stmtList = new ArrayList<Statement>();
	    }

	    public void addElement(Statement s) {
	       stmtList.add(s);
	    }

	    public Iterator<Statement> elements() {
	        return stmtList.iterator();
	    }

	    public int getSize() {
	        return stmtList.size();
	    }

	    private ArrayList<Statement> stmtList;

		public void genKra(PW pw) {

			pw.println("{");
			pw.add();
			
			for(Statement s : stmtList)
			{
				pw.printIdent("");
				s.genKra(pw);
			}
			
			pw.sub();
			pw.println("}");
		}

		@Override
		public void genC(PW pw) {
			
		}

}
