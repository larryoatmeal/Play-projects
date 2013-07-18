
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object dashboard extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Seq[Project],Seq[scala.Tuple2[Task, Project]],User,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(projects: Seq[Project], todoTasks: Seq[(Task,Project)], user: User):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.70*/("""

"""),_display_(Seq[Any](/*3.2*/main(projects, user)/*3.22*/{_display_(Seq[Any](format.raw/*3.23*/("""
    
    <header>
        <hgroup>
            <h1>Dashboard</h1>
            <h2>Tasks over all projects</h2>
        </hgroup>
    </header>
    
    <article  class="tasks">
        """),_display_(Seq[Any](/*13.10*/todoTasks/*13.19*/.groupBy(_._2).map/*13.37*/ {/*14.13*/case (project, projectTasks) =>/*14.44*/ {_display_(Seq[Any](format.raw/*14.46*/("""
                <div class="folder" data-folder-id=""""),_display_(Seq[Any](/*15.54*/project/*15.61*/.id)),format.raw/*15.64*/("""">
                    <header>
                        <h3><a href="#"""),_display_(Seq[Any](/*17.40*/routes/*17.46*/.Tasks.index(project.id.get))),format.raw/*17.74*/("""">"""),_display_(Seq[Any](/*17.77*/project/*17.84*/.name)),format.raw/*17.89*/("""</a></h3>
                        <span class="loader">Loading</span>
                    </header>
                    <ul class="list">
                        """),_display_(Seq[Any](/*21.26*/projectTasks/*21.38*/.map/*21.42*/ {/*22.29*/case (task, _) =>/*22.46*/ {_display_(Seq[Any](format.raw/*22.48*/("""
                                """),_display_(Seq[Any](/*23.34*/tasks/*23.39*/.item(task, isEditable = false))),format.raw/*23.70*/("""
                            """)))}})),format.raw/*25.26*/("""
                    </ul>
                </div>
            """)))}})),format.raw/*29.10*/("""
    </article>
    
""")))})),format.raw/*32.2*/("""

"""))}
    }
    
    def render(projects:Seq[Project],todoTasks:Seq[scala.Tuple2[Task, Project]],user:User): play.api.templates.Html = apply(projects,todoTasks,user)
    
    def f:((Seq[Project],Seq[scala.Tuple2[Task, Project]],User) => play.api.templates.Html) = (projects,todoTasks,user) => apply(projects,todoTasks,user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Jul 18 11:04:15 PDT 2013
                    SOURCE: /Users/lwang/workspace/samples/zentasks/app/views/dashboard.scala.html
                    HASH: 3d14d891d26d54f2ff121e8f2db74398b0f45581
                    MATRIX: 553->1|698->69|735->72|763->92|801->93|1024->280|1042->289|1069->307|1080->322|1120->353|1160->355|1250->409|1266->416|1291->419|1398->490|1413->496|1463->524|1502->527|1518->534|1545->539|1744->702|1765->714|1778->718|1789->750|1815->767|1855->769|1925->803|1939->808|1992->839|2055->895|2151->968|2204->990
                    LINES: 19->1|22->1|24->3|24->3|24->3|34->13|34->13|34->13|34->14|34->14|34->14|35->15|35->15|35->15|37->17|37->17|37->17|37->17|37->17|37->17|41->21|41->21|41->21|41->22|41->22|41->22|42->23|42->23|42->23|43->25|46->29|49->32
                    -- GENERATED --
                */
            