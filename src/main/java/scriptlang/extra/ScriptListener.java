package scriptlang.extra;

import scriptlang.AnalysisLangBaseListener;
import scriptlang.AnalysisLangParser.Between_paramContext;
import scriptlang.AnalysisLangParser.Calc_operatorContext;
import scriptlang.AnalysisLangParser.Chunk_paramContext;
import scriptlang.AnalysisLangParser.Code_paramContext;
import scriptlang.AnalysisLangParser.Compare_operatorContext;
import scriptlang.AnalysisLangParser.Compare_paramContext;
import scriptlang.AnalysisLangParser.Compute_paramContext;
import scriptlang.AnalysisLangParser.ConditionContext;
import scriptlang.AnalysisLangParser.Connect_paramContext;
import scriptlang.AnalysisLangParser.Constraint_paramContext;
import scriptlang.AnalysisLangParser.Convert_paramContext;
import scriptlang.AnalysisLangParser.DateContext;
import scriptlang.AnalysisLangParser.FieldContext;
import scriptlang.AnalysisLangParser.FormulaContext;
import scriptlang.AnalysisLangParser.Lsa_paramContext;
import scriptlang.AnalysisLangParser.NumberContext;
import scriptlang.AnalysisLangParser.TextContext;
import scriptlang.AnalysisLangParser.ValueContext;
import scriptlang.extra.OperationSpec.OperationType;

import table.Table;

import java.util.ArrayList;

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
    // : fieldparam=field 'USING' rangeparam=range
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CHUNK);

    this.opList.add(currentOp);
  }

  @Override
  public void enterCode_param(final Code_paramContext ctx) {
    // : fieldparam=field 'ON' conditionparam=condition
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
    // : fieldparam=field currentOpparam=compare_currentOperator
    // anotherfieldparam=field
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.COMPARE);

    this.opList.add(currentOp);
  }

  @Override
  public void enterCompute_param(final Compute_paramContext ctx) {
    // : fieldparam=field 'TO' formulaparam=formula
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
    // : fieldparam=field 'TO' anotherfieldparam=field
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CONNECT);

    this.opList.add(currentOp);
  }

  @Override
  public void enterConstraint_param(final Constraint_paramContext ctx) {
    // : fieldparam=field currentOpparam=compare_currentOperator
    // anotherfieldparam=field
    // | fieldparam=field currentOpparam=compare_currentOperator
    // valueparam=value
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CONSTRAINT);

    this.opList.add(currentOp);
  }

  @Override
  public void enterConvert_param(final Convert_paramContext ctx) {
    // : fieldparam=field 'TO' formulaparam=formula
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CONVERT);

    this.opList.add(currentOp);
  }
  
  @Override
  public void enterLsa_param(final Lsa_paramContext ctx) {
    // : fieldparam=field from=number to=number key=value target=value
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.LSA);
    
    this.opList.add(currentOp);
  }
  
  @Override
  public void enterBetween_param(final Between_paramContext ctx) {
    // : eventcol=field datecol1=value datecol2=value value1=value value2=value
    // | eventcol=field datecol1=value value1=value value2=value
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.BETWEEN);
    
    this.opList.add(currentOp);
  }

  /*
   * 
   * COMMON
   *
   */
  
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
