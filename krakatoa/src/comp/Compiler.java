
package comp;

import ast.*;
import lexer.*;
import java.io.*;
import java.util.*;

public class Compiler {
	
	//../tests/lex/ ../tests/sin/ ../tests/sem/ ../tests/chk/ ../tests/ger/

	// compile must receive an input with an character less than
	// p_input.lenght
	public Program compile(char[] input, PrintWriter outError) {

		ArrayList<CompilationError> compilationErrorList = new ArrayList<>();
		signalError = new ErrorSignaller(outError, compilationErrorList);
		symbolTable = new SymbolTable();
		lexer = new Lexer(input, signalError);
		signalError.setLexer(lexer);

		Program program = null;
		lexer.nextToken();
		program = program(compilationErrorList);
		return program;
	}

	private Program program(ArrayList<CompilationError> compilationErrorList) {
		// Program ::= KraClass { KraClass }
		ArrayList<MetaobjectCall> metaobjectCallList = new ArrayList<>();
		ArrayList<KraClass> kraClassList = new ArrayList<>();
		Program program = new Program(kraClassList, metaobjectCallList, compilationErrorList);
		try {
			while (lexer.token == Symbol.MOCall) {
				metaobjectCallList.add(metaobjectCall());
			}
			//kraClassList.add(classDec());
			while (lexer.token == Symbol.CLASS)
				kraClassList.add(classDec());
			
			boolean hasClassProgram = false;
			for (KraClass kraClass : kraClassList)
				if (kraClass.getName().equals("Program")) {
					hasClassProgram = true;
					break;
				}
			if (!hasClassProgram)
				signalError.showError("Source code without a class 'Program'");
			
			if (lexer.token != Symbol.EOF) {
				signalError.showError("End of file expected");
			}
		} catch (RuntimeException e) {
			// if there was an exception, there is a compilation signalError
			e.printStackTrace();
		}
		return program;
	}

	/**
	 * parses a metaobject call as <code>{@literal @}ce(...)</code> in <br>
	 * <code>
	 * &#64;ce(5, "'class' expected") <br>
	 * clas Program <br>
	 *     public void run() { } <br>
	 * end <br>
	 * </code>
	 *
	 * 
	 */
	@SuppressWarnings("incomplete-switch")
	private MetaobjectCall metaobjectCall() {
		String name = lexer.getMetaobjectName();
		lexer.nextToken();
		ArrayList<Object> metaobjectParamList = new ArrayList<>();
		if (lexer.token == Symbol.LEFTPAR) {
			// metaobject call with parameters
			lexer.nextToken();
			while (lexer.token == Symbol.LITERALINT || lexer.token == Symbol.LITERALSTRING
					|| lexer.token == Symbol.IDENT) {
				switch (lexer.token) {
				case LITERALINT:
					metaobjectParamList.add(lexer.getNumberValue());
					break;
				case LITERALSTRING:
					metaobjectParamList.add(lexer.getLiteralStringValue());
					break;
				case IDENT:
					metaobjectParamList.add(lexer.getStringValue());
				}
				lexer.nextToken();
				if (lexer.token == Symbol.COMMA)
					lexer.nextToken();
				else
					break;
			}
			if (lexer.token != Symbol.RIGHTPAR)
				signalError.showError("')' expected after metaobject call with parameters");
			else
				lexer.nextToken();
		}
		if (name.equals("nce")) {
			if (metaobjectParamList.size() != 0)
				signalError.showError("Metaobject 'nce' does not take parameters");
		} else if (name.equals("ce")) {
			if (metaobjectParamList.size() != 3 && metaobjectParamList.size() != 4)
				signalError.showError("Metaobject 'ce' take three or four parameters");
			if (!(metaobjectParamList.get(0) instanceof Integer))
				signalError.showError("The first parameter of metaobject 'ce' should be an integer number");
			if (!(metaobjectParamList.get(1) instanceof String) || !(metaobjectParamList.get(2) instanceof String))
				signalError.showError("The second and third parameters of metaobject 'ce' should be literal strings");
			if (metaobjectParamList.size() >= 4 && !(metaobjectParamList.get(3) instanceof String))
				signalError.showError("The fourth parameter of metaobject 'ce' should be a literal string");

		}

		return new MetaobjectCall(name, metaobjectParamList);
	}

