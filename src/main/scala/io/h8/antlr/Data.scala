package io.h8.antlr

import io.h8.antlr.Rows.data
import io.h8.antlr.antlr4.{DataLexer, DataParser}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object Data:
  private val data = "2 9 10 3 1 2 3"

  def main(args: Array[String]): Unit =
    val lexer = DataLexer(CharStreams.fromString(data))
    val tokens = CommonTokenStream(lexer)
    val parser = DataParser(tokens)
    println(parser.file.toStringTree(parser))
