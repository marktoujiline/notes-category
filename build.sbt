import Dependencies._
import microsites._

ThisBuild / scalaVersion     := "2.12.9"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val micrositeSettings: Seq[Def.Setting[_]] = Seq(
      micrositeName := "Category Theory Notes",
      micrositeDescription := "All personal notes related to category theory",
      micrositeBaseUrl := "notes-category",
      micrositeDocumentationUrl := "docs",
      micrositeGithubOwner := "marktoujiline",
      micrositeGithubRepo := "notes-category",
      micrositeConfigYaml := ConfigYml(
        yamlPath = Some((resourceDirectory in Compile).value / "microsite" / "myconfig.yml")
      ),
      includeFilter in makeSite := "*.html" | "*.css" | "*.png" | "*.jpg" | "*.gif" | "*.js" | "*.swf" | "*.md" | "*.svg"
    )


lazy val docs = (project in file("docs"))
  .settings(moduleName := "docs")
  .settings(micrositeSettings: _*)
  .enablePlugins(MicrositesPlugin)

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