	private KraClass classDec() {
		// Note que os m�todos desta classe n�o correspondem exatamente �s
		// regras
		// da gram�tica. Este m�todo classDec, por exemplo, implementa
		// a produ��o KraClass (veja abaixo) e partes de outras produ��es.

		/*
		 * KraClass ::= ``class'' Id [ ``extends'' Id ] "{" MemberList "}"
		 * MemberList ::= { Qualifier Member } Member ::= InstVarDec | MethodDec
		 * InstVarDec ::= Type IdList ";" MethodDec ::= Qualifier Type Id "("[
		 * FormalParamDec ] ")" "{" StatementList "}" Qualifier ::= [ "static" ]
		 * ( "private" | "public" )
		 */
		boolean hasRunMethod = false;
		
		if (lexer.token != Symbol.CLASS)
			signalError.showError("'class' expected");
		lexer.nextToken();
		if (lexer.token != Symbol.IDENT)
			signalError.show(ErrorSignaller.ident_expected);

		String className = lexer.getStringValue();
		
		if (symbolTable.getInGlobal(className) != null)
			signalError.showError("Class " + className + " has already been declared");
		KraClass currentClass = new KraClass(className);
		this.currClass = currentClass;
		
		symbolTable.putInGlobal(className, currentClass);
		lexer.nextToken();

		if (lexer.token == Symbol.EXTENDS) {
			lexer.nextToken();
			if (lexer.token != Symbol.IDENT)
				signalError.show(ErrorSignaller.ident_expected);
			String superclassName = lexer.getStringValue();
			
			if (className.equals(superclassName))
				signalError.showError("Class '" + className + "' is inheriting from itself");

			if (!isType(superclassName))
				signalError.showError("The specified superclass '" + superclassName + "' doesn't have been declared.");

			KraClass superClass = symbolTable.getInGlobal(superclassName);

			if (superClass == null)
				signalError.showError("The specified superclass '" + superclassName + "' doesn't have been declared.");

			KraClass kraClass = symbolTable.getInGlobal(className);
			kraClass.setSuperclass(superClass);
			lexer.nextToken();
		}

		InstanceVariableList instanceVarList = new InstanceVariableList();
		MethodList publicMethodList = new MethodList();
		MethodList privateMethodList = new MethodList();

		if (lexer.token != Symbol.LEFTCURBRACKET)
			signalError.showError("'{' expected", true);
		lexer.nextToken();
		while (lexer.token == Symbol.PRIVATE || lexer.token == Symbol.PUBLIC) {

			Symbol qualifier;
			switch (lexer.token) {
			case PRIVATE:
				lexer.nextToken();
				qualifier = Symbol.PRIVATE;
				break;
			case PUBLIC:
				lexer.nextToken();
				qualifier = Symbol.PUBLIC;
				break;
			default:
				signalError.showError("'public' or 'private' expected");
				qualifier = Symbol.PUBLIC;
			}
			
			Type t = type();
			
			if (lexer.token != Symbol.IDENT)
				signalError.showError("cldec Identifier expected");
			String name = lexer.getStringValue();
			lexer.nextToken();

			if (lexer.token == Symbol.LEFTPAR)
				
				if (qualifier == Symbol.PRIVATE)
					methodDec(t, name, qualifier, privateMethodList);
				else {
					methodDec(t, name, qualifier, publicMethodList);
					
					if(name.equals("run"))
						hasRunMethod = true;
					
				}
			else if (qualifier != Symbol.PRIVATE)
				signalError.showError("Attempt to declare a public instance variable");
			else
				instanceVarDec(t, name, instanceVarList);
		}
		
		// Isso não está funcionando
		if (className.equals("Program")) {
			if (!hasRunMethod)
				signalError.showError("Method 'run' was not found in class '" + className + "'");
		}

		currentClass.setInstanceVariableList(instanceVarList);
		currentClass.setPrivateMethodList(privateMethodList);
		currentClass.setPublicMethodList(publicMethodList);

		if (lexer.token != Symbol.RIGHTCURBRACKET)
			signalError.showError("'public', 'private', or '}' expected");
		lexer.nextToken();

		return currentClass;
	}

	private void instanceVarDec(Type type, String name, InstanceVariableList instanceVarList) {
		// InstVarDec ::= "private" Type IdList ";"
		InstanceVariable instanceVar = new InstanceVariable(name, type);
		// //System.out.println("216: " + lexer.token);
		if (!instanceVarList.addElement(instanceVar)) {
			signalError.showError("ivd Unique identifier expected");
		}

		while (lexer.token == Symbol.COMMA) {
			lexer.nextToken();
			if (lexer.token != Symbol.IDENT)
				signalError.showError("ivd while Identifier expected");

			String variableName = lexer.getStringValue();
			instanceVar = new InstanceVariable(variableName, type);

			if (!instanceVarList.addElement(instanceVar)) {
				signalError.showError("ivd while Unique identifier expected");
			}
			lexer.nextToken();
		}
		// //System.out.println("232: " + lexer.token);
		if (lexer.token != Symbol.SEMICOLON)
			signalError.show(ErrorSignaller.semicolon_expected);
		lexer.nextToken();
		// //System.out.println("236: " + lexer.token);
	}

	private void methodDec(Type type, String name, Symbol qualifier, MethodList methodList) {
		/*
		 * MethodDec ::= Qualifier Return Id "("[ FormalParamDec ] ")" "{"
		 * StatementList "}"
		 */
		
		if (name.equals("run"))
			if(qualifier == Symbol.PRIVATE)
				signalError.showError("Method 'run' of class '" + currClass.getName() + "' cannot be private");
			else if (type != Type.voidType)
				signalError.showError("Method 'run' of class '" + currClass.getName() + "' with a return value type different from 'void'");
		if (currClass.searchInstanceVariable(name) != null)
			signalError.showError("Method '" + name + "' has name equal to an instance variable");
		
		currentMethodReturnType = type;

		lexer.nextToken();
		ParamList parameterList = null;
		StatementList stmtList = null;

		if (lexer.token != Symbol.LEFTPAR)
			parameterList = formalParamDec();
		if (name.equals("run") && parameterList.getSize() > 0)
				signalError.showError("Method 'run' of class '" + currClass.getName() + "' cannot take parameters");
		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");
		
		MethodDec m = new MethodDec(qualifier, type, name, parameterList);

		lexer.nextToken();
		if (lexer.token != Symbol.LEFTCURBRACKET)
			signalError.showError("'{' expected");

		lexer.nextToken();
		m.setStatementList(statementList());
		
		if (currentMethodReturnType != Type.voidType) {
			Iterator<Statement> stmt = stmtList.elements();
			boolean hasReturnStatement = false;
			while (stmt.hasNext() && !(hasReturnStatement = stmt.next() instanceof ReturnStatement)) ;
			if (!hasReturnStatement)
				signalError.showError("Missing 'return' statement method in '" + name + "'");
		}
		
		if (lexer.token != Symbol.RIGHTCURBRACKET)
			signalError.showError("'}' expected");

		lexer.nextToken();

		symbolTable.removeLocalIdent();

		currentMethodReturnType = null;
		
		if(!methodList.addElement(m))
			signalError.showError("Method '" + name + "' is being redeclared");
	}

