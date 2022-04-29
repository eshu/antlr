val scala3Version = "3.1.2"

enablePlugins(Antlr4Plugin)

lazy val root = project
  .in(file("."))
  .settings(
    name := "antlr",
    version := "0.0.1-SNAPSHOT",

    scalaVersion := scala3Version,

    Antlr4 / antlr4Version := "4.10.1",
    Antlr4 / antlr4PackageName := Some("io.h8.antlr.antlr4"),

    libraryDependencies ++= Seq(
      "org.antlr" % "antlr4-runtime" % "4.10.1",
      "org.scalameta" %% "munit" % "0.7.29" % Test)
  )
