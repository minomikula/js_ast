package com.mindlin.nautilus.tree;

public interface ShorthandAssignmentPropertyTree extends AssignmentPropertyTree {
	@Override
	IdentifierTree getName();
	
	@Override
	default Kind getKind() {
		return Kind.SHORTHAND_ASSIGNMENT_PROPERTY;
	}
}