	private LocalVariableList localDec() {
		// LocalDec ::= Type IdList ";"
		LocalVariableList localDecList = new LocalVariableList();

		Type type = type();
		
		
		if (lexer.token != Symbol.IDENT)
			signalError.showError("ldec Identifier expected");
		String variableName = lexer.getStringValue();
		if (symbolTable.getInLocal(variableName) != null)
			signalError.showError("ldec Unique identifier expected");

		Variable v = new Variable(variableName, type);

		symbolTable.putInLocal(variableName, v);

		localDecList.addElement(v);

		lexer.nextToken();

				
		while (lexer.token == Symbol.COMMA) {
			lexer.nextToken();
						
			if (lexer.token != Symbol.IDENT)
				signalError.showError("Missing Identifier");

			variableName = lexer.getStringValue();

			if (symbolTable.getInLocal(variableName) != null)
				signalError.showError("ldec while Unique identifier expected");

			v = new Variable(variableName, type);

			symbolTable.putInLocal(variableName, v);

			localDecList.addElement(v);

			lexer.nextToken();
		}

		
		if (lexer.token != Symbol.SEMICOLON)
			signalError.showError("Missing ';'",true);
		
		lexer.nextToken();

		return localDecList;
	}

	private ParamList formalParamDec() {
		// FormalParamDec ::= ParamDec { "," ParamDec }

		ParamList parameterList = new ParamList();
		if (lexer.token == Symbol.RIGHTPAR)
			return parameterList;
		parameterList.addElement(paramDec());
		while (lexer.token == Symbol.COMMA) {
			lexer.nextToken();
			parameterList.addElement(paramDec());
		}

		return parameterList;
	}

	private Parameter paramDec() {
		// ParamDec ::= Type Id

		Type t = type();
		if (lexer.token != Symbol.IDENT)
			signalError.showError("pdec Identifier expected");

		String identName = lexer.getStringValue();

		if (symbolTable.getInLocal(identName) != null)
			signalError.showError("Parameter name " + identName + " has been previously declared in this method");

		lexer.nextToken();
		Parameter p = new Parameter(identName, t);

		symbolTable.putInLocal(identName, p);

		return p;
	}

	private Type type() {
		// Type ::= BasicType | Id
		Type result;

		switch (lexer.token) {
		case VOID:
			result = Type.voidType;
			break;
		case INT:
			result = Type.intType;
			break;
		case BOOLEAN:
			result = Type.booleanType;
			break;
		case STRING:
			result = Type.stringType;
			break;
		case IDENT:
			// # corrija: fa�a uma busca na TS para buscar a classe
			// IDENT deve ser uma classe.
			KraClass classType = symbolTable.getInGlobal(lexer.getStringValue());
			if (classType == null)
				signalError.showError("Class " + lexer.getStringValue() + " has not been declared");
			result = classType;
			break;
		default:
			signalError.showError("Type expected");
			result = Type.undefinedType;
		}
		lexer.nextToken();
		return result;
	}

	private StatementList compositeStatement() {

		if(lexer.token != Symbol.LEFTCURBRACKET)
			signalError.showError("{ expected",true);
		
		lexer.nextToken();
		StatementList stmtList = statementList();
		if (lexer.token != Symbol.RIGHTCURBRACKET)
			signalError.showError("'}' expected");

		lexer.nextToken();

		return stmtList;
	}

	private StatementList statementList() {
		// CompStatement ::= "{" { Statement } "}"
		//System.out.println("statementList");
		Symbol tk;
		StatementList stmtList = new StatementList();
		// statements always begin with an identifier, if, read, write, ...
		while ((tk = lexer.token) != Symbol.RIGHTCURBRACKET && tk != Symbol.ELSE)
			stmtList.addElement(statement());

		return stmtList;
	}

