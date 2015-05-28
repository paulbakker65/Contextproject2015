grammar AnalysisLang;

@header {
import scriptlang.extra.*;
import enums.*;
import table.value.*;
import operations.FilterOperation;
import java.util.*;
import java.text.*;
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
: chunk_operation
| code_operation
| connect_operation
| compare_operation
| constraint_operation
| convert_operation
| compute_operation
;

///////////////////////////////////////
//                                   //
// Operation Definitions             //
//                                   //
///////////////////////////////////////
chunk_operation
: 'CHUNK' param=chunk_param
;

code_operation
: 'CODE' param=code_param
;

connect_operation
: 'CONNECT' param=connect_param
;

compare_operation
: 'COMPARE' param=compare_param
;

constraint_operation
: 'CONSTRAINT' param=constraint_param
;

convert_operation
: 'CONVERT' param=convert_param
;

compute_operation
: 'COMPUTE' param=compute_param
;

///////////////////////////////////////
// Chunk                             //
///////////////////////////////////////
chunk_param
: fieldparam=field 'USING' rangeparam=range
;

///////////////////////////////////////
// Code                              //
///////////////////////////////////////
code_param
: fieldparam=field 'ON' conditionparam=condition
;

///////////////////////////////////////
// Connect                           //
///////////////////////////////////////
connect_param
: fieldparam=field 'TO' anotherfieldparam=field
;

///////////////////////////////////////
// Compare                           //
///////////////////////////////////////
compare_param
: fieldparam=field opparam=compare_operator anotherfieldparam=field
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
// Compute                           //
///////////////////////////////////////
compute_param
: fieldparam=field '<-' formulaparam=formula
;

///////////////////////////////////////
//                                   //
// Common                            //
//                                   //
///////////////////////////////////////
field returns [String tablename, String fieldname]
: '[' tablenameparam=ID '].[' fieldnameparam=ID ']' { $fieldname = $fieldnameparam.text; 
                                                      $tablename = $tablenameparam.text; }
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
//: dataparam=date         { $val = $dataparam.val; }
: numparam=number        { $val = $numparam.val;  }
| stringparam=text       { $val = new StringValue($stringparam.text); }
;

//date returns [DateValue val]
//: yearparam=DATE_FIELD_FOUR monthparam=DATE_FIELD_TWO 
//  dayparam=DATE_FIELD_TWO
//    {
//      GregorianCalendar c = new GregorianCalendar();
//      c.set($yearparam.int, $monthparam.int, $dayparam.int, 0, 0, 0);
//      c.setTimeInMillis(0);
//      $val = new DateValue(c.getTime());
//    }
//;

text
: 
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