grammar Java;

classDeclaration: 'class' Identifier typeParameters? ('extends' type)? ('implements' typeList) classBody;

typeParameters: '<' typeList '>';

typeList: type (',' type)*;

type: Identifier;

classBody: '{' '}';

Identifier : [a-zA-Z_]([a-zA-Z_0-9])*;