	private Statement statement() {
		/*
		 * Statement ::= Assignment ``;'' | IfStat |WhileStat | MessageSend
		 * ``;'' | ReturnStat ``;'' | ReadStat ``;'' | WriteStat ``;'' |
		 * ``break'' ``;'' | ``;'' | CompStatement | LocalDec
		 */

		Statement stmt = null;
		//System.out.println("426: " + lexer.token);
		switch (lexer.token) {
		case THIS:
		case IDENT:
		case SUPER:
		case INT:
		case BOOLEAN:
		case STRING:
			stmt = assignExprLocalDec();
			break;
		case ASSERT:
			stmt = assertStatement();
			break;
		case RETURN:
			stmt = returnStatement();
			break;
		case READ:
			stmt = readStatement();
			break;
		case WRITE:
			stmt = writeStatement();
			break;
		case WRITELN:
			stmt = writelnStatement();
			break;
		case IF:
			stmt = ifStatement();
			break;
		case BREAK:
			if (whileStack.size() == 0)
				signalError.showError("'break' statement found outside a 'while' statement");
			stmt = breakStatement();
			whileStack.pop();
			break;
		case WHILE:
			stmt = whileStatement();
			break;
		case DO:
			stmt = doWhileStatement(); 
			break;
		case SEMICOLON:
			stmt = nullStatement();
			break;
		case LEFTCURBRACKET:
			// NOTE Houston, temos um problema! Tecnicamente, um
			// CompositeStatement não
			// é um Statement, ou é?
			stmt = compositeStatement();
			break;
		// NOTE Foi o que eu pensei para tratarmos incremento e decremento,
		// veja primeiro se concorda Henrique, acho que daí precisamos criar
		// tipo uma classe para cada um dos tipos (inc e dec), talvez até
		// diferenciar
		// se é prefixo ou sufixo, não sei. (Gois)
		case PLUS:
			lexer.nextToken();
			if (lexer.token != Symbol.PLUS)
				signalError.showError("Missing '+'");
			lexer.nextToken();
			if (lexer.token != Symbol.IDENT)
				signalError.showError("stat case + Identifier expected");
			lexer.nextToken();
			if (lexer.token != Symbol.SEMICOLON)
				signalError.showError("Missing ';'");
			lexer.nextToken();
			// stmt = new IncrementStatement(new Variable(lexer.token()));
			break;
		case MINUS:
			lexer.nextToken();
			if (lexer.token != Symbol.MINUS)
				signalError.showError("Missing '-'");
			lexer.nextToken();
			if (lexer.token != Symbol.IDENT)
				signalError.showError("stat case - Identifier expected");
			lexer.nextToken();
			if (lexer.token != Symbol.SEMICOLON)
				signalError.showError("Missing ';'");
			lexer.nextToken();
			// stmt = new DecrementStatement(new Variable(lexer.token()));
			break;
		default:
			////System.out.println("493: " + lexer.token);
			signalError.showError("stat def Statement expected");
		}

		return stmt;
	}

	private Statement assertStatement() {
		lexer.nextToken();
		int lineNumber = lexer.getLineNumber();
		Expr e = expr();
		if (e.getType() != Type.booleanType)
			signalError.showError("boolean expression expected");
		if (lexer.token != Symbol.COMMA) {
			this.signalError.showError("',' expected after the expression of the 'assert' statement");
		}
		lexer.nextToken();
		if (lexer.token != Symbol.LITERALSTRING) {
			this.signalError.showError("A literal string expected after the ',' of the 'assert' statement");
		}
		String message = lexer.getLiteralStringValue();
		lexer.nextToken();
		
		if (lexer.token != Symbol.SEMICOLON)
			signalError.showError("Expected ;",true);
		
		lexer.nextToken();

		return new StatementAssert(e, lineNumber, message);
	}

	/*
	 * retorne true se 'name' � uma classe declarada anteriormente. � necess�rio
	 * fazer uma busca na tabela de s�mbolos para isto.
	 */
	private boolean isType(String name) {
		return this.symbolTable.getInGlobal(name) != null;
	}

	/*
	 * AssignExprLocalDec ::= Expression [ ``$=$'' Expression ] | LocalDec
	 */
	private Statement assignExprLocalDec() {
		if (lexer.token == Symbol.INT || lexer.token == Symbol.BOOLEAN || lexer.token == Symbol.STRING ||
		// token � uma classe declarada textualmente antes desta
		// instru��o
				(lexer.token == Symbol.IDENT && symbolTable.getInLocal(lexer.getLiteralStringValue()) == null  && isType(lexer.getStringValue()))) {
			/*
			 * uma declara��o de vari�vel. 'lexer.token' � o tipo da vari�vel
			 *
			 * AssignExprLocalDec ::= Expression [ ``$=$'' Expression ] |
			 * LocalDec LocalDec ::= Type IdList ``;''
			 */
			return localDec();
		} else if (lexer.token == Symbol.SUPER) {
			return new MessageSendStatement((MessageSendToSuper) expr());
		} else if (lexer.token == Symbol.THIS) {
			return new MessageSendStatement((MessageSendToSelf) expr());
		} else {
			Expr left  = null;
			Expr right = null;
			
			/*
			 * AssignExprLocalDec ::= Expression [ ``$=$'' Expression ]
			 * 
			 * NOTE ER-SIN04.kra está vindo pra cá na linha 8, não sei como verificar se é um statement ou não.
			 */
			//if ((lexer.token == Symbol.IDENT && isType(lexer.getStringValue())))
				//signalError.showError("Statement expected");
			//System.out.println("558:" + lexer.token);
			left = expr();
			//System.out.println(lexer.token);
			if (lexer.token == Symbol.ASSIGN) {
				lexer.nextToken();
				right = expr();	
				if (lexer.token != Symbol.SEMICOLON)
					signalError.showError("Missing ';'", true);
				
				lexer.nextToken();
				
				if (left.getType() != Type.undefinedType && right.getType() == Type.undefinedType)
					signalError.showError("Type error: type of the left-hand side of the assignment is a basic type and the type of the right-hand side is a class");
				if (left.getType() == Type.undefinedType && right.getType() != Type.undefinedType)
					signalError.showError("Type error: type of the right-hand sido of the assignment is a basic type and the type of the left-hand side is a class");
				if (left.getType() != Type.undefinedType && right.getType() == Type.voidType)
					signalError.showError("Type error: 'null' cannot be assigned to a variable of a basic type");
				if (left.getType() != right.getType())
					signalError.showError("Type error: '" + right.getType().getName() + "' cannot be assigned to " + left.getType().getName());
				// Check subtype
				if (left.getType() == Type.undefinedType && right.getType() == Type.undefinedType) {
					KraClass leftClassType = this.symbolTable.getInGlobal(left.getType().getName());
					KraClass rightClassType = this.symbolTable.getInGlobal(right.getType().getName());
					
					while (rightClassType.getSuperclass() != null)
						if (rightClassType.getSuperclass().getName().equals(leftClassType.getName()))
							break;
						else
							rightClassType = rightClassType.getSuperclass();
					
					if (rightClassType == null)
						signalError.showError("Type error: '" + right.getType().getName() + "' is not a subtype of " + left.getType().getName());
				}
				
				return new AssignStatement(left, right);
			}
			
			if (lexer.token != Symbol.SEMICOLON )
				signalError.showError("aeld ; Statement expected",true);
			lexer.nextToken();

			return new MessageSendStatement((MessageSend) left);
		}
	}

