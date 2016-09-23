name := """meetpoint-play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
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

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick"      % "3.1.0",
  "org.slf4j"          % "slf4j-nop"       % "1.6.4",
  "com.h2database"     % "h2"              % "1.4.191",
  "com.typesafe.play" %% "play-slick" % "2.0.2",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.2",
  "org.webjars.bower" % "bootstrap-sass" % "3.3.6"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

import org.irundaia.sbt.sass._

SassKeys.cssStyle := Maxified

SassKeys.generateSourceMaps := true

SassKeys.syntaxDetection := ForceScss
