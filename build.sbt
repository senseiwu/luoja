name := """meetpoint-play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "org.scalatest"       %%  "scalatest"     % "2.2.5"  % "test"
)

libraryDependencies ++= {
  Seq(
    "org.mongodb.scala" %% "mongo-scala-driver" % "1.0.1"
  )
}

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
