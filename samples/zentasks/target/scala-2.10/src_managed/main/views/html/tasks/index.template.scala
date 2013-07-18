
package views.html.tasks

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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Project,Seq[Task],Seq[User],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(project:Project, tasks: Seq[Task], team: Seq[User]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.54*/("""

<header>
    <hgroup>
        <h1>"""),_display_(Seq[Any](/*5.14*/project/*5.21*/.folder)),format.raw/*5.28*/("""</h1>
        <h2>"""),_display_(Seq[Any](/*6.14*/project/*6.21*/.name)),format.raw/*6.26*/("""</h2>
    </hgroup>
    <dl class="users">
        <dt>Project's team</dt>
        <dd>
            <div class="wrap">
                <h3>Team mates</h3>
                <div class="list">
                    """),_display_(Seq[Any](/*14.22*/team/*14.26*/.map/*14.30*/ { user =>_display_(Seq[Any](format.raw/*14.40*/("""
                        <dl data-user-id=""""),_display_(Seq[Any](/*15.44*/user/*15.48*/.email)),format.raw/*15.54*/("""">
                            <dt>"""),_display_(Seq[Any](/*16.34*/user/*16.38*/.name)),format.raw/*16.43*/(""" <span>("""),_display_(Seq[Any](/*16.52*/user/*16.56*/.email)),format.raw/*16.62*/(""")</span></dt>
                            <dd class="action">Action</dd>
                        </dl>
                    """)))})),format.raw/*19.22*/("""
                </div>
                <h3>Add a team mate</h3>
                <div class="addUserList">
                    """),_display_(Seq[Any](/*23.22*/User/*23.26*/.findAll.diff(team).map/*23.49*/ { user =>_display_(Seq[Any](format.raw/*23.59*/("""
                        <dl data-user-id=""""),_display_(Seq[Any](/*24.44*/user/*24.48*/.email)),format.raw/*24.54*/("""">
                            <dt>"""),_display_(Seq[Any](/*25.34*/user/*25.38*/.name)),format.raw/*25.43*/(""" <span>("""),_display_(Seq[Any](/*25.52*/user/*25.56*/.email)),format.raw/*25.62*/(""")</span></dt>
                            <dd class="action">Action</dd>
                        </dl>
                    """)))})),format.raw/*28.22*/("""
                </div>
            </div>
        </dd>
    </dl>
</header>
<article  class="tasks" id="tasks">
    """),_display_(Seq[Any](/*35.6*/tasks/*35.11*/.groupBy(_.folder).map/*35.33*/ {/*36.9*/case (folder, tasks) =>/*36.32*/ {_display_(Seq[Any](format.raw/*36.34*/("""
            """),_display_(Seq[Any](/*37.14*/views/*37.19*/.html.tasks.folder(folder, tasks))),format.raw/*37.52*/("""
        """)))}})),format.raw/*39.6*/("""
    <a href="#newFolder" class="new newFolder">New folder</a>
</article>

"""))}
    }
    
    def render(project:Project,tasks:Seq[Task],team:Seq[User]): play.api.templates.Html = apply(project,tasks,team)
    
    def f:((Project,Seq[Task],Seq[User]) => play.api.templates.Html) = (project,tasks,team) => apply(project,tasks,team)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Jul 18 11:04:15 PDT 2013
                    SOURCE: /Users/lwang/workspace/samples/zentasks/app/views/tasks/index.scala.html
                    HASH: 91ebff093df9fb8024c8f9944233c8c63c21cc7d
                    MATRIX: 532->1|661->53|733->90|748->97|776->104|830->123|845->130|871->135|1118->346|1131->350|1144->354|1192->364|1272->408|1285->412|1313->418|1385->454|1398->458|1425->463|1470->472|1483->476|1511->482|1667->606|1831->734|1844->738|1876->761|1924->771|2004->815|2017->819|2045->825|2117->861|2130->865|2157->870|2202->879|2215->883|2243->889|2399->1013|2552->1131|2566->1136|2597->1158|2607->1169|2639->1192|2679->1194|2729->1208|2743->1213|2798->1246|2840->1262
                    LINES: 19->1|22->1|26->5|26->5|26->5|27->6|27->6|27->6|35->14|35->14|35->14|35->14|36->15|36->15|36->15|37->16|37->16|37->16|37->16|37->16|37->16|40->19|44->23|44->23|44->23|44->23|45->24|45->24|45->24|46->25|46->25|46->25|46->25|46->25|46->25|49->28|56->35|56->35|56->35|56->36|56->36|56->36|57->37|57->37|57->37|58->39
                    -- GENERATED --
                */
            