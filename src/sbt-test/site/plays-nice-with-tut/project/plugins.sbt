addSbtPlugin("com.typesafe.sbt" % "sbt-site" % sys.props("project.version"))

libraryDependencies += {
  val v = if(sbtVersion.value.startsWith("0.13")) "0.5.2" else "0.6.0"
  Defaults.sbtPluginExtra(
    "org.tpolecat" % "tut-plugin" % v,
    sbtBinaryVersion.value,
    scalaBinaryVersion.value
  )
}
