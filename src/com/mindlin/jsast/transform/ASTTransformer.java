package com.mindlin.jsast.transform;

import java.util.ArrayList;

import com.mindlin.jsast.impl.tree.ArrayLiteralTreeImpl;
import com.mindlin.jsast.impl.tree.AssignmentTreeImpl;
import com.mindlin.jsast.impl.tree.BinaryTreeImpl;
import com.mindlin.jsast.impl.tree.BinaryTypeTree;
import com.mindlin.jsast.impl.tree.BlockTreeImpl;
import com.mindlin.jsast.impl.tree.CastTreeImpl;
import com.mindlin.jsast.impl.tree.CompilationUnitTreeImpl;
import com.mindlin.jsast.impl.tree.ConditionalExpressionTreeImpl;
import com.mindlin.jsast.impl.tree.DoWhileLoopTreeImpl;
import com.mindlin.jsast.impl.tree.ExpressionStatementTreeImpl;
import com.mindlin.jsast.impl.tree.ForLoopTreeImpl;
import com.mindlin.jsast.impl.tree.LabeledStatementTreeImpl;
import com.mindlin.jsast.impl.tree.MemberTypeTreeImpl;
import com.mindlin.jsast.impl.tree.ParenthesizedTreeImpl;
import com.mindlin.jsast.impl.tree.ReturnTreeImpl;
import com.mindlin.jsast.impl.tree.SequenceTreeImpl;
import com.mindlin.jsast.impl.tree.ThrowTreeImpl;
import com.mindlin.jsast.impl.tree.TupleTypeTreeImpl;
import com.mindlin.jsast.impl.tree.UnaryTreeImpl;
import com.mindlin.jsast.impl.tree.VariableDeclarationTreeImpl;
import com.mindlin.jsast.impl.tree.VariableDeclaratorTreeImpl;
import com.mindlin.jsast.impl.tree.WhileLoopTreeImpl;
import com.mindlin.jsast.impl.tree.WithTreeImpl;
import com.mindlin.jsast.tree.ArrayLiteralTree;
import com.mindlin.jsast.tree.ArrayPatternTree;
import com.mindlin.jsast.tree.AssignmentPatternTree;
import com.mindlin.jsast.tree.AssignmentTree;
import com.mindlin.jsast.tree.BinaryTree;
import com.mindlin.jsast.tree.BlockTree;
import com.mindlin.jsast.tree.BooleanLiteralTree;
import com.mindlin.jsast.tree.BreakTree;
import com.mindlin.jsast.tree.CastTree;
import com.mindlin.jsast.tree.ClassDeclarationTree;
import com.mindlin.jsast.tree.CommentNode;
import com.mindlin.jsast.tree.CompilationUnitTree;
import com.mindlin.jsast.tree.ComputedPropertyKeyTree;
import com.mindlin.jsast.tree.ConditionalExpressionTree;
import com.mindlin.jsast.tree.ContinueTree;
import com.mindlin.jsast.tree.DebuggerTree;
import com.mindlin.jsast.tree.DoWhileLoopTree;
import com.mindlin.jsast.tree.EmptyStatementTree;
import com.mindlin.jsast.tree.EnumDeclarationTree;
import com.mindlin.jsast.tree.ExportTree;
import com.mindlin.jsast.tree.ExpressionStatementTree;
import com.mindlin.jsast.tree.ExpressionTree;
import com.mindlin.jsast.tree.ForEachLoopTree;
import com.mindlin.jsast.tree.ForLoopTree;
import com.mindlin.jsast.tree.FunctionCallTree;
import com.mindlin.jsast.tree.FunctionExpressionTree;
import com.mindlin.jsast.tree.IdentifierTree;
import com.mindlin.jsast.tree.IfTree;
import com.mindlin.jsast.tree.ImportTree;
import com.mindlin.jsast.tree.InterfaceDeclarationTree;
import com.mindlin.jsast.tree.LabeledStatementTree;
import com.mindlin.jsast.tree.NewTree;
import com.mindlin.jsast.tree.NullLiteralTree;
import com.mindlin.jsast.tree.NumericLiteralTree;
import com.mindlin.jsast.tree.ObjectLiteralTree;
import com.mindlin.jsast.tree.ObjectPatternTree;
import com.mindlin.jsast.tree.ObjectPropertyKeyTree;
import com.mindlin.jsast.tree.ParenthesizedTree;
import com.mindlin.jsast.tree.PatternTree;
import com.mindlin.jsast.tree.RegExpLiteralTree;
import com.mindlin.jsast.tree.ReturnTree;
import com.mindlin.jsast.tree.SequenceTree;
import com.mindlin.jsast.tree.StatementTree;
import com.mindlin.jsast.tree.StringLiteralTree;
import com.mindlin.jsast.tree.SuperExpressionTree;
import com.mindlin.jsast.tree.SwitchTree;
import com.mindlin.jsast.tree.TemplateLiteralTree;
import com.mindlin.jsast.tree.ThisExpressionTree;
import com.mindlin.jsast.tree.ThrowTree;
import com.mindlin.jsast.tree.Tree;
import com.mindlin.jsast.tree.TryTree;
import com.mindlin.jsast.tree.TypeAliasTree;
import com.mindlin.jsast.tree.UnaryTree;
import com.mindlin.jsast.tree.VariableDeclarationTree;
import com.mindlin.jsast.tree.VariableDeclaratorTree;
import com.mindlin.jsast.tree.WhileLoopTree;
import com.mindlin.jsast.tree.WithTree;
import com.mindlin.jsast.tree.type.ArrayTypeTree;
import com.mindlin.jsast.tree.type.FunctionTypeTree;
import com.mindlin.jsast.tree.type.GenericRefTypeTree;
import com.mindlin.jsast.tree.type.GenericTypeTree;
import com.mindlin.jsast.tree.type.IdentifierTypeTree;
import com.mindlin.jsast.tree.type.IndexTypeTree;
import com.mindlin.jsast.tree.type.InterfaceTypeTree;
import com.mindlin.jsast.tree.type.IntersectionTypeTree;
import com.mindlin.jsast.tree.type.MemberTypeTree;
import com.mindlin.jsast.tree.type.ParameterTypeTree;
import com.mindlin.jsast.tree.type.SpecialTypeTree;
import com.mindlin.jsast.tree.type.TupleTypeTree;
import com.mindlin.jsast.tree.type.TypeTree;
import com.mindlin.jsast.tree.type.UnionTypeTree;

