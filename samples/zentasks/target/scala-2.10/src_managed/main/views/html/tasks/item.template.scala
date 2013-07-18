
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
object item extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Task,Boolean,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(task: Task, isEditable: Boolean = true):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.42*/("""

<li data-task-id=""""),_display_(Seq[Any](/*3.20*/task/*3.24*/.id)),format.raw/*3.27*/("""">
    
    """),_display_(Seq[Any](/*5.6*/if(isEditable)/*5.20*/ {_display_(Seq[Any](format.raw/*5.22*/("""
        <input class="done" type="checkbox" """),_display_(Seq[Any](/*6.46*/(if(task.done) "checked"))),format.raw/*6.71*/(""" />
    """)))})),format.raw/*7.6*/("""
    
    <h4>"""),_display_(Seq[Any](/*9.10*/task/*9.14*/.title)),format.raw/*9.20*/("""</h4>
    
    """),_display_(Seq[Any](/*11.6*/task/*11.10*/.dueDate.map/*11.22*/ { date =>_display_(Seq[Any](format.raw/*11.32*/("""
        <time datetime=""""),_display_(Seq[Any](/*12.26*/date)),format.raw/*12.30*/("""">"""),_display_(Seq[Any](/*12.33*/date/*12.37*/.format("MMM dd yyyy"))),format.raw/*12.59*/("""</time>
    """)))})),format.raw/*13.6*/("""
    
    """),_display_(Seq[Any](/*15.6*/task/*15.10*/.assignedTo.map/*15.25*/ { user =>_display_(Seq[Any](format.raw/*15.35*/("""
        <span class="assignedTo">"""),_display_(Seq[Any](/*16.35*/user)),format.raw/*16.39*/("""</span>
    """)))})),format.raw/*17.6*/("""
    
    """),_display_(Seq[Any](/*19.6*/if(isEditable)/*19.20*/ {_display_(Seq[Any](format.raw/*19.22*/("""
        <a class="deleteTask" href=""""),_display_(Seq[Any](/*20.38*/routes/*20.44*/.Tasks.delete(task.id.get))),format.raw/*20.70*/("""">Delete task</a>
        <span class="loader">Loading</span>
    """)))})),format.raw/*22.6*/("""
    
</li>
"""))}
    }
    
    def render(task:Task,isEditable:Boolean): play.api.templates.Html = apply(task,isEditable)
    
    def f:((Task,Boolean) => play.api.templates.Html) = (task,isEditable) => apply(task,isEditable)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Jul 18 11:04:15 PDT 2013
                    SOURCE: /Users/lwang/workspace/samples/zentasks/app/views/tasks/item.scala.html
                    HASH: 943f4f9ab8726c332f7abd41cb4625a8b1c57840
                    MATRIX: 516->1|633->41|689->62|701->66|725->69|772->82|794->96|833->98|914->144|960->169|999->178|1049->193|1061->197|1088->203|1139->219|1152->223|1173->235|1221->245|1283->271|1309->275|1348->278|1361->282|1405->304|1449->317|1495->328|1508->332|1532->347|1580->357|1651->392|1677->396|1721->409|1767->420|1790->434|1830->436|1904->474|1919->480|1967->506|2065->573
                    LINES: 19->1|22->1|24->3|24->3|24->3|26->5|26->5|26->5|27->6|27->6|28->7|30->9|30->9|30->9|32->11|32->11|32->11|32->11|33->12|33->12|33->12|33->12|33->12|34->13|36->15|36->15|36->15|36->15|37->16|37->16|38->17|40->19|40->19|40->19|41->20|41->20|41->20|43->22
                    -- GENERATED --
                */
            