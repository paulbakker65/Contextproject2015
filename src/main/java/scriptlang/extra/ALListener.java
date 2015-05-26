package scriptlang.extra;

import java.util.ArrayList;

import scriptlang.AnalysisLangBaseListener;
import scriptlang.AnalysisLangParser.Chunk_paramContext;
import scriptlang.AnalysisLangParser.Code_paramContext;
import scriptlang.AnalysisLangParser.Compare_paramContext;
import scriptlang.AnalysisLangParser.Compute_paramContext;
import scriptlang.AnalysisLangParser.Connect_paramContext;
import scriptlang.AnalysisLangParser.Constraint_paramContext;
import scriptlang.AnalysisLangParser.Convert_paramContext;
import scriptlang.extra.OperationSpec.OperationType;

/**
 * ALListener is the implemented AnalysisLang Listener. It implements
 * the methods required to parse the script input.
 * 
 */
public class ALListener extends AnalysisLangBaseListener {
  
  ArrayList<OperationSpec> opList;
  
  public ALListener() {
    super();
    this.opList = new ArrayList<OperationSpec>();
  }
  
  public ArrayList<OperationSpec> getOpList() {
    return this.opList;
  }

  @Override
  public void enterChunk_param(Chunk_paramContext ctx) {
    // : fieldparam=field 'USING' rangeparam=range
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CHUNK);
    if (ctx.fieldparam != null && ctx.rangeparam != null) {
      op.addOperationOperand(ctx.fieldparam.fieldname);
      op.addOperationOperand(ctx.rangeparam.g.numparam.val);
      op.addOperationOperand(ctx.rangeparam.l.numparam.val);
    }
    this.opList.add(op);
  }

  @Override
  public void enterCode_param(Code_paramContext ctx) {
    // : fieldparam=field 'ON' conditionparam=condition
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CODE);
    if (ctx.fieldparam != null && ctx.conditionparam != null) {
      op.addOperationOperand(ctx.fieldparam.fieldname);
      op.addOperationOperand(ctx.conditionparam.cond);
    }
    this.opList.add(op);
  }

  @Override
  public void enterConnect_param(Connect_paramContext ctx) {
    // : fieldparam=field 'TO' anotherfieldparam=field
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CONNECT);
    if (ctx.fieldparam != null && ctx.anotherfieldparam != null) {
      op.addOperationOperand(ctx.fieldparam.fieldname);
      op.addOperationOperand(ctx.anotherfieldparam.fieldname);
    }
    this.opList.add(op);
  }

  @Override
  public void enterCompare_param(Compare_paramContext ctx) {
    // : fieldparam=field opparam=compare_operator anotherfieldparam=field
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.COMPARE);
    if (ctx.fieldparam != null && ctx.opparam != null && ctx.anotherfieldparam != null) {
      op.addOperationOperand(ctx.fieldparam.fieldname);
      op.addOperationOperand(ctx.opparam.op);
      op.addOperationOperand(ctx.anotherfieldparam.fieldname);
    }
    this.opList.add(op);
  }
  
  @Override
  public void enterConstraint_param(Constraint_paramContext ctx) {
    // : fieldparam=field opparam=compare_operator anotherfieldparam=field
    // | fieldparam=field opparam=compare_operator valueparam=value
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CONSTRAINT);
    if (ctx.anotherfieldparam != null) {
      op.addOperationOperand(ctx.fieldparam.fieldname);
      op.addOperationOperand(ctx.opparam.op);
      op.addOperationOperand(ctx.anotherfieldparam.fieldname);
    } else if (ctx.valueparam != null) {
      op.addOperationOperand(ctx.fieldparam.fieldname);
      op.addOperationOperand(ctx.opparam.op);
      op.addOperationOperand(ctx.valueparam.val);
    }
    this.opList.add(op);
  }

  @Override
  public void enterConvert_param(Convert_paramContext ctx) {
    // : fieldparam=field 'TO' formulaparam=formula
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.CONVERT);
    if (ctx.fieldparam != null && ctx.formulaparam != null) {
      op.addOperationOperand(ctx.fieldparam.fieldname);
      op.addOperationOperand(ctx.formulaparam.form);
    }
    this.opList.add(op);
  }

  @Override
  public void enterCompute_param(Compute_paramContext ctx) {
    // : fieldparam=field 'TO' formulaparam=formula
    OperationSpec op = new OperationSpec();
    op.setOperationType(OperationType.COMPUTE);
    if (ctx.fieldparam != null && ctx.formulaparam != null) {
      op.addOperationOperand(ctx.fieldparam.fieldname);
      op.addOperationOperand(ctx.formulaparam.form);
    }
    this.opList.add(op);
  }
  
}
