grammar Rows;

@parser::members {
  int col;

  public RowsParser(TokenStream input, int col) {
    this(input);
    this.col = col;
  }
}

file: (row (NL|EOF))+;

row
locals [int i = 0]
  :( STUFF
      {
      $i++;
      if ($i == col) System.out.println($STUFF.text);
      }
   )+;

SEP: ',' -> skip;
NL: [\r\n];
STUFF: ~[,\r\n]+;
