val scala3Version = "3.6.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "simple_lang_parser",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
    javacOptions := Seq("--release", "17"),

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )

