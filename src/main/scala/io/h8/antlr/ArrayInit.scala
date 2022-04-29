package io.h8.antlr

import org.antlr.v4.runtime.{ANTLRInputStream, CharStreams, CommonTokenStream}
import io.h8.antlr.antlr4.ArrayInitLexer
import io.h8.antlr.antlr4.ArrayInitParser
import io.h8.antlr.antlr4.ArrayInitBaseListener
import org.antlr.v4.runtime.tree.ParseTreeWalker

object ArrayInit:
    def main(args: Array[String]): Unit =
        println("ArrayInit")
        printTree()
        translate()

    def printTree(): Unit =
        val input = CharStreams.fromString("{1, {2, 3}, 4}")
        // val input = CharStreams.fromString("{1,2")
        val lexer = ArrayInitLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = ArrayInitParser(tokens)
        val tree = parser.init
        println(tree.toStringTree(parser))


    def translate(): Unit =
        val input = CharStreams.fromString("{99, 3, 451}")
        val lexer = ArrayInitLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = ArrayInitParser(tokens)
        val tree = parser.init
        val walker = ParseTreeWalker()
        walker.walk(ShortToUnicodeString(), tree)
        println()

    class ShortToUnicodeString extends ArrayInitBaseListener:
        override def enterInit(ctx: ArrayInitParser.InitContext): Unit =
            print('"')

        override def exitInit(ctx: ArrayInitParser.InitContext): Unit =
            print('"')

        override def enterValue(ctx: ArrayInitParser.ValueContext): Unit =
            val value = Integer.parseInt(ctx.INT.getText)
            print(f"\\u$value%04x")
        
    