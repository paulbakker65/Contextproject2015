grammar AnalysisLang;

@header {
package net.tudelft.hi.e.script;

import java.util.*;
}

parse
: operation*
;

///////////////////////////////////////
//                                   //
// Operation Type Definitions        //
//                                   //
///////////////////////////////////////
operation
: between_operation
| chunk_operation
| foreach_chunk_operation
| code_operation
| connect_operation
| constraint_operation
| convert_operation
| compute_operation
| lsa_operation
;

///////////////////////////////////////
//                                   //
// Operation Definitions             //
//                                   //
///////////////////////////////////////
between_operation
: 'BETWEEN' param=between_param
;

chunk_operation
: 'CHUNK' param=chunk_param
;

foreach_chunk_operation
: 'FOR EACH CHUNK' param=foreach_chunk_param
;

code_operation
: 'CODE' param=code_param
;

compute_operation
: 'COMPUTE' param=compute_param
;

connect_operation
: 'CONNECT' param=connect_param
;

constraint_operation
: 'CONSTRAINT' param=constraint_param
;

convert_operation
: 'CONVERT' param=convert_param
;

lsa_operation
: 'LSA' param=lsa_param
;

///////////////////////////////////////
// Between                           //
///////////////////////////////////////
between_param
: eventcol=field datecol1=field datecol2=field value1=value value2=value
| eventcol=field datecol1=field value1=value value2=value
;

///////////////////////////////////////
// Chunk                             //
///////////////////////////////////////
chunk_param
: fieldparam=field 'USING' rangeparam=range
| fieldparam=field 'USING' type=chunk_type numberparam=number
;

chunk_type returns [int i]
: 'YEAR'  { $i = 0; }
| 'MONTH' { $i = 1; }
| 'DAY'   { $i = 2; }
;

///////////////////////////////////////
// For each Chunk                    //
///////////////////////////////////////
foreach_chunk_param
: tableparam=table levelparam=number operationparam=operation
;

///////////////////////////////////////
// Code                              //
///////////////////////////////////////
code_param
: tableparam=table 'ON' patternparam=pattern 'AS' codenameparam=text
;

///////////////////////////////////////
// Compute                           //
///////////////////////////////////////
compute_param
: tableparam=table computeopparam=compute_operator fieldparam=field
;

///////////////////////////////////////
// Connect                           //
///////////////////////////////////////
connect_param
: fieldparam=field 'TO' anotherfieldparam=field
;

///////////////////////////////////////
// Constraint                        //
///////////////////////////////////////
constraint_param
: fieldparam=field opparam=compare_operator anotherfieldparam=field
| fieldparam=field opparam=compare_operator valueparam=value
;

///////////////////////////////////////
// Convert                           //
///////////////////////////////////////
convert_param
: fieldparam=field 'TO' formulaparam=formula
;

///////////////////////////////////////
// Lsa                               //
///////////////////////////////////////
lsa_param
: fieldparam=field from=number to=number key=value target=value
;

///////////////////////////////////////
//                                   //
// Common                            //
//                                   //
///////////////////////////////////////
table returns [String tablename]
: '[' tablenameparam=ID ']'                         { $tablename = $tablenameparam.text;  }
;

field returns [String tablename, String fieldname]
: '[' tablenameparam=ID '].[' fieldnameparam=ID ']' { $fieldname = $fieldnameparam.text;
                                                      $tablename = $tablenameparam.text; }
;

pattern returns [ArrayList<PatternDescription> patterndesc]
@init {
  $patterndesc = new ArrayList<PatternDescription>();
}
: ( '{' countparam=count_pattern recordconditionparam=record_condition '}'  { $patterndesc.add(new PatternDescription($countparam.count, $recordconditionparam.recordcondition)); } )+
;

record_condition returns [RecordCondition recordcondition]
: tableparam=table                            { $recordcondition = new RecordOccurrenceCondition($tableparam.tablename); }
| fieldparam=field conditionparam=condition   { $recordcondition = new RecordMatchesConditionCondition($field.fieldname, $conditionparam.cond); }
;

count_pattern returns [Count count]
: wildcard='*'          { $count = new MultipleCount(); }
| numberparam=NUMBER    { $count = new SingleCount($numberparam.int); }
;

number returns [Value val]
: numparam=NUMBER                    { $val = new NumberValue($numparam.int); }
;

