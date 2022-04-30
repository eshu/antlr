grammar Expr;

import CommonLexerRules;

prog:	stat* ;

stat:   expr (NEWLINE | EOF) # printExpr
    |   ID '=' expr (NEWLINE | EOF) # assign
    |   NEWLINE # blank
    ;

expr:	expr op=(MUL|DIV) expr # MulDiv
    |	expr op=(ADD|SUB) expr # AddSub
    |	INT # int
    |   ID # id
    |	'(' expr ')' # parens
    ;

MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';