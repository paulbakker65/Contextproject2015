package scriptlang.extra;

import java.util.ArrayList;

import scriptlang.AnalysisLangBaseListener;
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
import scriptlang.AnalysisLangParser.FieldContext;
import scriptlang.AnalysisLangParser.FormulaContext;
import scriptlang.AnalysisLangParser.RangeContext;
import scriptlang.AnalysisLangParser.ValueContext;
import scriptlang.extra.OperationSpec.OperationType;
import table.Table;

/**
 * ALListener is the implemented AnalysisLang Listener. It implements
 * the methods required to parse the script input.
 * 
 */
public class ALListener extends AnalysisLangBaseListener {
  
  ArrayList<Table> tables;
  ArrayList<OperationSpec> opList;
  OperationSpec currentOp;
  
  public ALListener(ArrayList<Table> tables) {
    super();
    this.tables = tables;
    this.opList = new ArrayList<OperationSpec>();
  }
  
  public ArrayList<OperationSpec> getOpList() {
    return this.opList;
  }

  @Override
  public void enterChunk_param(Chunk_paramContext ctx) {
    // : fieldparam=field 'USING' rangeparam=range
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CHUNK);
    
    this.opList.add(currentOp);
  }

  @Override
  public void enterCode_param(Code_paramContext ctx) {
    // : fieldparam=field 'ON' conditionparam=condition
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CODE);
    
    this.opList.add(currentOp);
  }

  @Override
  public void enterConnect_param(Connect_paramContext ctx) {
    // : fieldparam=field 'TO' anotherfieldparam=field
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CONNECT);
    
    this.opList.add(currentOp);
  }

  @Override
  public void enterCompare_param(Compare_paramContext ctx) {
    // : fieldparam=field currentOpparam=compare_currentOperator anotherfieldparam=field
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.COMPARE);
    
    this.opList.add(currentOp);
  }
  
  @Override
  public void enterConstraint_param(Constraint_paramContext ctx) {
    // : fieldparam=field currentOpparam=compare_currentOperator anotherfieldparam=field
    // | fieldparam=field currentOpparam=compare_currentOperator valueparam=value
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CONSTRAINT);

    this.opList.add(currentOp);
  }

  @Override
  public void enterConvert_param(Convert_paramContext ctx) {
    // : fieldparam=field 'TO' formulaparam=formula
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.CONVERT);
    
    this.opList.add(currentOp);
  }

  @Override
  public void enterCompute_param(Compute_paramContext ctx) {
    // : fieldparam=field 'TO' formulaparam=formula
    currentOp = new OperationSpec(tables);
    currentOp.setOperationType(OperationType.COMPUTE);
    
    this.opList.add(currentOp);
  }
  
  @Override
  public void enterField(FieldContext ctx) {
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
  public void enterValue(ValueContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.val != null) {
      currentOp.addOperationOperand(ctx.val);
    }
  }
  
  @Override
  public void enterCompare_operator(Compare_operatorContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.op != null) {
      currentOp.addOperationOperand(ctx.op);
    }
  }
  
  @Override
  public void enterCalc_operator(Calc_operatorContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.op != null) {
      currentOp.addOperationOperand(ctx.op);
    }
  }
  
  @Override
  public void enterFormula(FormulaContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.form != null) {
      currentOp.addOperationOperand(ctx.form);
      ctx.children.clear();
    }
  }
  
  @Override
  public void enterCondition(ConditionContext ctx) {
    if (ctx == null) {
      return;
    }
    if (ctx.cond != null) {
      currentOp.addOperationOperand(ctx.cond);
      ctx.children.clear();
    }
  }
}

