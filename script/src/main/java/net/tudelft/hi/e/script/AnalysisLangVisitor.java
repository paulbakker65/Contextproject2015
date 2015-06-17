// Generated from /Users/mawdegroot/git/Contextproject2015/script/src/main/antlr4/AnalysisLang.g4 by ANTLR 4.5

package net.tudelft.hi.e.script;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AnalysisLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AnalysisLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(@NotNull AnalysisLangParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(@NotNull AnalysisLangParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#between_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetween_operation(@NotNull AnalysisLangParser.Between_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#chunk_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChunk_operation(@NotNull AnalysisLangParser.Chunk_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#foreach_chunk_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeach_chunk_operation(@NotNull AnalysisLangParser.Foreach_chunk_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#code_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode_operation(@NotNull AnalysisLangParser.Code_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#compute_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompute_operation(@NotNull AnalysisLangParser.Compute_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#connect_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConnect_operation(@NotNull AnalysisLangParser.Connect_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#constraint_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraint_operation(@NotNull AnalysisLangParser.Constraint_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#convert_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConvert_operation(@NotNull AnalysisLangParser.Convert_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#lsa_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsa_operation(@NotNull AnalysisLangParser.Lsa_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#between_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetween_param(@NotNull AnalysisLangParser.Between_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#chunk_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChunk_param(@NotNull AnalysisLangParser.Chunk_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#chunk_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChunk_type(@NotNull AnalysisLangParser.Chunk_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#foreach_chunk_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeach_chunk_param(@NotNull AnalysisLangParser.Foreach_chunk_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#code_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode_param(@NotNull AnalysisLangParser.Code_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#compute_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompute_param(@NotNull AnalysisLangParser.Compute_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#connect_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConnect_param(@NotNull AnalysisLangParser.Connect_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#constraint_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraint_param(@NotNull AnalysisLangParser.Constraint_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#convert_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConvert_param(@NotNull AnalysisLangParser.Convert_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#lsa_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsa_param(@NotNull AnalysisLangParser.Lsa_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable(@NotNull AnalysisLangParser.TableContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(@NotNull AnalysisLangParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(@NotNull AnalysisLangParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#record_condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecord_condition(@NotNull AnalysisLangParser.Record_conditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#count_pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCount_pattern(@NotNull AnalysisLangParser.Count_patternContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull AnalysisLangParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#compare_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare_operator(@NotNull AnalysisLangParser.Compare_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#calc_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalc_operator(@NotNull AnalysisLangParser.Calc_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#compute_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompute_operator(@NotNull AnalysisLangParser.Compute_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormula(@NotNull AnalysisLangParser.FormulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(@NotNull AnalysisLangParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange(@NotNull AnalysisLangParser.RangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull AnalysisLangParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#date}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate(@NotNull AnalysisLangParser.DateContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalysisLangParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(@NotNull AnalysisLangParser.TextContext ctx);
}
