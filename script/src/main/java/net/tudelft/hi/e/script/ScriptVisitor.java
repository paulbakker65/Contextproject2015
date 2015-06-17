package net.tudelft.hi.e.script;

import net.tudelft.hi.e.common.enums.ChunkType;
import net.tudelft.hi.e.common.enums.ComputeOperator;
import net.tudelft.hi.e.common.exceptions.TableNotFoundException;
import net.tudelft.hi.e.computation.*;
import net.tudelft.hi.e.data.NumberValue;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.data.Value;
import net.tudelft.hi.e.script.AnalysisLangParser.Between_operationContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Between_paramContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Chunk_operationContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Constraint_operationContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Script visitor class that visits each node of the parse thee instead of listening to a
 * treewalkers findings.
 */
class ScriptVisitor extends AbstractParseTreeVisitor implements AnalysisLangVisitor {

  private static final Logger LOG = Logger.getLogger(ScriptVisitor.class.getName());

  List<Table> tables;

  List<Operation> operationList;

  public ScriptVisitor(final List<Table> tables) {
    super();
    this.tables = tables;
    this.operationList = new ArrayList<Operation>();
  }

  /**
   * Creates a list of operations walking a parsetree generated by the analysisparser.
   *
   * @return the list of operations the visitor created.
   */
  public List<Operation> getOperationList() {
    return this.operationList;
  }

  /**
   * The getTableForTableName method retrieves the Table with the specified table name from the list
   * of Tables in the tables field.
   *
   * @param tableName The table name that is searched for.
   * @return The Table object retrieved from tables.
   * @throws TableNotFoundException If the the table cannot be found in the tables field a
   *                                TableNotFoundException is thrown.
   */
  private Table getTableForTableName(final String tableName) throws TableNotFoundException {
    for (Table table : tables) {
      if (table.getName().equals(tableName)) {
        return table;
      }
    }
    throw new TableNotFoundException(String.format("Table \"%s\" not found", tableName));
  }

  @Override public final BetweenOperation visitBetween_operation(Between_operationContext ctx) {
    return visitBetween_param(ctx.param);
  }

  @Override public final BetweenOperation visitBetween_param(Between_paramContext ctx) {
    try {
      return new BetweenOperation(getTableForTableName(visitField(ctx.eventcol)[0]),
          visitField(ctx.datecol1)[1], visitField(ctx.datecol2)[1], visitValue(ctx.value1),
          visitValue(ctx.value2));
    } catch (TableNotFoundException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      return null;
    }
  }

  @Override public final Object visitCalc_operator(AnalysisLangParser.Calc_operatorContext ctx) {
    return ctx.op;
  }

  @Override public final ChunkingOperation visitChunk_operation(Chunk_operationContext ctx) {
    return visitChunk_param(ctx.param);
  }

  @Override
  public final ChunkingOperation visitChunk_param(AnalysisLangParser.Chunk_paramContext ctx) {
    if (ctx.type != null) {
      try {
        return new ChunkingOperation(getTableForTableName(visitField(ctx.fieldparam)[0]),
            visitField(ctx.fieldparam)[1], visitChunk_type(ctx.type));
      } catch (TableNotFoundException ex) {
        LOG.log(Level.SEVERE, ex.getMessage(), ex);
        return null;
      }
    } else {
      return null;
    }
  }

  @Override public final ChunkType visitChunk_type(AnalysisLangParser.Chunk_typeContext ctx) {
    switch (ctx.i) {
      case 0:
        return ChunkType.valueOf("YEAR");
      case 1:
        return ChunkType.valueOf("MONTH");
      case 2:
        return ChunkType.valueOf("DAY");
      default:
        return null;
    }
  }

  @Override public final Object visitCode_operation(AnalysisLangParser.Code_operationContext ctx) {
    return visitCode_param(ctx.param);
  }

  @Override public final CodingOperation visitCode_param(AnalysisLangParser.Code_paramContext ctx) {
    try {
      return new CodingOperation(getTableForTableName(visitTable(ctx.tableparam)),
          visitPattern(ctx.patternparam), visitText(ctx.codenameparam).toString());
    } catch (TableNotFoundException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      return null;
    }
  }

  @Override
  public final Object visitCompare_operator(AnalysisLangParser.Compare_operatorContext ctx) {
    return ctx.op;
  }

  @Override public final ComputeOperation visitCompute_operation(
      AnalysisLangParser.Compute_operationContext ctx) {
    return visitCompute_param(ctx.param);
  }

  @Override public final ComputeOperator visitCompute_operator(
      AnalysisLangParser.Compute_operatorContext ctx) {
    return ctx.op;
  }

  @Override
  public final ComputeOperation visitCompute_param(AnalysisLangParser.Compute_paramContext ctx) {
    try {
      return new ComputeOperation(getTableForTableName(visitTable(ctx.tableparam)),
          visitCompute_operator(ctx.computeopparam), visitField(ctx.fieldparam)[1]);
    } catch (TableNotFoundException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      return null;
    }
  }

