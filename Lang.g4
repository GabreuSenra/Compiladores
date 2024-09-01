grammar Lang;

program
    : def+
    ;
def
    : data | fun 
    ;
data    
    : 'data' ID '{' decl+ '}' 
    ;
decl    
    : ID '::' type ';' 
    ;
fun
    : ID '(' params? ')' (':' type (',' type)*)? '{' cmd+ '}'
    ;
params  
    : param (',' param)* 
    ;
param   
    : ID '::' type 
    ;
type    
    : type '[' ']' 
    | btype
    ;
btype   
    : 'Int' 
    | 'Char' 
    | 'Bool' 
    | 'Float' 
    | ID 
    ;
cmd
    : '{' cmd* '}'
    | 'if' '(' exp ')' cmd
    | 'if' '(' exp ')' cmd ('else' cmd)?
    | 'iterate' '(' exp ')' cmd
    | 'read' lvalue ';'
    | 'print' exp ';'
    | 'return' exp (',' exp)* ';'
    | lvalue '=' exp ';'
    | ID '(' exps? ')' '<' lvalue (',' lvalue)* '>' ';'
    ;
exp     
    : exp ('&&' | '<' | '==' | '!=' | '+' | '-' | '*' | '/' | '%' ) exp 
    | '!' exp 
    | '-' exp 
    | 'true' 
    | 'false' 
    | 'null' 
    | INT 
    | FLOAT 
    | CHAR 
    | lvalue 
    | '(' exp ')' 
    | 'new' type ('[' exp ']')? 
    | ID '(' exps? ')' ('[' exp ']')?
    ;
lvalue  
    : ID 
    | lvalue '[' exp ']' 
    | lvalue '.' ID ;
exps    
    : exp (',' exp)* 
    ;


EQ : '=' ;
EQEQ : '==';
DIFF : '!=';
PLUS : '+';
MINUS: '-';
MULT: '*';
DIV: '/';
MOD: '%';
GREATER: '>';
LESS: '<';
NOT: '!';
AND: '&&';
LP: '(';
RP: ')';
L_BRACKET: '[';
R_BRACKET: ']';
L_BRACE: '{';
R_BRACE: '}';
DOT: '.';
TYPE_DEF: '::';
RETURN_TYPE: ':';
COMMA: ',';
SEMI: ';';
NULL: 'null';

BOOLEAN: 'boolean';
ABSTRACT: 'abstract';
BREAK: 'break';
DATA_DEF: 'data';
RETURN: 'return';
INT_N: 'Int';
BOOL: 'Bool';
FLOAT_N: 'Float';
CHAR_N: 'Char';
BOOL_TRUE: 'true';
BOOL_FALSE: 'false';
IF: 'if';
ELSE: 'else';
PRINT: 'print';
READ: 'read';
NEW: 'new';
ITERATE: 'iterate';


InputCharacter : '[^\r|\n]';
FimDeLinha : '\r|\n|\r\n';
Branco : '\r|\n|\r\n |[ \t\f]';
ID      : [a-zA-Z][a-zA-Z0-9_]* ;
INT     : [0-9]+ ;
FLOAT   : [0-9]* '.' [0-9]+ ;
CHAR : '\'' ( '\\' . | ~('\\' | '\'') ) '\'' ;
WS      : [ \t\r\n]+ -> skip ;
COMMENT : '--' ~[\r\n]* -> skip ;
MLCOMMENT : '{-' .*? '-}' -> skip ;