public class ASTTransformer<D> implements TreeTransformation<D> {
	TreeTransformation<D> transformation;
	
	public ASTTransformer(TreeTransformation<D> transformation) {
		this.transformation = transformation;
	}
	
	@Override
	public ExpressionTree visitArrayLiteral(ArrayLiteralTree node, D ctx) {
		ArrayList<ExpressionTree> elements = new ArrayList<>();
		boolean modified = false;
		for (ExpressionTree element : node.getElements()) {
			ExpressionTree newElement = (ExpressionTree) element.accept(this, ctx);
			modified |= newElement != element;
			elements.add(newElement);
		}
		
		if (modified)
			node = new ArrayLiteralTreeImpl(node.getStart(), node.getEnd(), elements);
		
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public PatternTree visitArrayPattern(ArrayPatternTree node, D ctx) {
		// TODO Auto-generated method stub
		return (PatternTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public TypeTree visitArrayType(ArrayTypeTree node, D ctx) {
		// TODO Auto-generated method stub
		return (TypeTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitAssignment(AssignmentTree node, D ctx) {
		ExpressionTree oldLHS = node.getLeftOperand();
		ExpressionTree oldRHS = node.getRightOperand();
		
		ExpressionTree newLHS = (ExpressionTree) oldLHS.accept(this, ctx);
		ExpressionTree newRHS = (ExpressionTree) oldRHS.accept(this, ctx);
		
		if (oldLHS != newLHS || oldRHS != newRHS)
			node = new AssignmentTreeImpl(node.getStart(), node.getEnd(), node.getKind(), newLHS, newRHS);
		
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitBinary(BinaryTree node, D ctx) {
		ExpressionTree oldLHS = node.getLeftOperand();
		ExpressionTree oldRHS = node.getRightOperand();
		
		ExpressionTree newLHS = (ExpressionTree) oldLHS.accept(this, ctx);
		ExpressionTree newRHS = (ExpressionTree) oldRHS.accept(this, ctx);
		
		// TODO fix for subtypes of BinaryTree
		if (oldLHS != newLHS || oldRHS != newRHS)
			node = new BinaryTreeImpl(node.getStart(), node.getEnd(), node.getKind(), newLHS, newRHS);
		
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitBlock(BlockTree node, D ctx) {
		ArrayList<StatementTree> statements = new ArrayList<>();
		boolean modified = false;
		for (StatementTree statement : node.getStatements()) {
			StatementTree newStatement = (StatementTree) statement.accept(this, ctx);
			modified |= newStatement != statement;
			statements.add(newStatement);
		}
		
		if (modified)
			node = new BlockTreeImpl(node.getStart(), node.getEnd(), statements);
		
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitBooleanLiteral(BooleanLiteralTree node, D ctx) {
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitBreak(BreakTree node, D ctx) {
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitCast(CastTree node, D ctx) {
		ExpressionTree oldExpr = node.getExpression();
		TypeTree oldType = node.getType();
		
		ExpressionTree newExpr = (ExpressionTree) oldExpr.accept(this, ctx);
		TypeTree newType = (TypeTree) oldType.accept(this, ctx);
		
		if (oldExpr != newExpr || oldType != newType)
			node = new CastTreeImpl(node.getStart(), node.getEnd(), newExpr, newType);
		
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public Tree visitClassDeclaration(ClassDeclarationTree node, D ctx) {
		// TODO finish
		return node.accept(this.transformation, ctx);
	}
	
	@Override
	public CommentNode visitComment(CommentNode node, D ctx) {
		return (CommentNode) node.accept(this.transformation, ctx);
	}
	
	@Override
	public CompilationUnitTree visitCompilationUnit(CompilationUnitTree node, D ctx) {
		boolean modified = false;
		ArrayList<StatementTree> statements = new ArrayList<>();
		for (StatementTree statement : node.getSourceElements()) {
			StatementTree stmt = (StatementTree) statement.accept(this, ctx);
			statements.add(stmt);
			modified |= stmt != statement;
		}
		
		if (modified)
			node = new CompilationUnitTreeImpl(node.getStart(), node.getEnd(), node.getSourceFile(), node.getLineMap(),
					statements, node.isStrict());
		
		return (CompilationUnitTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ObjectPropertyKeyTree visitComputedPropertyKey(ComputedPropertyKeyTree node, D ctx) {
		// TODO finish
		return (ObjectPropertyKeyTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitConditionalExpression(ConditionalExpressionTree node, D ctx) {
		ExpressionTree oldCondition = node.getCondition();
		ExpressionTree oldTrueExprn = node.getTrueExpression();
		ExpressionTree oldFalseExpr = node.getFalseExpression();
		
		ExpressionTree newCondition = (ExpressionTree) oldCondition.accept(this, ctx);
		ExpressionTree newTrueExprn = (ExpressionTree) oldTrueExprn.accept(this, ctx);
		ExpressionTree newFalseExpr = (ExpressionTree) oldFalseExpr.accept(this, ctx);
		
		if (newCondition != oldCondition || newTrueExprn != oldTrueExprn || newFalseExpr != oldFalseExpr)
			node = new ConditionalExpressionTreeImpl(node.getStart(), node.getEnd(), newCondition, newTrueExprn,
					newFalseExpr);
		
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitDoWhileLoop(DoWhileLoopTree node, D ctx) {
		ExpressionTree oldCondition = node.getCondition();
		StatementTree oldStatement = node.getStatement();
		
		ExpressionTree newCondition = (ExpressionTree) oldCondition.accept(this, ctx);
		StatementTree newStatement = (StatementTree) oldStatement.accept(this, ctx);
		
		if (oldCondition != newCondition || oldStatement != newStatement)
			node = new DoWhileLoopTreeImpl(node.getStart(), node.getEnd(), newStatement, newCondition);
		
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitEmptyStatement(EmptyStatementTree node, D ctx) {
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitEnumDeclaration(EnumDeclarationTree node, D ctx) {
		// TODO finish
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitExport(ExportTree node, D ctx) {
		// TODO finish
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitExpressionStatement(ExpressionStatementTree node, D ctx) {
		ExpressionTree expr0 = node.getExpression();
		ExpressionTree expr1 = (ExpressionTree) expr0.accept(this, ctx);
		if (expr0 != expr1)
			node = new ExpressionStatementTreeImpl(node.getStart(), node.getEnd(), expr1);
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public TypeTree visitIntersectionType(IntersectionTypeTree node, D ctx) {
		TypeTree oldLeftType = node.getLeftType();
		TypeTree oldRightType = node.getRightType();
		
		TypeTree newLeftType = (TypeTree) oldLeftType.accept(this, ctx);
		TypeTree newRightType = (TypeTree) oldRightType.accept(this, ctx);
		
		if (newLeftType != oldLeftType || newRightType != oldRightType)
			node = new BinaryTypeTree(node.getStart(), node.getEnd(), node.isImplicit(), newLeftType, node.getKind(),
					newRightType);
		
		return (TypeTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitLabeledStatement(LabeledStatementTree node, D ctx) {
		StatementTree oldStmt = node.getStatement();
		StatementTree newStmt = (StatementTree) oldStmt.accept(this, ctx);
		
		if (oldStmt != newStmt)
			node = new LabeledStatementTreeImpl(node.getStart(), node.getEnd(), node.getName(), newStmt);
		
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitParentheses(ParenthesizedTree node, D ctx) {
		ExpressionTree oldExpr = node.getExpression();
		ExpressionTree newExpr = (ExpressionTree) oldExpr.accept(this, ctx);
		
		if (oldExpr != newExpr)
			node = new ParenthesizedTreeImpl(node.getStart(), node.getEnd(), newExpr);
		
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitRegExpLiteral(RegExpLiteralTree node, D ctx) {
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitReturn(ReturnTree node, D ctx) {
		ExpressionTree oldExpr = node.getExpression();
		ExpressionTree newExpr = oldExpr == null ? null : (ExpressionTree) oldExpr.accept(this, ctx);
		
		if (oldExpr != newExpr)
			node = new ReturnTreeImpl(node.getStart(), node.getEnd(), newExpr);
		
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitSequence(SequenceTree node, D ctx) {
		boolean modified = false;
		ArrayList<ExpressionTree> expressions = new ArrayList<>(node.getExpressions().size());
		for (ExpressionTree expression : node.getExpressions()) {
			ExpressionTree newExpression = (ExpressionTree) expression.accept(this, ctx);
			
			modified |= newExpression != expression;
			
			expressions.add(newExpression);
		}
		
		if (modified) {
			expressions.trimToSize();
			node = new SequenceTreeImpl(node.getStart(), node.getEnd(), expressions);
		}
		
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitStringLiteral(StringLiteralTree node, D ctx) {
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitSuper(SuperExpressionTree node, D ctx) {
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitSwitch(SwitchTree node, D ctx) {
		// TODO finish
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitTemplateLiteral(TemplateLiteralTree node, D ctx) {
		// TODO finish
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitThis(ThisExpressionTree node, D ctx) {
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitThrow(ThrowTree node, D ctx) {
		ExpressionTree oldExpression = node.getExpression();
		ExpressionTree newExpression = (ExpressionTree) oldExpression.accept(this, ctx);
		
		if (oldExpression != newExpression)
			node = new ThrowTreeImpl(node.getStart(), node.getEnd(), newExpression);
		
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitTry(TryTree node, D ctx) {
		// TODO finish
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitTypeAlias(TypeAliasTree node, D ctx) {
		// TODO finish
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public TypeTree visitTupleType(TupleTypeTree node, D ctx) {
		boolean modified = false;
		ArrayList<TypeTree> slots = new ArrayList<>();
		for (TypeTree slot : node.getSlotTypes()) {
			TypeTree newSlot = (TypeTree) slot.accept(this, ctx);
			if (newSlot != slot)
				modified = true;
			slots.add(newSlot);
		}
		
		if (modified)
			node = new TupleTypeTreeImpl(node.getStart(), node.getEnd(), node.isImplicit(), slots);
		
		return (TypeTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitUnary(UnaryTree node, D ctx) {
		ExpressionTree oldExpr = node.getExpression();
		ExpressionTree newExpr = (ExpressionTree) oldExpr.accept(this, ctx);
		
		if (newExpr != oldExpr)
			node = new UnaryTreeImpl(node.getStart(), node.getEnd(), newExpr, node.getKind());
		
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public TypeTree visitUnionType(UnionTypeTree node, D ctx) {
		TypeTree oldLeftType = node.getLeftType();
		TypeTree oldRightType = node.getRightType();
		
		TypeTree newLeftType = (TypeTree) oldLeftType.accept(this, ctx);
		TypeTree newRightType = (TypeTree) oldRightType.accept(this, ctx);
		
		if (newLeftType != oldLeftType || newRightType != oldRightType)
			node = new BinaryTypeTree(node.getStart(), node.getEnd(), node.isImplicit(), newLeftType, node.getKind(),
					newRightType);
		
		return (TypeTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitVariableDeclaration(VariableDeclarationTree node, D ctx) {
		boolean modified = false;
		ArrayList<VariableDeclaratorTree> declarations = new ArrayList<>();
		for (VariableDeclaratorTree declarator : node.getDeclarations()) {
			PatternTree oldIdentifier = declarator.getIdentifier();
			TypeTree oldType = declarator.getType();
			ExpressionTree oldInitializer = declarator.getIntitializer();
			
			PatternTree newIdentifier = (PatternTree) oldIdentifier.accept(this, ctx);
			TypeTree newType = (TypeTree) oldType.accept(this, ctx);
			ExpressionTree newInitaializer = oldInitializer == null ? null
					: (ExpressionTree) oldInitializer.accept(this, ctx);
			
			if (newIdentifier != oldIdentifier || newType != oldType || newInitaializer != oldInitializer) {
				declarator = new VariableDeclaratorTreeImpl(declarator.getStart(), declarator.getEnd(), newIdentifier,
						newType, newInitaializer);
				modified = true;
			}
			
			declarations.add(declarator);
		}
		
		if (modified)
			node = new VariableDeclarationTreeImpl(node.getStart(), node.getEnd(), node.isScoped(), node.isConst(),
					declarations);
		
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitWhileLoop(WhileLoopTree node, D ctx) {
		ExpressionTree oldCondition = node.getCondition();
		StatementTree oldStatement = node.getStatement();
		
		ExpressionTree newCondition = (ExpressionTree) oldCondition.accept(this, ctx);
		StatementTree newStatement = (StatementTree) oldStatement.accept(this, ctx);
		
		if (oldCondition != newCondition || oldStatement != newStatement)
			node = new WhileLoopTreeImpl(node.getStart(), node.getEnd(), newCondition, newStatement);
		
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitAssignmentPattern(AssignmentPatternTree node, D ctx) {
		// TODO Auto-generated method stub
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitContinue(ContinueTree node, D ctx) {
		// TODO Auto-generated method stub
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitDebugger(DebuggerTree node, D ctx) {
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitForEachLoop(ForEachLoopTree node, D ctx) {
		// TODO Auto-generated method stub
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitForLoop(ForLoopTree node, D ctx) {
		StatementTree oldInitializer = node.getInitializer();
		StatementTree newInitializer = (StatementTree) oldInitializer.accept(this, ctx);
		ExpressionTree oldCondition = node.getCondition();
		ExpressionTree newCondition = oldCondition == null ? null : (ExpressionTree) oldCondition.accept(this, ctx);
		ExpressionTree oldUpdate = node.getUpdate();
		ExpressionTree newUpdate = oldUpdate == null ? null : (ExpressionTree) oldUpdate.accept(this, ctx);
		StatementTree oldStatement = node.getStatement();
		StatementTree newStatement = (StatementTree) oldStatement.accept(this, ctx);
		
		if (oldInitializer != newInitializer || oldCondition != newCondition || oldUpdate != newUpdate || oldStatement != newStatement)
			node = new ForLoopTreeImpl(node.getStart(), node.getEnd(), newInitializer, newCondition, newUpdate, newStatement);
		
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitFunctionCall(FunctionCallTree node, D ctx) {
		// TODO Auto-generated method stub
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitFunctionExpression(FunctionExpressionTree node, D ctx) {
		// TODO Auto-generated method stub
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public TypeTree visitFunctionType(FunctionTypeTree node, D ctx) {
		// TODO Auto-generated method stub
		return (TypeTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public TypeTree visitGenericRefType(GenericRefTypeTree node, D ctx) {
		// TODO Auto-generated method stub
		return (TypeTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public TypeTree visitGenericType(GenericTypeTree node, D ctx) {
		// TODO Auto-generated method stub
		return (TypeTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public Tree visitIdentifier(IdentifierTree node, D ctx) {
		// TODO Auto-generated method stub
		return node.accept(this.transformation, ctx);
	}
	
	@Override
	public TypeTree visitIdentifierType(IdentifierTypeTree node, D ctx) {
		// TODO Auto-generated method stub
		return (TypeTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitIf(IfTree node, D ctx) {
		// TODO Auto-generated method stub
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitImport(ImportTree node, D ctx) {
		// TODO Auto-generated method stub
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public TypeTree visitIndexType(IndexTypeTree node, D ctx) {
		// TODO Auto-generated method stub
		return (TypeTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitInterfaceDeclaration(InterfaceDeclarationTree node, D ctx) {
		// TODO Auto-generated method stub
		return (StatementTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public TypeTree visitInterfaceType(InterfaceTypeTree node, D ctx) {
		// TODO Auto-generated method stub
		return (TypeTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public TypeTree visitMemberType(MemberTypeTree node, D ctx) {
		TypeTree oldBase = node.getBaseType();
		TypeTree newBase = (TypeTree) oldBase.accept(this, ctx);
		
		TypeTree oldName = node.getName();
		TypeTree newName = (TypeTree) oldName.accept(this, ctx);
		
		if (oldBase != newBase || oldName != newName)
			node = new MemberTypeTreeImpl(node.getStart(), node.getEnd(), newBase, newName, node.isImplicit());
		
		return (TypeTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitNew(NewTree node, D ctx) {
		// TODO Auto-generated method stub
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitNull(NullLiteralTree node, D ctx) {
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitNumericLiteral(NumericLiteralTree node, D ctx) {
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public ExpressionTree visitObjectLiteral(ObjectLiteralTree node, D ctx) {
		// TODO Auto-generated method stub
		return (ExpressionTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public PatternTree visitObjectPattern(ObjectPatternTree node, D ctx) {
		// TODO Auto-generated method stub
		return (PatternTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public Tree visitParameterType(ParameterTypeTree node, D ctx) {
		// TODO Auto-generated method stub
		return node.accept(this.transformation, ctx);
	}
	
	@Override
	public TypeTree visitSpecialType(SpecialTypeTree node, D ctx) {
		return (TypeTree) node.accept(this.transformation, ctx);
	}
	
	@Override
	public StatementTree visitWith(WithTree node, D ctx) {
		ExpressionTree e1 = node.getScope();
		StatementTree s1 = node.getStatement();
		
		ExpressionTree e2 = (ExpressionTree) e1.accept(this, ctx);
		StatementTree s2 = (StatementTree) s1.accept(this, ctx);
		
		if (e1 != e2 || s1 != s2)
			node = new WithTreeImpl(node.getStart(), node.getEnd(), e2, s2);
		
		return (StatementTree) node.accept(this.transformation, ctx);
	}
}