  @Override public final List<Condition> visitCondition(AnalysisLangParser.ConditionContext ctx) {
    List<Condition> resultConditionList = new ArrayList<Condition>();
    resultConditionList.add(ctx.cond);
    if (ctx.anothercond != null) {
      resultConditionList.addAll(visitCondition(ctx.anothercond));
    }
    return resultConditionList;
  }

  @Override public final ConnectionOperation visitConnect_operation(
      AnalysisLangParser.Connect_operationContext ctx) {
    return visitConnect_param(ctx.param);
  }

  @Override
  public final ConnectionOperation visitConnect_param(AnalysisLangParser.Connect_paramContext ctx) {
    try {
      String[] firstField = visitField(ctx.fieldparam);
      String[] secondField = visitField(ctx.anotherfieldparam);
      return new ConnectionOperation(getTableForTableName(firstField[0]),
          getTableForTableName(secondField[0]), firstField[1], secondField[1]);
    } catch (TableNotFoundException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      return null;
    }
  }

  @Override
  public final ConstraintOperation visitConstraint_operation(Constraint_operationContext ctx) {
    return visitConstraint_param(ctx.param);
  }

  @Override public final ConstraintOperation visitConstraint_param(
      AnalysisLangParser.Constraint_paramContext ctx) {
    try {
      return new ConstraintOperation(getTableForTableName(visitField(ctx.fieldparam)[0]),
          visitField(ctx.fieldparam)[1], ctx.opparam.op, visitValue(ctx.valueparam));
    } catch (TableNotFoundException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      return null;
    }
  }

  @Override
  public final Object visitConvert_operation(AnalysisLangParser.Convert_operationContext ctx) {
    return visitConvert_param(ctx.param);
  }

  @Override public final Object visitConvert_param(AnalysisLangParser.Convert_paramContext ctx) {
    return null;
  }

  @Override public final Object visitCount_pattern(AnalysisLangParser.Count_patternContext ctx) {
    return ctx.count;
  }

  @Override public final Value visitDate(AnalysisLangParser.DateContext ctx) {
    return ctx.val;
  }

  @Override public final String[] visitField(AnalysisLangParser.FieldContext ctx) {
    return new String[] {ctx.tablename, ctx.fieldname};
  }

  @Override public final Object visitForeach_chunk_operation(
      AnalysisLangParser.Foreach_chunk_operationContext ctx) {
    return visitForeach_chunk_param(ctx.param);
  }

  @Override public final ForEachChunkOperation visitForeach_chunk_param(
      AnalysisLangParser.Foreach_chunk_paramContext ctx) {
    try {
      return new ForEachChunkOperation(getTableForTableName(visitTable(ctx.tableparam)),
          visitNumberGetInt(ctx.levelparam), visitOperationNoAdd(ctx.operationparam));
    } catch (TableNotFoundException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      return null;
    }
  }

  @Override public final Object visitFormula(AnalysisLangParser.FormulaContext ctx) {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public final LsaOperation visitLsa_operation(AnalysisLangParser.Lsa_operationContext ctx) {
    return visitLsa_param(ctx.param);
  }

  @Override public final LsaOperation visitLsa_param(AnalysisLangParser.Lsa_paramContext ctx) {
    try {
      String[] fieldReference = visitField(ctx.fieldparam);
      return new LsaOperation(getTableForTableName(fieldReference[0]), fieldReference[1],
          visitNumberGetInt(ctx.from), visitNumberGetInt(ctx.to), visitValue(ctx.key),
          visitValue(ctx.target));
    } catch (TableNotFoundException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      return null;
    }
  }

  @Override public final Value visitNumber(AnalysisLangParser.NumberContext ctx) {
    return ctx.val;
  }

  private final int visitNumberGetInt(AnalysisLangParser.NumberContext ctx) {
    NumberValue val = (NumberValue) ctx.val;
    return (int) val.getValue();
  }

  @Override public final Operation visitOperation(AnalysisLangParser.OperationContext ctx) {
    operationList.add((Operation) visitChildren(ctx));
    return operationList.get(operationList.size() - 1);
  }

  private Operation visitOperationNoAdd(AnalysisLangParser.OperationContext ctx) {
    return (Operation) visitChildren(ctx);
  }

  @Override public final Object visitParse(AnalysisLangParser.ParseContext ctx) {
    visitChildren(ctx);
    return operationList;
  }

  @Override public final Pattern visitPattern(AnalysisLangParser.PatternContext ctx) {
    return PatternFactory.createPattern(ctx.patterndesc);
  }

  @Override public final Object visitRange(AnalysisLangParser.RangeContext ctx) {
    throw new UnsupportedOperationException(
        "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public final Object visitRecord_condition(AnalysisLangParser.Record_conditionContext ctx) {
    return ctx.recordcondition;
  }

  @Override public final String visitTable(AnalysisLangParser.TableContext ctx) {
    return ctx.tablename;
  }

  @Override public final Value visitText(AnalysisLangParser.TextContext ctx) {
    return ctx.val;
  }

  @Override public final Value visitValue(AnalysisLangParser.ValueContext ctx) {
    return ctx.val;
  }

}
