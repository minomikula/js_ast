package com.mindlin.nautilus.impl.tree;

import java.util.List;
import java.util.Objects;

import com.mindlin.nautilus.fs.SourcePosition;
import com.mindlin.nautilus.tree.ExpressionTree;
import com.mindlin.nautilus.tree.TemplateElementTree;
import com.mindlin.nautilus.tree.TemplateLiteralTree;

public class TemplateLiteralTreeImpl extends AbstractTree implements TemplateLiteralTree {
	protected final List<TemplateElementTree> quasis;
	
	protected final List<ExpressionTree> expressions;
	
	public TemplateLiteralTreeImpl(SourcePosition start, SourcePosition end, List<TemplateElementTree> quasis, List<ExpressionTree> expressions) {
		super(start, end);
		this.quasis = quasis;
		this.expressions = expressions;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TemplateElementTree> getQuasis() {
		return this.quasis;
	}

	@Override
	public List<ExpressionTree> getExpressions() {
		return this.expressions;
	}
	
	@Override
	protected int hash() {
		return Objects.hash(getKind(), getQuasis(), getExpressions());
	}
}