	private ExprList realParameters() {
		ExprList anExprList = null;

		if (lexer.token != Symbol.LEFTPAR)
			signalError.showError("'(' expected");
		lexer.nextToken();
		if (startExpr(lexer.token))
			anExprList = exprList();
		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");
		lexer.nextToken();
		return anExprList;
	}

	private WhileStatement whileStatement() {
		whileStack.push(true);

		lexer.nextToken();
		if (lexer.token != Symbol.LEFTPAR)
			signalError.showError("'(' expected");
		lexer.nextToken();
		Expr e = expr();
		if (e.getType() != Type.booleanType)
			signalError.showError("boolean expression expected in a while statement");
		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");
		lexer.nextToken();
		Statement stmt = statement();
		
		// Acho que deveria ter um pop aqui, mas quando tem, tem um erro que não é reportado
		//whileStack.pop();

		return new WhileStatement(e, stmt);
	}

	private DoWhileStatement doWhileStatement() {		
		if (lexer.token != Symbol.DO)
			signalError.showError("expected 'do'");
		lexer.nextToken();
		if (lexer.token != Symbol.LEFTCURBRACKET)
			signalError.showError("'{' expected after do");
		Statement stmt = statement();
		
		if (lexer.token != Symbol.WHILE)
			signalError.showError("expected 'while'");
		
		lexer.nextToken();
		if (lexer.token != Symbol.LEFTPAR)
			signalError.showError("'(' expected");
		lexer.nextToken();
		Expr e = expr();
		if (e.getType() != Type.booleanType)
			signalError.showError("boolean expression expected in a do-while statement");
		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");
		lexer.nextToken();

		if(lexer.token != Symbol.SEMICOLON)
			signalError.showError("expected ; after ) in do-while statement",true);
		
		lexer.nextToken();
		
		return new DoWhileStatement(e, stmt);
	}

	private IfStatement ifStatement() {

		lexer.nextToken();
		if (lexer.token != Symbol.LEFTPAR)
			signalError.showError("'(' expected");
		lexer.nextToken();
		Expr e = expr();
		if (e.getType() != Type.booleanType)
			signalError.showError("boolean expression expected in an if statement");
		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");
		lexer.nextToken();
		Statement ifStmt = statement();
		if (lexer.token == Symbol.ELSE) {
			lexer.nextToken();
			return new IfElseStatement(e, ifStmt, statement());
		}

		return new IfStatement(e, ifStmt);
	}

	private ReturnStatement returnStatement() {

		lexer.nextToken();
		Expr e = expr();
		if (lexer.token != Symbol.SEMICOLON)	
			signalError.showError("; expected after return",true);
		
		if (e.getType() != currentMethodReturnType)
			signalError.showError("Illegal 'return' statement. Method returns'" + currentMethodReturnType.getName() + "'");
		
		lexer.nextToken();
		
		return new ReturnStatement(e);
	}

	private ReadStatement readStatement() {
		//System.out.println("readStatement");
		lexer.nextToken();
		if (lexer.token != Symbol.LEFTPAR)
			signalError.showError("'(' expected after 'read' command");
		lexer.nextToken();
		ExprList el = exprList();
		for (Expr e : el.getList())
			if (e.getType() == Type.booleanType)
				signalError.showError("Command 'read' does not accept 'boolean' variables");
			else if (e.getType() == Type.voidType)
				signalError.showError("Command 'read' does not accept 'void' variables");
			else if (e.getType() == Type.undefinedType)
				signalError.showError("Command 'read' does not accept non-primitives variables");
			else if (e.getType() instanceof KraClass)
				signalError.showError("Command 'read' expects a variable");
		/*while (true) {
			if (lexer.token == Symbol.THIS) {
				lexer.nextToken();
				if (lexer.token != Symbol.DOT)
					signalError.showError(". expected");
				lexer.nextToken();
			}
			if (lexer.token != Symbol.IDENT)
				signalError.show(ErrorSignaller.ident_expected);

			String name = lexer.getStringValue();
			lexer.nextToken();
			if (lexer.token == Symbol.COMMA)
				lexer.nextToken();
			else
				break;
		}*/

		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");
		lexer.nextToken();
		if (lexer.token != Symbol.SEMICOLON)
			signalError.showError("; expected in read statement",true);
		lexer.nextToken();

		return new ReadStatement(el);
	}

