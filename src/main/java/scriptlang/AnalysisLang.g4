grammar AnalysisLang;

@header {
package scriptlang;
  
import parsers.*;
import operations.FilterOperation;
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
// Code                             //
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
: 
;

///////////////////////////////////////
//                                   //
// Common                            //
//                                   //
///////////////////////////////////////
field
: '[' field_name=ID ']'
;

number
: num=NUMBER
;

compare_operator
: op=EQ
| op=NEQ
| op=GEQ
| op=G
| op=LEQ
| op=L
;

calc_operator
: op=MULTIPLY
| op=DIVIDE
| op=PLUS
| op=MINUS
| op=MODULO
;

formula
: fieldparam=field opparam=calc_operator anotherfieldparam=field
| fieldparam=field opparam=calc_operator valueparam=number
| fieldparam=field opparam=calc_operator formulaparam=formula
;

condition
: opparam=compare_operator valueparam=value
| conparamone=condition 'AND' conparamtwo=condition
;

range
: '>' g=value 'AND' '<' l=value
;

value
: date
| number
| text
;

date
: NUMCHAR NUMCHAR NUMCHAR NUMCHAR DATE_FIELD_SEP
  NUMCHAR NUMCHAR DATE_FIELD_SEP
  NUMCHAR NUMCHAR
;

text
: (ALPHACHAR | NUMCHAR)+
;

///////////////////////////////////////
//                                   //
// LEXER RULES                       //
//                                   //
///////////////////////////////////////

ID  : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

DATE_FIELD_SEP
: '\\'
;

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

ALPHACHAR
: (('a'..'z' | 'A'..'Z') ~('0'..'9'))
;

NUMCHAR
: '0'..'9'
;

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