compare_operator returns [CompareOperator op]
: opparam=EQ                         { $op = CompareOperator.EQ;   }
| opparam=NEQ                        { $op = CompareOperator.NEQ;  }
| opparam=GEQ                        { $op = CompareOperator.GEQ;  }
| opparam=G                          { $op = CompareOperator.G;    }
| opparam=LEQ                        { $op = CompareOperator.LEQ;  }
| opparam=L                          { $op = CompareOperator.L;    }
;

calc_operator returns [CalcOperator op]
: opparam=MULTIPLY                   { $op = CalcOperator.MULTIPLY;}
| opparam=DIVIDE                     { $op = CalcOperator.DIVIDE;  }
| opparam=PLUS                       { $op = CalcOperator.PLUS;    }
| opparma=MINUS                      { $op = CalcOperator.MINUS;   }
| opparam=MODULO                     { $op = CalcOperator.MODULO;  }
;

compute_operator returns [ComputeOperator op]
: opparam=AVG                        { $op = ComputeOperator.AVG;    }
| opparam=COUNT                      { $op = ComputeOperator.COUNT;  }
| opparam=MAX                        { $op = ComputeOperator.MAX;    }
| opparam=MIN                        { $op = ComputeOperator.MIN;    }
| opparam=STDDEV                     { $op = ComputeOperator.STDEV; }
| opparam=SUM                        { $op = ComputeOperator.SUM;    }
;

formula returns [Formula form]
: fieldparam=field opparam=calc_operator anotherfieldparam=field
  { $form = new Formula($fieldparam.fieldname, $opparam.op, $anotherfieldparam.fieldname); }
| fieldparam=field opparam=calc_operator valueparam=number
  { $form = new Formula($fieldparam.fieldname, $opparam.op, $valueparam.val); }
| fieldparam=field opparam=calc_operator formulaparam=formula
  { $form = new Formula($fieldparam.fieldname, $opparam.op, $formulaparam.form); }
;

condition returns [Condition cond]
: opparam=compare_operator valueparam=value
  { $cond = new Condition($opparam.op, $valueparam.val); }
| opparam=compare_operator valueparam=value 'AND' anothercond=condition
  { $cond = new Condition($opparam.op, $valueparam.val); }
;

range
: '>' g=value 'AND' '<' l=value
;

value returns [Value val]
: dataparam=date         { $val = $dataparam.val; }
| numparam=number        { $val = $numparam.val;  }
| stringparam=text       { $val = $stringparam.val; }
;

date returns [Value val]
: 'DATE(' yearparam=NUMBER '-' monthparam=NUMBER '-' dayparam=NUMBER ')' {
                                           GregorianCalendar calObj = new GregorianCalendar();
                                           calObj.set($yearparam.int, $monthparam.int, $dayparam.int);
                                           $val = new DateValue(calObj); }
;

text returns [Value val]
: stringparam=STRING    { String text = $stringparam.text;
                          text = text.substring(1, text.length()-1);
                          $val = new StringValue(text);
}
;

///////////////////////////////////////
//                                   //
// LEXER RULES                       //
//                                   //
///////////////////////////////////////

ID  : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

EQ  : '==' ;
NEQ : '!=' ;
GEQ : '>=' ;
G   : '>'  ;
LEQ : '<=' ;
L   : '<'  ;
MULTIPLY : '*' ;
DIVIDE   : '/' ;
PLUS     : '+' ;
MINUS    : '-' ;
MODULO   : '%' ;
AVG      : 'AVG()' ;
COUNT    : 'COUNT()' ;
MAX      : 'MAX()' ;
MIN      : 'MIN()' ;
SUM      : 'SUM()' ;
STDDEV   : 'STDDEV()' ;


NUMBER
: INT
| FLOAT
;

fragment
INT
: '0'..'9'+
;

fragment
FLOAT
: ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
| '.' ('0'..'9')+ EXPONENT?
| ('0'..'9')+ EXPONENT
;

WS
: ( ' '
  | '\t'
  | '\r'
  | '\n'
  ) -> channel(HIDDEN)
//  ) {$channel=HIDDEN;}
;

STRING
: '"' ( ESC_SEQ | ~('\\'|'"') )* '"' ;

CHAR
: '\'' ( ESC_SEQ | ~('\''|'\\') ) '\'' ;

fragment
EXPONENT: ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_NUM : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ : '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') ;
