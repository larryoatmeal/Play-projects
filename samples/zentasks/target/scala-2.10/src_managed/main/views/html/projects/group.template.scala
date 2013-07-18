
package views.html.projects

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
object group extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Seq[Project],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(group: String, projects: Seq[Project] = Nil):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.47*/("""

<li data-group=""""),_display_(Seq[Any](/*3.18*/group)),format.raw/*3.23*/("""">
    <span class="toggle"></span>
    <h4 class="groupName">"""),_display_(Seq[Any](/*5.28*/group)),format.raw/*5.33*/("""</h4>
    <span class="loader">Loading</span>
    <dl class="options">
        <dt>Options</dt>
        <dd>
            <button class="newProject">New project</button>
            <button class="deleteGroup">Remove group</button>
        </dd>
    </dl>
    <ul>
        """),_display_(Seq[Any](/*15.10*/projects/*15.18*/.map/*15.22*/ { project =>_display_(Seq[Any](format.raw/*15.35*/("""
            """),_display_(Seq[Any](/*16.14*/views/*16.19*/.html.projects.item(project))),format.raw/*16.47*/("""
        """)))})),format.raw/*17.10*/("""
    </ul>
</li>
"""))}
    }
    
    def render(group:String,projects:Seq[Project]): play.api.templates.Html = apply(group,projects)
    
    def f:((String,Seq[Project]) => play.api.templates.Html) = (group,projects) => apply(group,projects)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Jul 18 11:04:15 PDT 2013
                    SOURCE: /Users/lwang/workspace/samples/zentasks/app/views/projects/group.scala.html
                    HASH: 68a575de1dec1d7205f53bdcec2ab19bcefadac6
                    MATRIX: 527->1|649->46|703->65|729->70|827->133|853->138|1162->411|1179->419|1192->423|1243->436|1293->450|1307->455|1357->483|1399->493
                    LINES: 19->1|22->1|24->3|24->3|26->5|26->5|36->15|36->15|36->15|36->15|37->16|37->16|37->16|38->17
                    -- GENERATED --
                */
            