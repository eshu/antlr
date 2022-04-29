grammar Expr;

import CommonLexerRules;

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
