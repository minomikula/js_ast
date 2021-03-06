package com.mindlin.nautilus.tree;

/**
 * Object literal assignment property, in form of 
 * {@code key: value}
 * 
 * @author mailmindlin
 */
public interface AssignmentPropertyTree extends PropertyTree, ObjectLiteralElement, UnvisitableTree {
	ExpressionTree getInitializer();
	
	@Override
	default Kind getKind() {
		return Kind.ASSIGNMENT_PROPERTY;
	}
}
