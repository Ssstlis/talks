import com.softwaremill.clippy.ClippySbtPlugin.autoImport.clippyColorsEnabled

lazy val talk = project
  .in(file("15-10-19-kinoplan"))
  .settings(
    addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.6"),

    scalaVersion := "2.12.10",
    scalacOptions := Seq(
      "-language:implicitConversions",
      "-language:higherKinds",
      "-language:existentials",
      "-Ywarn-unused",
      "-Ypartial-unification",
      "-Ywarn-dead-code",
      "-Xlint:-adapted-args,_",
      "-deprecation",
      "-unchecked",
      "-feature",
      "-encoding",
      "utf8"
    ),
    cancelable in Global := true,
    clippyColorsEnabled := true,
    version := "0.1.0",
    fork in run := true,
    sources in(Compile, doc) := Seq.empty,
    publishArtifact in(Compile, packageDoc) := false,
    name := "15-10-19-kinoplan",
    libraryDependencies ++= Seq(
      "org.scala-lang" %  "scala-library" % "2.12.10",
      "org.typelevel"  %% "cats-effect"   % "2.0.0"
    )
  )
