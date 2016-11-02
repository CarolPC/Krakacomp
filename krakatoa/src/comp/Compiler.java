
package comp;

import ast.*;
import lexer.*;
import java.io.*;
import java.util.*;

public class Compiler {

	// ../tests/lex/ ../tests/sin/ ../tests/sem/ ../tests/chk/ ../tests/ger/

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
			// kraClassList.add(classDec());
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

		currentClass.setInstanceVariableList(instanceVarList);
		currentClass.setPrivateMethodList(privateMethodList);
		currentClass.setPublicMethodList(publicMethodList);

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

				if (qualifier == Symbol.PRIVATE) {
					if (className.equals("Program") && name.equals("run"))
						signalError.showError("Method 'run' of class 'Program' cannot be private");

					methodDec(t, name, qualifier, privateMethodList);
				} else {
					if (className.equals("Program") && name.equals("run")) {
						if (t == Type.voidType)
							hasRunMethod = true;
						else
							signalError.showError(
									"Method 'run' of class 'Program' with a return type differente from 'void'");
					}

					methodDec(t, name, qualifier, publicMethodList);
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

		if (lexer.token != Symbol.RIGHTCURBRACKET)
			signalError.showError("'public', 'private', or '}' expected");
		lexer.nextToken();

		return currentClass;
	}

	private void instanceVarDec(Type type, String name, InstanceVariableList instanceVarList) {
		// InstVarDec ::= "private" Type IdList ";"
		InstanceVariable instanceVar = new InstanceVariable(name, type);
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

		if (lexer.token != Symbol.SEMICOLON)
			signalError.show(ErrorSignaller.semicolon_expected);
		lexer.nextToken();
	}

	@SuppressWarnings("unused")
	private void methodDec(Type type, String name, Symbol qualifier, MethodList methodList) {
		/*
		 * MethodDec ::= Qualifier Return Id "("[ FormalParamDec ] ")" "{"
		 * StatementList "}"
		 */

		this.methodHasReturn = false;

		if (currClass.searchInstanceVariable(name) != null)
			signalError.showError("Method '" + name + "' has name equal to an instance variable");

		currentMethodReturnType = type;

		lexer.nextToken();
		ParamList parameterList = null;
		StatementList stmtList = null;

		if (lexer.token != Symbol.LEFTPAR)
			parameterList = formalParamDec();

		if (currClass.getName().equals("Program") && name.equals("run") && parameterList != null
				&& parameterList.getSize() > 0)
			signalError.showError("Method 'run' of class 'Program' cannot take parameters");

		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");

		MethodDec m = new MethodDec(qualifier, type, name, parameterList);

		checkRedeclaration(type, name, parameterList);

		methodList.addElement(m);

		lexer.nextToken();

		if (lexer.token != Symbol.LEFTCURBRACKET)
			signalError.showError("'{' expected");

		lexer.nextToken();
		m.setStatementList(stmtList = statementList());

		if (lexer.token != Symbol.RIGHTCURBRACKET)
			signalError.showError("'}' expected");
		if (currentMethodReturnType != Type.voidType && !this.methodHasReturn)
			signalError.showError("No return statement inside method " + m.getName());

		lexer.nextToken();

		symbolTable.removeLocalIdent();

		currentMethodReturnType = null;

	}

	private void checkRedeclaration(Type type, String name, ParamList parameterList) {
		MethodDec mm = null;
		KraClass whoHasMethod = currClass.searchMethodByName(name, mm);

		if (whoHasMethod == null)
			return;
		// Era para mm ser setado em searchMethodByName, mas está sendo passado como valor
		mm = whoHasMethod.getMethodByName(name);

		if (whoHasMethod.getName().equals(currClass.getName()))
			if (name.equals(mm.getName()))
				signalError.showError("Method '" + name + "' is being redeclared");
		if (!parameterList.equals(mm.getParamList()) || !type.equals(mm.getType())
				)
			signalError.showError("Method '" + name + "' of subclass '" + currClass.getName()
					+ "' has a signature different from method inherited from superclass '" + whoHasMethod.getName()
					+ "'");
	}

