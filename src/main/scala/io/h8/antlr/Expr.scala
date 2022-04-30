package io.h8.antlr

import io.h8.antlr.antlr4.{ArrayInitLexer, ArrayInitParser, ExprBaseVisitor, ExprLexer, ExprParser}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

import scala.collection.mutable

object Expr:
  def main(args: Array[String]): Unit =
    println("Expr")
    for expr <- List("193", "a = 5", "b = 6", "a + b * 2", "(1 + 2) * 3") do printTree(expr)
    printTree("""
        |193
        |a = 5
        |b = 6
        |a + b * 2
        |(1 + 2) * 3
        |""".stripMargin)
    evalTree("""
        |193
        |a = 5
        |b = 6
        |a + b * 2
        |(1 + 2) * 3
        |""".stripMargin)

  private def parse(expr: String): ExprParser =
    val input = CharStreams.fromString(expr)
    val lexer = ExprLexer(input)
    val tokens = CommonTokenStream(lexer)
    ExprParser(tokens)

  def printTree(expr: String): Unit =
    val parser = parse(expr)
    val tree = parser.prog
    println(tree.toStringTree(parser))

  def evalTree(expr: String): Unit =
    val parser = parse(expr)
    val tree = parser.prog
    val eval = new EvalVisitor()
    eval.visit(tree)

  class EvalVisitor extends ExprBaseVisitor[Int]:
    private val memory = mutable.HashMap[String, Int]()

    override def visitAssign(ctx: ExprParser.AssignContext): Int =
      val id = ctx.ID.getText
      val value = visit(ctx.expr())
      memory(id) = value
      value

    override def visitPrintExpr(ctx: ExprParser.PrintExprContext): Int =
      println(visit(ctx.expr()))
      0

    override def visitInt(ctx: ExprParser.IntContext): Int = Integer.parseInt(ctx.INT().getText)

    override def visitId(ctx: ExprParser.IdContext): Int = memory.getOrElse(ctx.ID().getText, 0)

    override def visitMulDiv(ctx: ExprParser.MulDivContext): Int =
      val left = visit(ctx.expr(0))
      val right = visit(ctx.expr(1))
      ctx.op.getType match
        case ExprParser.MUL => left * right
        case ExprParser.DIV => left / right
        case op             => throw IllegalStateException(s"Unexpected operation $op")

    override def visitAddSub(ctx: ExprParser.AddSubContext): Int =
      val left = visit(ctx.expr(0))
      val right = visit(ctx.expr(1))
      ctx.op.getType match
        case ExprParser.ADD => left + right
        case ExprParser.SUB => left - right
        case op             => throw IllegalStateException(s"Unexpected operation $op")

    override def visitParens(ctx: ExprParser.ParensContext): Int =
      visit(ctx.expr())
