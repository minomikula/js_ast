package com.mindlin.jsast.impl.tree;

import java.util.List;
import java.util.Objects;

import com.mindlin.jsast.fs.SourcePosition;
import com.mindlin.jsast.tree.ClassPropertyTree;
import com.mindlin.jsast.tree.ClassTreeBase.ClassDeclarationTree;
import com.mindlin.jsast.tree.IdentifierTree;
import com.mindlin.jsast.tree.type.GenericParameterTree;
import com.mindlin.jsast.tree.type.TypeTree;

public class ClassDeclarationTreeImpl extends AbstractTree implements ClassDeclarationTree {
	protected final IdentifierTree name;
	protected final TypeTree superType;
	protected final boolean isAbstract;
	protected final List<TypeTree> implementing;
	protected final List<ClassPropertyTree<?>> properties;
	protected final List<GenericParameterTree> generics;
	
	public ClassDeclarationTreeImpl(SourcePosition start, SourcePosition end, boolean isAbstract, IdentifierTree name, List<GenericParameterTree> generics, TypeTree superType, List<TypeTree> implementing, List<ClassPropertyTree<?>> properties) {
		super(start, end);
		this.isAbstract = isAbstract;
		this.name = name;
		this.generics = generics;
		this.superType = superType;
		this.implementing = implementing;
		this.properties = properties;
	}
	
	@Override
	public IdentifierTree getIdentifier() {
		return name;
	}
	
	@Override
	public TypeTree getSuperType() {
		return superType;
	}
	
	@Override
	public List<TypeTree> getImplementing() {
		return implementing;
	}
	
	@Override
	public List<ClassPropertyTree<?>> getProperties() {
		return properties;
	}

	@Override
	public boolean isAbstract() {
		return isAbstract;
	}

	@Override
	public List<GenericParameterTree> getGenerics() {
		return this.generics;
	}
	
	@Override
	protected int hash() {
		return Objects.hash(getKind(), isAbstract(), getIdentifier(), getGenerics(), getSuperType(), getImplementing(), getProperties());
	}
}
