package io.h8.antlr

import io.h8.antlr.antlr4.{ArrayInitLexer, ArrayInitParser, ExprLexer, ExprParser}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object Expr:
  def main(args: Array[String]): Unit =
    println("Expr")
    for expr <- List("193", "a = 5", "b = 6", "a + b * 2", "(1 + 2) * 3") do printTree(expr)
    printTree(
      """
        |193
        |a = 5
        |b = 6
        |a + b * 2
        |(1 + 2) * 3
        |""".stripMargin)

  def printTree(expr: String): Unit =
    val input = CharStreams.fromString(expr)
    val lexer = ExprLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = ExprParser(tokens)
    val tree = parser.prog
    println(tree.toStringTree(parser))