	private WriteStatement writeStatement() {
		//System.out.println("writeStatement");
		lexer.nextToken();
		if (lexer.token != Symbol.LEFTPAR)
			signalError.showError("'(' expected");
		lexer.nextToken();
		ExprList el = exprList();
		for (Expr e : el.getList())
			if (e.getType() == Type.booleanType)
				signalError.showError("Command 'write' does not accept 'boolean' variables");
			else if (e.getType() == Type.voidType)
				signalError.showError("Command 'write' does not accept 'void' variables");
			else if (e.getType() == Type.undefinedType)
				signalError.showError("Command 'write' does not accept non-primitives variables");
			else if (e.getType() instanceof KraClass)
				signalError.showError("Command 'write' does not accept objects");
		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");
		lexer.nextToken();
		if (lexer.token != Symbol.SEMICOLON)
			signalError.showError("; expected in write statement",true);
		lexer.nextToken();

		return new WriteStatement(el);
	}

	private WriteLineStatement writelnStatement() {

		lexer.nextToken();
		if (lexer.token != Symbol.LEFTPAR)
			signalError.showError("'(' expected");
		lexer.nextToken();
		ExprList el = exprList();
		for (Expr e : el.getList())
			if (e.getType() == Type.booleanType)
				signalError.showError("Command 'write' does not accept 'boolean' variables");
			else if (e.getType() == Type.voidType)
				signalError.showError("Command 'write' does not accept 'void' variables");
			else if (e.getType() == Type.undefinedType)
				signalError.showError("Command 'writeln' does not accept non-primitives variables");
			else if (e.getType() instanceof KraClass)
				signalError.showError("Command 'write' does not accept objects");
		WriteLineStatement writeStmt = new WriteLineStatement(el);
		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");
		lexer.nextToken();
		if (lexer.token != Symbol.SEMICOLON)
			signalError.showError("; expected in writeln statement",true);
		lexer.nextToken();

		return writeStmt;
	}

	// NOTE não sei se é útil, mas BreakStatement pode estar vazio só para
	// checarmos que temos um break e devemos cancelar e sair fora do laço
	// corrente
	private BreakStatement breakStatement() {			
		lexer.nextToken();
		if (lexer.token != Symbol.SEMICOLON)
			signalError.show(ErrorSignaller.semicolon_expected);
		lexer.nextToken();

		return new BreakStatement();
	}

	private NullStatement nullStatement() {
		lexer.nextToken();

		return new NullStatement();
	}

	private ExprList exprList() {
		// ExpressionList ::= Expression { "," Expression }
//System.out.println("exprList");
		ExprList anExprList = new ExprList();
		anExprList.addElement(expr());
		while (lexer.token == Symbol.COMMA) {
			lexer.nextToken();
			anExprList.addElement(expr());
		}
		//for (Expr e : anExprList.getList())
			////System.out.println(e.getType());
		return anExprList;
	}

	private Expr expr() {
		////System.out.println("expr");
		Expr left = simpleExpr();
		//System.out.println("758:" + lexer.token);
		Symbol op = lexer.token;
		if (op == Symbol.EQ || op == Symbol.NEQ || op == Symbol.LE || op == Symbol.LT || op == Symbol.GE
				|| op == Symbol.GT) {
			lexer.nextToken();
			
			Symbol opAux = lexer.token;
			if (opAux == Symbol.EQ || opAux == Symbol.NEQ || opAux == Symbol.LE || opAux == Symbol.LT || opAux == Symbol.GE
					|| opAux == Symbol.GT)
				System.out.println("Expression expected OR invalid sequence of symbols");
			
			Expr right = simpleExpr();
			
			//if (io == Symbol)
			
			left = new CompositeExpr(left, op, right);
		}
		return left;
	}

	private Expr simpleExpr() {
		Symbol op;

		Expr left = term();
		//System.out.println("789:" + lexer.token);
		while ((op = lexer.token) == Symbol.MINUS || op == Symbol.PLUS || op == Symbol.OR) {
			lexer.nextToken();
			Expr right = term();
			
			if (left.getType() != right.getType())
				signalError.showError("operator '" + op + "' of '" + left.getType().getName() + "' expects an '" + right.getType().getName() + "' value");
			left = new CompositeExpr(left, op, right);
		}
		return left;
	}

	private Expr term() {
		Symbol op;

		Expr left = signalFactor();
		//System.out.println("802:" + lexer.token);
		while ((op = lexer.token) == Symbol.DIV || op == Symbol.MULT || op == Symbol.AND) {
			lexer.nextToken();
			Expr right = signalFactor();
			left = new CompositeExpr(left, op, right);
		}
		return left;
	}

	private Expr signalFactor() {
		Symbol op;
		if ((op = lexer.token) == Symbol.PLUS || op == Symbol.MINUS) {
			lexer.nextToken();
			return new SignalExpr(op, factor());
		} else
			return factor();
	}

