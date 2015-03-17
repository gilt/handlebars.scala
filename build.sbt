//gilt.GiltProject.jarSettings

name := "handlebars"

organization := "com.gilt"

scalaVersion := "2.11.6"

crossScalaVersions := Seq("2.11.6", "2.10.5")

libraryDependencies ++= Seq(
  "com.google.guava" % "guava" % "12.0",
  "org.slf4j" % "slf4j-api" % "1.6.4",
  "org.slf4j" % "slf4j-simple" % "1.6.4",
  "org.specs2" %% "specs2-core" % "3.0.1" % "test",
  "org.specs2" %% "specs2-matcher-extra" % "3.0.1" % "test"
)

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    // if scala 2.11+ is used, add dependency on scala-xml module
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.0.3",
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.3"
      )
    case _ =>
      libraryDependencies.value
  }
}

resolvers ++= Seq(
  "Sonatype.org Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype.org Releases" at "http://oss.sonatype.org/service/local/staging/deploy/maven2",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
)

scalacOptions += "-unchecked"

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://nexus.gilt.com/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("gilt.internal.snapshots" at nexus + "content/repositories/internal-snapshots")
  else
    Some("gilt.internal.releases"  at nexus + "content/repositories/internal-releases")
}

//For publishing / testing locally
//publishTo := Some(Resolver.file("m2",  new File(Path.userHome.absolutePath+"/.m2/repository")))

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))

homepage := Some(url("https://github.com/mwunsch/handlebars.scala"))

pomExtra := (
  <scm>
    <url>git@github.com:mwunsch/handlebars.scala.git</url>
    <connection>scm:git:git@github.com:mwunsch/handlebars.scala.git</connection>
  </scm>
  <developers>
    <developer>
      <id>mwunsch</id>
      <name>Mark Wunsch</name>
      <url>http://markwunsch.com/</url>
    </developer>
  </developers>)