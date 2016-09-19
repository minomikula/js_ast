package com.mindlin.jsast.tree;

import java.util.List;

public interface FunctionExpressionTree extends ExpressionTree {
	StatementTree getBody();

	IdentifierTree getName();

	List<ParameterTree> getParameters();

	boolean isStrict();
	
	boolean isArrow();
	
	boolean isGenerator();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.FUNCTION_EXPRESSION;
	}
}
