package com.mindlin.jsast.impl.tree;

import com.mindlin.jsast.fs.SourcePosition;
import com.mindlin.jsast.tree.ExpressionTree;
import com.mindlin.jsast.tree.ReturnTree;

public class ReturnTreeImpl extends AbstractExpressiveStatementTree implements ReturnTree {
	
	public ReturnTreeImpl(SourcePosition start, SourcePosition end, ExpressionTree expression) {
		super(start, end, expression);
	}
	
	/**
	 * For wrapping expression lambdas
	 * 
	 * @param expression
	 */
	public ReturnTreeImpl(ExpressionTree expression) {
		this(expression.getStart(), expression.getEnd(), expression);
	}
}
