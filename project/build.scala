import sbt._
import Keys._

object HarateBuild extends Build {
  val Organization = "xyz.unnati"
  val Name = "harate"
  val Version = "0.1.0"
  val ScalaVersion = "2.11.8"

  lazy val project = Project(
    "harate",
    file("."),
    settings = Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
      libraryDependencies ++= Seq(
        "com.typesafe" % "config" % "1.3.0",
        "ch.qos.logback" % "logback-classic" % "1.1.5" % "runtime",
        "org.clapper" %% "grizzled-slf4j" % "1.0.4",
        "org.mongodb" % "mongo-java-driver" % "3.3.0",
        "org.twitter4j" % "twitter4j-stream" % "4.0.4"
      )))

}