	private LocalDecStatement localDec() {
		// LocalDec ::= Type IdList ";"
		LocalDecStatement localDecList = new LocalDecStatement();

		Type type = type();

		do {
			if (lexer.token != Symbol.IDENT)
				signalError.showError("Identifier expected");
			String variableName = lexer.getStringValue();
			if (symbolTable.getInLocal(variableName) != null)
				signalError.showError("ldec Unique identifier expected");

			Variable v = new Variable(variableName, type);

			symbolTable.putInLocal(variableName, v);

			lexer.nextToken();

			if (lexer.token == Symbol.ASSIGN) {
				lexer.nextToken();
				this.insideAssign = true;
				Expr e = expr();

				// TODO verificar subtipo depois
				if (!this.isTypeOrSupertype(v.getType(), e.getType()))
					signalError.showError("Expression in RHS is not same type or subtype of variable in LHS");

				localDecList.addElement(v, e);
				this.insideAssign = false;
			} else {
				localDecList.addElement(v);
			}

			if (lexer.token != Symbol.COMMA)
				break;

			lexer.nextToken();

		} while (true);

		if (lexer.token != Symbol.SEMICOLON)
			signalError.showError("Missing ';'", true);

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

		if (lexer.token != Symbol.LEFTCURBRACKET)
			signalError.showError("{ expected", true);

		lexer.nextToken();
		StatementList stmtList = statementList();
		if (lexer.token != Symbol.RIGHTCURBRACKET)
			signalError.showError("'}' expected");

		lexer.nextToken();

		return stmtList;
	}

	private StatementList statementList() {
		// CompStatement ::= "{" { Statement } "}"
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
		String variableName = null;
		Variable variable = null;

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
				signalError.showError("Identifier expected");
			variableName = lexer.getStringValue();
			variable = symbolTable.getInLocal(variableName);
			if (variable == null)
				signalError.showError("Variable '" + variableName + "' in increment statement is not declared.");
			lexer.nextToken();
			if (lexer.token != Symbol.SEMICOLON)
				signalError.showError("Missing ';'");
			lexer.nextToken();
			stmt = new IncrementStatement(variable);
			break;
		case MINUS:
			lexer.nextToken();
			if (lexer.token != Symbol.MINUS)
				signalError.showError("Missing '-'");
			lexer.nextToken();
			if (lexer.token != Symbol.IDENT)
				signalError.showError("Identifier expected");
			variableName = lexer.getStringValue();
			variable = symbolTable.getInLocal(variableName);
			if (variable == null)
				signalError.showError("Variable '" + variableName + "' in decrement statement is not declared.");
			lexer.nextToken();
			if (lexer.token != Symbol.SEMICOLON)
				signalError.showError("Missing ';'");
			lexer.nextToken();
			stmt = new DecrementStatement(variable);
			break;
		default:
			signalError.showError("stat def Statement expected");
		}

