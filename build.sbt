organization := "com.github.masahitojp"

name := "abc"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.2"

seq(webSettings :_*)

classpathTypes ~= (_ + "orbit")

libraryDependencies ++= Seq(
  "org.scalatra" % "scalatra" % "2.1.1",
  "org.scalatra" % "scalatra-scalate" % "2.1.1",
  "net.liftweb" % "lift-json_2.9.1" % "2.4",
  "org.scalatra" % "scalatra-swagger" % "2.1.1",
  "org.scalatra" % "scalatra-lift-json" % "2.1.1",
  "org.scalatra" % "scalatra-scalatest" % "2.1.1" % "test",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar")),
  "ch.qos.logback" % "logback-classic" % "1.0.7" % "runtime",
  "org.scalaquery" % "scalaquery_2.9.1-1" % "0.10.0-M1",
  "com.h2database" % "h2" % "1.3.168",
  "com.jolbox" % "bonecp" % "0.7.1.RELEASE"
)
