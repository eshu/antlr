grammar Expr;
prog:	stat* ;
stat:   expr (NEWLINE | EOF)
    |   ID '=' expr (NEWLINE | EOF)
    |   NEWLINE
    ;
expr:	expr ('*'|'/') expr
    |	expr ('+'|'-') expr
    |	INT
    |   ID
    |	'(' expr ')'
    ;

ID      : [a-zA-Z]+;
NEWLINE : [\r\n]+ ;
INT     : [0-9]+ ;
WS      : [ \t]+ -> skip;