		return stmt;
	}

	private Statement assertStatement() {
		lexer.nextToken();
		int lineNumber = lexer.getLineNumber();
		this.insideAssert = true;
		Expr e = expr();
		if (e.getType() != Type.booleanType)
			signalError.showError("boolean expression expected");
		if (lexer.token != Symbol.COMMA) {
			this.signalError.showError("',' expected after the expression of the 'assert' statement");
		}
		lexer.nextToken();
		this.insideAssert = false;
		if (lexer.token != Symbol.LITERALSTRING) {
			this.signalError.showError("A literal string expected after the ',' of the 'assert' statement");
		}
		String message = lexer.getLiteralStringValue();
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
				(lexer.token == Symbol.IDENT && symbolTable.getInLocal(lexer.getStringValue()) == null
						&& isType(lexer.getStringValue()))) {
			/*
			 * uma declara��o de vari�vel. 'lexer.token' � o tipo da vari�vel
			 *
			 * AssignExprLocalDec ::= Expression [ ``$=$'' Expression ] |
			 * LocalDec LocalDec ::= Type IdList ``;''
			 */
			return localDec();
		} else {
			Expr left = null;
			Expr right = null;

			/*
			 * AssignExprLocalDec ::= Expression [ ``$=$'' Expression ]
			 */
			left = expr();

			if (lexer.token == Symbol.ASSIGN) {
				lexer.nextToken();
				this.insideAssign = true;
				right = expr();

				if (!this.isTypeOrSupertype(left.getType(), right.getType()))
					signalError.showError("RHS is not type or subtype of LHS");

				if (lexer.token != Symbol.SEMICOLON)
					signalError.showError("Missing ';'", true);

				lexer.nextToken();
				this.insideAssign = false;

				return new AssignStatement(left, right);
			}

			if (lexer.token != Symbol.SEMICOLON)
				signalError.showError("Statement expected", true);
			lexer.nextToken();

			return new MessageSendStatement((MessageSend) left);
		}

	}

	private ExprList realParameters() {
		ExprList anExprList = null;
		this.insideMethodCall = true;

		if (lexer.token != Symbol.LEFTPAR)
			signalError.showError("'(' expected");
		lexer.nextToken();
		if (startExpr(lexer.token))
			anExprList = exprList();
		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");

		this.insideMethodCall = false;
		lexer.nextToken();
		return anExprList;
	}

	private WhileStatement whileStatement() {
		whileStack.push(true);

		lexer.nextToken();
		if (lexer.token != Symbol.LEFTPAR)
			signalError.showError("'(' expected");
		lexer.nextToken();
		this.insideWhileCond = true;
		Expr e = expr();
		if (e.getType() != Type.booleanType)
			signalError.showError("boolean expression expected in a while statement");
		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");
		lexer.nextToken();
		this.insideWhileCond = false;
		Statement stmt = statement();

		// Acho que deveria ter um pop aqui, mas quando tem, tem um erro que não
		// é reportado
		// whileStack.pop();

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
		this.insideWhileCond = true;
		lexer.nextToken();
		Expr e = expr();
		if (e.getType() != Type.booleanType)
			signalError.showError("boolean expression expected in a do-while statement");
		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");
		lexer.nextToken();
		this.insideWhileCond = false;

		if (lexer.token != Symbol.SEMICOLON)
			signalError.showError("expected ; after ) in do-while statement", true);

		lexer.nextToken();

		return new DoWhileStatement(e, stmt);
	}

	private IfStatement ifStatement() {

		lexer.nextToken();
		if (lexer.token != Symbol.LEFTPAR)
			signalError.showError("'(' expected");
		lexer.nextToken();
		this.insideIfCond = true;
		Expr e = expr();
		if (e.getType() != Type.booleanType)
			signalError.showError("boolean expression expected in an if statement");
		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");
		lexer.nextToken();
		this.insideIfCond = false;
		Statement ifStmt = statement();
		if (lexer.token == Symbol.ELSE) {
			lexer.nextToken();
			return new IfElseStatement(e, ifStmt, statement());
		}

		return new IfStatement(e, ifStmt);
	}

	private ReturnStatement returnStatement() {

		lexer.nextToken();
		this.insideReturn = true;
		Expr e = expr();
		this.insideReturn = false;

		if (lexer.token != Symbol.SEMICOLON)
			signalError.showError("; expected after return", true);

		if (!this.isTypeOrSupertype(currentMethodReturnType, e.getType()))
			signalError.showError("Illegal 'return' statement. Method doesn't returns a subtype of '"
					+ currentMethodReturnType.getName() + "'");

		lexer.nextToken();

		this.methodHasReturn = true;

		return new ReturnStatement(e);
	}

	private ReadStatement readStatement() {
		this.insideRead = true;

		lexer.nextToken();
		if (lexer.token != Symbol.LEFTPAR)
			signalError.showError("'(' expected after 'read' command");
		lexer.nextToken();
		ExprList el = exprList();
		for (Expr e : el.getList())
			if (e instanceof LiteralBoolean || e.getType() == Type.booleanType)
				signalError.showError("Command 'read' does not accept 'boolean' variables");
			else if (!(e instanceof VariableExpr) && (e instanceof LiteralInt || e instanceof LiteralString))
				signalError.showError("Command 'read' expects a variable");

		if (lexer.token != Symbol.RIGHTPAR)
			signalError.showError("')' expected");
		lexer.nextToken();
		if (lexer.token != Symbol.SEMICOLON)
			signalError.showError("; expected in read statement", true);
		lexer.nextToken();

		this.insideRead = false;

		return new ReadStatement(el);
	}

	private WriteStatement writeStatement() {
		this.insideWrite = true;

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
			signalError.showError("; expected in write statement", true);
		lexer.nextToken();

		this.insideWrite = false;

		return new WriteStatement(el);
	}

	private WriteLineStatement writelnStatement() {

		this.insideWrite = true;
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
			signalError.showError("; expected in writeln statement", true);
		lexer.nextToken();

		this.insideWrite = false;
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
		ExprList anExprList = new ExprList();

		anExprList.addElement(expr());
		while (lexer.token == Symbol.COMMA) {
			lexer.nextToken();
			anExprList.addElement(expr());
		}

		return anExprList;
	}

	private Expr expr() {
		Expr left = simpleExpr();

		Symbol op = lexer.token;
		if (op == Symbol.EQ || op == Symbol.NEQ || op == Symbol.LE || op == Symbol.LT || op == Symbol.GE
				|| op == Symbol.GT) {
			lexer.nextToken();

			Symbol opAux = lexer.token;
			if (opAux == Symbol.EQ || opAux == Symbol.NEQ || opAux == Symbol.LE || opAux == Symbol.LT
					|| opAux == Symbol.GE || opAux == Symbol.GT)
				signalError.showError("Expression expected OR invalid sequence of symbols");

			Expr right = simpleExpr();

			// TODO Não funciona bem para Strings e subtipos
			if ((op == Symbol.EQ || op == Symbol.NEQ) && !this.isTypeOrSupertype(left.getType(), right.getType())
					&& !this.isTypeOrSupertype(right.getType(), left.getType())) {
				signalError.showError("Incompatibles types cannot be compared with '" + op
						+ "' because the result will always be 'false'");
			}

			left = new CompositeExpr(left, op, right);
		}

		return left;
	}

	private Expr simpleExpr() {
		Symbol op;

		Expr left = term();
		while ((op = lexer.token) == Symbol.MINUS || op == Symbol.PLUS || op == Symbol.OR) {
			lexer.nextToken();
			Expr right = term();

			if (left.getType() != right.getType())
				signalError.showError("operator '" + op + "' of '" + left.getType().getName() + "' expects an '"
						+ right.getType().getName() + "' value");
			if (left.getType() == Type.booleanType && op != Symbol.OR)
				signalError.showError("type boolean does not supports operation '" + op + "'");
			left = new CompositeExpr(left, op, right);
		}

		return left;
	}

	private Expr term() {
		Symbol op;

		Expr left = signalFactor();
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
				signalError.showError(
						"'super' used in class '" + currClass.getName() + "' that does not have a superclass");
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
			m = this.currClass.getSuperclass().searchPublicMethod(new MethodDec(messageName, exprList));

			if (m == null)
				signalError.showError("method \"" + messageName + "\" doesn't exist on superclass or its superclasses");

			if (m.getType() != Type.voidType && !this.insideAssign && !this.insideReturn && !this.insideMethodCall
					&& !this.insideRead && !this.insideWrite && !this.insideWhileCond && !this.insideIfCond
					&& !this.insideAssert)
				signalError.showError("Message send 'super." + m.getName()
						+ "' returns a value that is not used (comp.Compiler.statement())");
			return new MessageSendToMethod(new MessageSendToSuper(this.currClass.getSuperclass()), m, exprList);

		case IDENT:
			/*
			 * PrimaryExpr ::= Id | Id "." Id | Id "." Id "(" [ ExpressionList ]
			 * ")" | Id "." Id "." Id "(" [ ExpressionList ] ")" |
			 */
			String firstId = lexer.getStringValue();
			v = symbolTable.getInLocal(firstId);

			if (v == null) {
				v = this.currClass.searchInstanceVariable(firstId);

				if (v == null)
					signalError.showError("variable \"" + firstId + "\" was not declared in this scope.");
				else
					signalError.showError("variable \"" + firstId + "\" is a private variable.");

			}

			lexer.nextToken();

			if (lexer.token != Symbol.DOT) {
				// Id
				// retorne um objeto da ASA que representa um identificador
				return new VariableExpr(v);

			} else { // Id "."

				tmpClass = symbolTable.getInGlobal(v.getType().getName());

				if (tmpClass == null)
					signalError.showError(firstId + " is not a class instance");

				lexer.nextToken(); // coma o "."

				if (lexer.token != Symbol.IDENT) {
					signalError.showError("fact not id Identifier expected");
				} else {
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

						if (m == null)
							signalError.showError("method doesn't exist in class \"" + this.currClass.getName()
									+ "\" or its superclasses");

					} else if (lexer.token == Symbol.LEFTPAR) {
						// Id "." Id "(" [ ExpressionList ] ")"
						exprList = this.realParameters();
						/*
						 * para fazer as confer�ncias sem�nticas, procure por
						 * m�todo 'ident' na classe de 'firstId'
						 */

						m = tmpClass.searchPublicMethod(new MethodDec(id, exprList));

						if (m == null)
							signalError.showError(
									"method doesn't exist in class \"" + tmpClass.getName() + "\" or its superclasses");

						if ((m.getType() != Type.voidType) && !this.insideAssign && !this.insideReturn
								&& !this.insideMethodCall && !this.insideRead && !this.insideWrite
								&& !this.insideWhileCond && !this.insideIfCond && !this.insideAssert)
							signalError.showError("Message send '" + id + "." + m.getName()
									+ "' returns a value that is not used (comp.Compiler.statement())");

						return new MessageSendToMethod(new VariableExpr(v), m, exprList);

					} else {
						// retorne o objeto da ASA que representa Id "." Id

						Variable vInstance = tmpClass.searchInstanceVariable(id);
						if (vInstance == null)
							signalError.showError(
									tmpClass.getName() + " has no instance variable with name \"" + id + "\"");
						else
							signalError.showError("you cannot access private variable");

						return new MessageSendToVariable(new VariableExpr(v), vInstance);
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

					if (m == null)
						signalError.showError("method doesn't exist in class \"" + this.currClass.getName()
								+ "\" or its superclasses");

					if (m.getType() != Type.voidType && !this.insideAssign && !this.insideReturn
							&& !this.insideMethodCall && !this.insideRead && !this.insideWrite && !this.insideWhileCond
							&& !this.insideIfCond && !this.insideAssert)
						signalError.showError("Message send 'this." + m.getName()
								+ "' returns a value that is not used (comp.Compiler.statement())");
					return new MessageSendToMethod(new MessageSendToSelf(this.currClass), m, exprList);

				} else if (lexer.token == Symbol.DOT) {
					// "this" "." Id "." Id "(" [ ExpressionList ] ")"
					Variable iv = this.currClass.searchInstanceVariable(id);
					if (iv == null)
						signalError.showError(currClass.getName() + " hasn't instance name " + id);
					KraClass ivClass = symbolTable.getInGlobal(iv.getType().getName());
					if (ivClass == null)
						signalError.showError(iv.getName() + " is not an instance variable");

					lexer.nextToken();
					if (lexer.token != Symbol.IDENT)
						signalError.showError("fact dot Identifier expected");
					String id2 = lexer.getStringValue();
					lexer.nextToken();
					exprList = this.realParameters();
					m = ivClass.searchMethod(new MethodDec(id2, exprList));

					if (m == null)
						signalError.showError("method doesn't exist in class \"" + this.currClass.getName()
								+ "\" or its superclasses");

					if (m.getType() != Type.voidType && !this.insideAssign && !this.insideReturn
							&& !this.insideMethodCall && !this.insideRead && !this.insideWrite && !this.insideWhileCond
							&& !this.insideIfCond && !this.insideAssert)
						signalError.showError("Message send 'this." + id + "." + id2 + "." + m.getName()
								+ "' returns a value that is not used (comp.Compiler.statement())");
					return new MessageSendToMethod(new MessageSendToVariable(new MessageSendToSelf(currClass), iv), m,
							exprList);
				} else {
					// retorne o objeto da ASA que representa "this" "." Id
					/*
					 * confira se a classe corrente realmente possui uma
					 * vari�vel de inst�ncia 'ident'
					 */

					v = this.currClass.searchInstanceVariable(id);
					if (v == null)
						signalError.showError(this.currClass.getName() + " has no variable with name \"" + id + "\"");

					return new MessageSendToVariable(new MessageSendToSelf(this.currClass), v);
				}
			}

		default:
			signalError.showError("Expression expected");
		}
		return null;
	}

	private LiteralInt literalInt() {
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

	// lê-se a isTypeOrSupertype of b
	private boolean isTypeOrSupertype(Type a, Type b) {

		if (a == b)
			return true;

		if ((this.symbolTable.getInGlobal(a.getName()) != null || a == Type.stringType) && b == Type.nullType)
			return true;

		KraClass c = this.symbolTable.getInGlobal(b.getName());

		if (c == null)
			return false;

		while ((c = c.getSuperclass()) != null)
			if (a == c)
				return true;

		return false;
	}

	private SymbolTable symbolTable;
	private KraClass currClass;
	private Lexer lexer;
	private ErrorSignaller signalError;

	private Type currentMethodReturnType;
	private boolean methodHasReturn = false;
	private boolean insideAssign = false;
	private boolean insideReturn = false;
	private boolean insideMethodCall = false;
	private boolean insideRead = false;
	private boolean insideWrite = false;
	private boolean insideWhileCond = false;
	private boolean insideIfCond = false;
	private boolean insideAssert = false;
	private Stack<Boolean> whileStack = new Stack<>();

}
