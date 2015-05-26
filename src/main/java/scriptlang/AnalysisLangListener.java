// Generated from AnalysisLang.g4 by ANTLR 4.5
package scriptlang;

import scriptlang.extra.*;
import table.value.*;
import operations.FilterOperation;
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