	/*
	 * Factor ::= BasicValue | "(" Expression ")" | "!" Factor | "null" |
	 * ObjectCreation | PrimaryExpr
	 *
	 * BasicValue ::= IntValue | BooleanValue | StringValue BooleanValue ::=
	 * "true" | "false" ObjectCreation ::= "new" Id "(" ")" PrimaryExpr ::=
	 * "super" "." Id "(" [ ExpressionList ] ")" | Id | Id "." Id | Id "." Id
	 * "(" [ ExpressionList ] ")" | Id "." Id "." Id "(" [ ExpressionList ] ")"
	 * | "this" | "this" "." Id | "this" "." Id "(" [ ExpressionList ] ")" |
	 * "this" "." Id "." Id "(" [ ExpressionList ] ")"
	 */
	private Expr factor() {

		Expr anExpr;
		ExprList exprList;
		String messageName, id;
		MethodDec m;
		Variable v;
		KraClass tmpClass;
		//System.out.println("839:" + lexer.token);
		switch (lexer.token) {
		// IntValue
		case LITERALINT:
			return literalInt();
		// BooleanValue
		case FALSE:
			lexer.nextToken();
			return LiteralBoolean.False;
		// BooleanValue
		case TRUE:
			lexer.nextToken();
			return LiteralBoolean.True;
		// StringValue
		case LITERALSTRING:
			String literalString = lexer.getLiteralStringValue();
			lexer.nextToken();
			return new LiteralString(literalString);
		// "(" Expression ")" |
		case LEFTPAR:
			lexer.nextToken();
			anExpr = expr();
			if (lexer.token != Symbol.RIGHTPAR)
				signalError.showError("')' expected");
			lexer.nextToken();
			return new ParenthesisExpr(anExpr);

		// "null"
		case NULL:
			lexer.nextToken();
			return new NullExpr();
		// "!" Factor
		case NOT:
			lexer.nextToken();
			anExpr = expr();
			return new UnaryExpr(anExpr, Symbol.NOT);
		// ObjectCreation ::= "new" Id "(" ")"
		case NEW:
			lexer.nextToken();
			if (lexer.token != Symbol.IDENT)
				signalError.showError("fact new Identifier expected");

			String className = lexer.getStringValue();
			/*
			 * // encontre a classe className in symbol table KraClass aClass =
			 * symbolTable.getInGlobal(className); if ( aClass == null ) ...
			 */
			KraClass aClass = symbolTable.getInGlobal(className);

			if (aClass == null)
				signalError.showError("classname " + className + " in the new command has not been declared");

			lexer.nextToken();
			if (lexer.token != Symbol.LEFTPAR)
				signalError.showError("'(' expected");
			lexer.nextToken();
			if (lexer.token != Symbol.RIGHTPAR)
				signalError.showError("')' expected");
			lexer.nextToken();
			/*
			 * return an object representing the creation of an object
			 */
			return new NewExpr(aClass);
		/*
		 * PrimaryExpr ::= "super" "." Id "(" [ ExpressionList ] ")" | Id | Id
		 * "." Id | Id "." Id "(" [ ExpressionList ] ")" | Id "." Id "." Id "("
		 * [ ExpressionList ] ")" | "this" | "this" "." Id | "this" "." Id "(" [
		 * ExpressionList ] ")" | "this" "." Id "." Id "(" [ ExpressionList ]
		 * ")"
		 */
		case SUPER:
			if (currClass.getSuperclass() == null)
				signalError.showError("'super' used in class '" + currClass.getName() + "' that does not have a superclass");
			// "super" "." Id "(" [ ExpressionList ] ")"
			lexer.nextToken();
			if (lexer.token != Symbol.DOT) {
				signalError.showError("'.' expected");
			} else
				lexer.nextToken();
			if (lexer.token != Symbol.IDENT)
				signalError.showError("fact sup Identifier expected");
			messageName = lexer.getStringValue();
			/*
			 * para fazer as confer�ncias sem�nticas, procure por 'messageName'
			 * na superclasse/superclasse da superclasse etc
			 */
			lexer.nextToken();
			exprList = realParameters();
			m = this.currClass.getSuperclass().searchPublicMethod(new MethodDec(messageName,exprList));
			
			if(m == null)
				signalError.showError("method \""+ messageName + "\"doesn't exist on superclass or its superclasses");
			
			return new MessageSendToMethod(new MessageSendToSuper(this.currClass.getSuperclass()), m, exprList);
			
		case IDENT:
			/*
			 * PrimaryExpr ::= Id | Id "." Id | Id "." Id "(" [ ExpressionList ]
			 * ")" | Id "." Id "." Id "(" [ ExpressionList ] ")" |
			 */
			String firstId = lexer.getStringValue();
			v = symbolTable.getInLocal(firstId);
			
			if(v == null) {
				v = this.currClass.searchInstanceVariable(firstId);
			
				if(v == null)
					signalError.showError("variable \""+firstId+"\" was not declared in this scope.");
				else
					signalError.showError("variable \""+firstId+"\" is a private variable.");
			
			}
			
			lexer.nextToken();

			if (lexer.token != Symbol.DOT) {
				// Id
				// retorne um objeto da ASA que representa um identificador
				return new VariableExpr(v);
				
			} else { // Id "."
				
				tmpClass = symbolTable.getInGlobal(v.getType().getName());
				
				if(tmpClass == null)
					signalError.showError(firstId+" is not a class instance");
				
				lexer.nextToken(); // coma o "."
				
				if (lexer.token != Symbol.IDENT) {
					signalError.showError("fact not id Identifier expected");
				} 
				else {
					// Id "." Id
					id = lexer.getStringValue();
					lexer.nextToken();
					if (lexer.token == Symbol.DOT) {
						// Id "." Id "." Id "(" [ ExpressionList ] ")"
						/*
						 * se o compilador permite vari�veis est�ticas, �
						 * poss�vel ter esta op��o, como
						 * Clock.currentDay.setDay(12); Contudo, se vari�veis
						 * est�ticas n�o estiver nas especifica��es, sinalize um
						 * erro neste ponto.
						 */
						signalError.showError("Static variables are not permitted");
						
						lexer.nextToken();
						if (lexer.token != Symbol.IDENT)
							signalError.showError("fact Identifier expected");
						messageName = lexer.getStringValue();
						lexer.nextToken();
						exprList = this.realParameters();
						m = this.currClass.searchMethod(new MethodDec(id, exprList));
						
						if(m == null)
							signalError.showError("method doesn't exist in class \""+this.currClass.getName() + "\" or its superclasses");

					} else if (lexer.token == Symbol.LEFTPAR) {
						// Id "." Id "(" [ ExpressionList ] ")"
						exprList = this.realParameters();
						/*
						 * para fazer as confer�ncias sem�nticas, procure por
						 * m�todo 'ident' na classe de 'firstId'
						 */
						
						
						m = tmpClass.searchPublicMethod(new MethodDec(id, exprList));
						
						if(m == null)
							signalError.showError("method doesn't exist in class \""+tmpClass.getName() + "\" or its superclasses");
						
						return new MessageSendToMethod(new VariableExpr(v), m, exprList);
						
					} else {
						// retorne o objeto da ASA que representa Id "." Id
						
						Variable vInstance = tmpClass.searchInstanceVariable(id);
						if(vInstance == null)
							signalError.showError(tmpClass.getName()+" has no instance variable with name \""+ id +"\"");
						else
							signalError.showError("you cannot access private variable");
						
						return new MessageSendToVariable(new VariableExpr(v),vInstance);
					}
				}
			}
			
			break;
		case THIS:
			/*
			 * Este 'case THIS:' trata os seguintes casos: PrimaryExpr ::=
			 * "this" | "this" "." Id | "this" "." Id "(" [ ExpressionList ] ")"
			 * | "this" "." Id "." Id "(" [ ExpressionList ] ")"
			 */
			lexer.nextToken();
			if (lexer.token != Symbol.DOT) {
				// only 'this'
				// retorne um objeto da ASA que representa 'this'
				// confira se n�o estamos em um m�todo est�tico
				return new MessageSendToSelf(this.currClass);
			} else {
				lexer.nextToken();
				if (lexer.token != Symbol.IDENT)
					signalError.showError("fact dot not ident Identifier expected");
				id = lexer.getStringValue();
				lexer.nextToken();
				// j� analisou "this" "." Id
				if (lexer.token == Symbol.LEFTPAR) {
					// "this" "." Id "(" [ ExpressionList ] ")"
					/*
					 * Confira se a classe corrente possui um m�todo cujo nome �
					 * 'ident' e que pode tomar os par�metros de ExpressionList
					 */
					exprList = this.realParameters();
					
					m = this.currClass.searchMethod(new MethodDec(id, exprList));
					
					if(m == null)
						signalError.showError("method doesn't exist in class \""+this.currClass.getName() + "\" or its superclasses");
					
					return new MessageSendToMethod(new MessageSendToSelf(this.currClass), m, exprList); 
										
				} else if (lexer.token == Symbol.DOT) {
					// "this" "." Id "." Id "(" [ ExpressionList ] ")"
					signalError.showError("Static variables are not permitted");
					lexer.nextToken();
					if (lexer.token != Symbol.IDENT)
						signalError.showError("fact dot Identifier expected");
					lexer.nextToken();
					exprList = this.realParameters();
					m = this.currClass.searchMethod(new MethodDec(id, exprList));
					
					if(m == null)
						signalError.showError("method doesn't exist in class \""+this.currClass.getName() + "\" or its superclasses");
				} else {
					// retorne o objeto da ASA que representa "this" "." Id
					/*
					 * confira se a classe corrente realmente possui uma
					 * vari�vel de inst�ncia 'ident'
					 */
					
					v = this.currClass.searchInstanceVariable(id);
					if(v == null)
						signalError.showError(this.currClass.getName()+" has no variable with name \""+ v.getName() +"\"");
					
					return new MessageSendToVariable(new MessageSendToSelf(this.currClass),v);
				}
			}
			break;
		default:
			signalError.showError("Expression expected");
		}
		return null;
	}

	private LiteralInt literalInt() {

		LiteralInt e = null;

		// the number value is stored in lexer.getToken().value as an object of
		// Integer.
		// Method intValue returns that value as an value of type int.
		int value = lexer.getNumberValue();
		lexer.nextToken();
		return new LiteralInt(value);
	}

	private static boolean startExpr(Symbol token) {

		return token == Symbol.FALSE || token == Symbol.TRUE || token == Symbol.NOT || token == Symbol.THIS
				|| token == Symbol.LITERALINT || token == Symbol.SUPER || token == Symbol.LEFTPAR
				|| token == Symbol.NULL || token == Symbol.IDENT || token == Symbol.LITERALSTRING;

	}

	private SymbolTable symbolTable;
	private KraClass currClass;
	private Lexer lexer;
	private ErrorSignaller signalError;
	
	private Type currentMethodReturnType;
	private Stack<Boolean> whileStack = new Stack<>();

}
