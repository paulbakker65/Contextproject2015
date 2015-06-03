// Generated from AnalysisLang.g4 by ANTLR 4.5
package scriptlang;

import scriptlang.extra.*;
import enums.*;
import table.value.*;
import java.util.*;
import java.text.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AnalysisLangParser}.
 */
public interface AnalysisLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(AnalysisLangParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(AnalysisLangParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(AnalysisLangParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(AnalysisLangParser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#between_operation}.
	 * @param ctx the parse tree
	 */
	void enterBetween_operation(AnalysisLangParser.Between_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#between_operation}.
	 * @param ctx the parse tree
	 */
	void exitBetween_operation(AnalysisLangParser.Between_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#chunk_operation}.
	 * @param ctx the parse tree
	 */
	void enterChunk_operation(AnalysisLangParser.Chunk_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#chunk_operation}.
	 * @param ctx the parse tree
	 */
	void exitChunk_operation(AnalysisLangParser.Chunk_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#code_operation}.
	 * @param ctx the parse tree
	 */
	void enterCode_operation(AnalysisLangParser.Code_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#code_operation}.
	 * @param ctx the parse tree
	 */
	void exitCode_operation(AnalysisLangParser.Code_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#compare_operation}.
	 * @param ctx the parse tree
	 */
	void enterCompare_operation(AnalysisLangParser.Compare_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#compare_operation}.
	 * @param ctx the parse tree
	 */
	void exitCompare_operation(AnalysisLangParser.Compare_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#compute_operation}.
	 * @param ctx the parse tree
	 */
	void enterCompute_operation(AnalysisLangParser.Compute_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#compute_operation}.
	 * @param ctx the parse tree
	 */
	void exitCompute_operation(AnalysisLangParser.Compute_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#connect_operation}.
	 * @param ctx the parse tree
	 */
	void enterConnect_operation(AnalysisLangParser.Connect_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#connect_operation}.
	 * @param ctx the parse tree
	 */
	void exitConnect_operation(AnalysisLangParser.Connect_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#constraint_operation}.
	 * @param ctx the parse tree
	 */
	void enterConstraint_operation(AnalysisLangParser.Constraint_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#constraint_operation}.
	 * @param ctx the parse tree
	 */
	void exitConstraint_operation(AnalysisLangParser.Constraint_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#convert_operation}.
	 * @param ctx the parse tree
	 */
	void enterConvert_operation(AnalysisLangParser.Convert_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#convert_operation}.
	 * @param ctx the parse tree
	 */
	void exitConvert_operation(AnalysisLangParser.Convert_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#lsa_operation}.
	 * @param ctx the parse tree
	 */
	void enterLsa_operation(AnalysisLangParser.Lsa_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#lsa_operation}.
	 * @param ctx the parse tree
	 */
	void exitLsa_operation(AnalysisLangParser.Lsa_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#between_param}.
	 * @param ctx the parse tree
	 */
	void enterBetween_param(AnalysisLangParser.Between_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#between_param}.
	 * @param ctx the parse tree
	 */
	void exitBetween_param(AnalysisLangParser.Between_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#chunk_param}.
	 * @param ctx the parse tree
	 */
	void enterChunk_param(AnalysisLangParser.Chunk_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#chunk_param}.
	 * @param ctx the parse tree
	 */
	void exitChunk_param(AnalysisLangParser.Chunk_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#chunk_type}.
	 * @param ctx the parse tree
	 */
	void enterChunk_type(AnalysisLangParser.Chunk_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#chunk_type}.
	 * @param ctx the parse tree
	 */
	void exitChunk_type(AnalysisLangParser.Chunk_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#code_param}.
	 * @param ctx the parse tree
	 */
	void enterCode_param(AnalysisLangParser.Code_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#code_param}.
	 * @param ctx the parse tree
	 */
	void exitCode_param(AnalysisLangParser.Code_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#compare_param}.
	 * @param ctx the parse tree
	 */
	void enterCompare_param(AnalysisLangParser.Compare_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#compare_param}.
	 * @param ctx the parse tree
	 */
	void exitCompare_param(AnalysisLangParser.Compare_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#compute_param}.
	 * @param ctx the parse tree
	 */
	void enterCompute_param(AnalysisLangParser.Compute_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#compute_param}.
	 * @param ctx the parse tree
	 */
	void exitCompute_param(AnalysisLangParser.Compute_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#connect_param}.
	 * @param ctx the parse tree
	 */
	void enterConnect_param(AnalysisLangParser.Connect_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#connect_param}.
	 * @param ctx the parse tree
	 */
	void exitConnect_param(AnalysisLangParser.Connect_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#constraint_param}.
	 * @param ctx the parse tree
	 */
	void enterConstraint_param(AnalysisLangParser.Constraint_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#constraint_param}.
	 * @param ctx the parse tree
	 */
	void exitConstraint_param(AnalysisLangParser.Constraint_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#convert_param}.
	 * @param ctx the parse tree
	 */
	void enterConvert_param(AnalysisLangParser.Convert_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#convert_param}.
	 * @param ctx the parse tree
	 */
	void exitConvert_param(AnalysisLangParser.Convert_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#lsa_param}.
	 * @param ctx the parse tree
	 */
	void enterLsa_param(AnalysisLangParser.Lsa_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#lsa_param}.
	 * @param ctx the parse tree
	 */
	void exitLsa_param(AnalysisLangParser.Lsa_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(AnalysisLangParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(AnalysisLangParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(AnalysisLangParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(AnalysisLangParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(AnalysisLangParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(AnalysisLangParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#record_condition}.
	 * @param ctx the parse tree
	 */
	void enterRecord_condition(AnalysisLangParser.Record_conditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#record_condition}.
	 * @param ctx the parse tree
	 */
	void exitRecord_condition(AnalysisLangParser.Record_conditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#count_pattern}.
	 * @param ctx the parse tree
	 */
	void enterCount_pattern(AnalysisLangParser.Count_patternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#count_pattern}.
	 * @param ctx the parse tree
	 */
	void exitCount_pattern(AnalysisLangParser.Count_patternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(AnalysisLangParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(AnalysisLangParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#compare_operator}.
	 * @param ctx the parse tree
	 */
	void enterCompare_operator(AnalysisLangParser.Compare_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#compare_operator}.
	 * @param ctx the parse tree
	 */
	void exitCompare_operator(AnalysisLangParser.Compare_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#calc_operator}.
	 * @param ctx the parse tree
	 */
	void enterCalc_operator(AnalysisLangParser.Calc_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#calc_operator}.
	 * @param ctx the parse tree
	 */
	void exitCalc_operator(AnalysisLangParser.Calc_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(AnalysisLangParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(AnalysisLangParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(AnalysisLangParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(AnalysisLangParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#range}.
	 * @param ctx the parse tree
	 */
	void enterRange(AnalysisLangParser.RangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#range}.
	 * @param ctx the parse tree
	 */
	void exitRange(AnalysisLangParser.RangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(AnalysisLangParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(AnalysisLangParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#date}.
	 * @param ctx the parse tree
	 */
	void enterDate(AnalysisLangParser.DateContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#date}.
	 * @param ctx the parse tree
	 */
	void exitDate(AnalysisLangParser.DateContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalysisLangParser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(AnalysisLangParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalysisLangParser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(AnalysisLangParser.TextContext ctx);
}