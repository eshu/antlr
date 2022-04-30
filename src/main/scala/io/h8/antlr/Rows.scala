package io.h8.antlr

import io.h8.antlr.antlr4.{RowsLexer, RowsParser}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object Rows:
  private val data =
    """
      |parrt,Terence Parr,101
      |tombu,Tom Burns,020
      |bke,Kevin Edgar,008
      |""".stripMargin.trim

  def main(args: Array[String]): Unit = 1 to 3 foreach col

  def col(n: Int): Unit =
    println(n)
    val lexer = RowsLexer(CharStreams.fromString(data))
    val tokens = CommonTokenStream(lexer)
    val parser = RowsParser(tokens, n)
    parser.setBuildParseTree(false)
    parser.file()
    println()


