package com.mindlin.jsast.impl.tree;

import com.mindlin.jsast.fs.SourcePosition;
import com.mindlin.jsast.tree.ExpressionTree;
import com.mindlin.jsast.tree.UnaryTree;

public class UnaryTreeImpl extends AbstractExpressiveExpressionTree implements UnaryTree {
	protected final Kind kind;
	
	public UnaryTreeImpl(SourcePosition start, SourcePosition end, ExpressionTree expression, Kind kind) {
		super(start, end, expression);
		this.kind = kind;
	}
	
	@Override
	public Kind getKind() {
		return this.kind;
	}
}
