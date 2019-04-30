ThisBuild / organization := "com.thoughtworks.binding"

name := "NextAnimationFrame"

enablePlugins(ScalaJSPlugin)

libraryDependencies += "com.thoughtworks.binding" %%% "dom" % "11.7.0"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.7"
