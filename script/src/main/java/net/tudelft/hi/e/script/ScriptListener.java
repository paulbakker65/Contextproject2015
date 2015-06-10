package net.tudelft.hi.e.script;

import java.util.ArrayList;
import net.tudelft.hi.e.common.enums.ChunkType;
import net.tudelft.hi.e.data.Table;
import net.tudelft.hi.e.script.AnalysisLangParser.Between_paramContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Calc_operatorContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Chunk_paramContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Chunk_typeContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Code_paramContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Compare_operatorContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Compare_paramContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Compute_paramContext;
import net.tudelft.hi.e.script.AnalysisLangParser.ConditionContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Connect_paramContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Constraint_paramContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Convert_paramContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Count_patternContext;
import net.tudelft.hi.e.script.AnalysisLangParser.DateContext;
import net.tudelft.hi.e.script.AnalysisLangParser.FieldContext;
import net.tudelft.hi.e.script.AnalysisLangParser.FormulaContext;
import net.tudelft.hi.e.script.AnalysisLangParser.Lsa_paramContext;
import net.tudelft.hi.e.script.AnalysisLangParser.NumberContext;
import net.tudelft.hi.e.script.AnalysisLangParser.PatternContext;
import net.tudelft.hi.e.script.AnalysisLangParser.TableContext;
import net.tudelft.hi.e.script.AnalysisLangParser.TextContext;
import net.tudelft.hi.e.script.OperationSpec.OperationType;

/**
 * ScriptListener is the implemented AnalysisLang Listener. It implements the methods required to
 * parse the script input.
 *
 */
public class ScriptListener extends AnalysisLangBaseListener {

  ArrayList<Table> tables;
  ArrayList<OperationSpec> opList;
  OperationSpec currentOp;

  /**
   * ScriptListener extends AnalysisLangBaseListener. It is used to catch the objects created by the
   * parser.
   *
   * @param tables The tables parameter is an ArrayList of all the tables the input parser has read.
   */
  public ScriptListener(final ArrayList<Table> tables) {
    super();
    this.tables = tables;
    this.opList = new ArrayList<OperationSpec>();
  }

  @Override
  public void enterCalc_operator(final Calc_operatorContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.op != null) {
      currentOp.addOperationOperand(ctx.op);
    }
  }

  @Override
  public void enterChunk_param(final Chunk_paramContext ctx) {
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CHUNK);

    this.opList.add(currentOp);
  }

  @Override
  public void enterChunk_type(final Chunk_typeContext ctx) {
    switch (ctx.i) {
      case 0:
        currentOp.addOperationOperand(ChunkType.valueOf("YEAR"));
        break;
      case 1:
        currentOp.addOperationOperand(ChunkType.valueOf("MONTH"));
        break;
      case 2:
        currentOp.addOperationOperand(ChunkType.valueOf("DAY"));
        break;
      default:
        break;
    }
  }

  @Override
  public void enterCode_param(final Code_paramContext ctx) {
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CODE);

    this.opList.add(currentOp);
  }

  @Override
  public void enterCompare_operator(final Compare_operatorContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.op != null) {
      currentOp.addOperationOperand(ctx.op);
    }
  }

  @Override
  public void enterCompare_param(final Compare_paramContext ctx) {
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.COMPARE);

    this.opList.add(currentOp);
  }

  @Override
  public void enterCompute_param(final Compute_paramContext ctx) {
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.COMPUTE);

    this.opList.add(currentOp);
  }

  @Override
  public void enterCondition(final ConditionContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.cond != null) {
      currentOp.addOperationOperand(ctx.cond);
      ctx.children.clear();
    }
  }

  @Override
  public void enterConnect_param(final Connect_paramContext ctx) {
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CONNECT);

    this.opList.add(currentOp);
  }

  @Override
  public void enterConstraint_param(final Constraint_paramContext ctx) {
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CONSTRAINT);

    this.opList.add(currentOp);
  }

  @Override
  public void enterConvert_param(final Convert_paramContext ctx) {
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CONVERT);

    this.opList.add(currentOp);
  }

  @Override
  public void enterLsa_param(final Lsa_paramContext ctx) {
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.LSA);

    this.opList.add(currentOp);
  }

  @Override
  public void enterBetween_param(final Between_paramContext ctx) {
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.BETWEEN);

    this.opList.add(currentOp);
  }

  /*
   * COMMON
   */

  @Override
  public void enterPattern(final PatternContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.patterndesc != null) {
      currentOp.addOperationOperand(ctx.patterndesc);
      ctx.children.clear();
    }
  }

  @Override
  public void enterCount_pattern(final Count_patternContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.count != null) {
      currentOp.addOperationOperand(ctx.count);
    }
  }

  @Override
  public void enterTable(final TableContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.tablename != null) {
      currentOp.addOperationOperand(ctx.tablename);
    }
  }

  @Override
  public void enterField(final FieldContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.tablename != null) {
      currentOp.addOperationOperand(ctx.tablename);
    }
    if (ctx.fieldname != null) {
      currentOp.addOperationOperand(ctx.fieldname);
    }
  }

  @Override
  public void enterFormula(final FormulaContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.form != null) {
      currentOp.addOperationOperand(ctx.form);
      ctx.children.clear();
    }
  }

  @Override
  public void enterNumber(final NumberContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.val != null) {
      currentOp.addOperationOperand(ctx.val);
    }
  }

  @Override
  public void enterDate(final DateContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.val != null) {
      currentOp.addOperationOperand(ctx.val);
    }
  }

  @Override
  public void enterText(final TextContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.val != null) {
      currentOp.addOperationOperand(ctx.val);
    }
  }

  public ArrayList<OperationSpec> getOpList() {
    return this.opList;
  }
}
