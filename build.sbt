
sbtPlugin := true

name := "sbt-site"

organization := "com.typesafe.sbt"

version := "1.3.0-SNAPSHOT"

licenses += ("BSD 3-Clause", url("http://opensource.org/licenses/BSD-3-Clause"))
//#scm-info
scmInfo := Some(ScmInfo(url("https://github.com/sbt/sbt-site"), "git@github.com:sbt/sbt-site.git"))
//#scm-info

scalacOptions ++= Seq("-deprecation", "-unchecked")

resolvers += Resolver.sonatypeRepo("releases")

val unfilteredVersion = "0.9.1"

libraryDependencies ++= Seq(
  "ws.unfiltered"     %% "unfiltered-directives" % unfilteredVersion,
  "ws.unfiltered"     %% "unfiltered-filter"     % unfilteredVersion,
  "ws.unfiltered"     %% "unfiltered-jetty"      % unfilteredVersion,
  "ws.unfiltered"     %% "unfiltered-specs2"     % unfilteredVersion % Test,
  "org.foundweekends" %% "pamflet-library"       % "0.7.1",
  "org.yaml"           % "snakeyaml"             % "1.13",
  "com.typesafe"       % "config"                % "1.2.1", // Last version to support Java 1.6
  "org.asciidoctor"    % "asciidoctorj"          % "1.5.4"
)

libraryDependencies ++= {
  if((sbtVersion in pluginCrossBuild).value.startsWith("0.13")) {
    Seq(
      Defaults.sbtPluginExtra(
        "com.lightbend.paradox" % "sbt-paradox" % "0.2.12",
        (sbtBinaryVersion in pluginCrossBuild).value,
        (scalaBinaryVersion in pluginCrossBuild).value
      ),
      Defaults.sbtPluginExtra(
        "org.planet42" % "laika-sbt" % "0.6.0",
        (sbtBinaryVersion in pluginCrossBuild).value,
        (scalaBinaryVersion in pluginCrossBuild).value
      )
    )
  } else Nil
}

enablePlugins(ScriptedPlugin)

/*
scripted := {
  if((sbtVersion in pluginCrossBuild).value.startsWith("0.13")) {
    scripted.evaluated
  } else {
    scripted.toTask{
      file("src/sbt-test").listFiles.filterNot { dir =>
        Set("paradox", "laika").contains(dir.getName)
      }.map(_ + "/" + "*").mkString(" ", " ", "")
    }.value
  }
}
*/

scriptedLaunchOpts += "-Dproject.version="+version.value

// scriptedBufferLog := false
