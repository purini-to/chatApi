name := """ChatApi"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.33",
  "com.edulify" %% "play-hikaricp" % "1.5.0",
  "org.mindrot" % "jbcrypt" % "0.3m"
)
