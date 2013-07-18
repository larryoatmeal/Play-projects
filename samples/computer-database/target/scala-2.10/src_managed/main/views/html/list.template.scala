
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
object list extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Page[scala.Tuple2[Computer, Option[Company]]],Int,String,play.api.mvc.Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(currentPage: Page[(Computer, Option[Company])], currentOrderBy: Int, currentFilter: String)(implicit flash: play.api.mvc.Flash):play.api.templates.Html = {
        _display_ {
def /*16.2*/header/*16.8*/(orderBy: Int, title: String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*16.41*/("""
    <th class="col"""),_display_(Seq[Any](/*17.20*/orderBy)),format.raw/*17.27*/(""" header """),_display_(Seq[Any](/*17.36*/if(scala.math.abs(currentOrderBy) == orderBy){/*17.83*/{if(currentOrderBy < 0) "headerSortDown" else "headerSortUp"}})),format.raw/*17.144*/("""">
        <a href=""""),_display_(Seq[Any](/*18.19*/link(0, Some(orderBy)))),format.raw/*18.41*/("""">"""),_display_(Seq[Any](/*18.44*/title)),format.raw/*18.49*/("""</a>
    </th>
""")))};def /*6.2*/link/*6.6*/(newPage: Int, newOrderBy: Option[Int] = None) = {{
    routes.Application.list(newPage, newOrderBy.map { orderBy =>
        if(orderBy == scala.math.abs(currentOrderBy)) -currentOrderBy else orderBy
    }.getOrElse(currentOrderBy), currentFilter)
    
}};
Seq[Any](format.raw/*1.130*/("""

"""),format.raw/*5.42*/("""
"""),format.raw/*11.2*/("""

"""),format.raw/*15.37*/("""
"""),format.raw/*20.2*/("""

"""),_display_(Seq[Any](/*22.2*/main/*22.6*/ {_display_(Seq[Any](format.raw/*22.8*/("""
    
    <h1>"""),_display_(Seq[Any](/*24.10*/Messages("computers.list.title", currentPage.total))),format.raw/*24.61*/("""</h1>

    """),_display_(Seq[Any](/*26.6*/flash/*26.11*/.get("success").map/*26.30*/ { message =>_display_(Seq[Any](format.raw/*26.43*/("""
        <div class="alert-message warning">
            <strong>Done!</strong> """),_display_(Seq[Any](/*28.37*/message)),format.raw/*28.44*/("""
        </div>
    """)))})),format.raw/*30.6*/("""

    <div id="actions">
        
        """),_display_(Seq[Any](/*34.10*/helper/*34.16*/.form(action=routes.Application.list())/*34.55*/ {_display_(Seq[Any](format.raw/*34.57*/("""
            <input type="search" id="searchbox" name="f" value=""""),_display_(Seq[Any](/*35.66*/currentFilter)),format.raw/*35.79*/("""" placeholder="Filter by computer name...">
            <input type="submit" id="searchsubmit" value="Filter by name" class="btn primary">
        """)))})),format.raw/*37.10*/("""
        
        <a class="btn success" id="add" href=""""),_display_(Seq[Any](/*39.48*/routes/*39.54*/.Application.create())),format.raw/*39.75*/("""">Add a new computer</a>
        
    </div>
    
    """),_display_(Seq[Any](/*43.6*/Option(currentPage.items)/*43.31*/.filterNot(_.isEmpty).map/*43.56*/ { computers =>_display_(Seq[Any](format.raw/*43.71*/("""
        
        <table class="computers zebra-striped">
            <thead>
                <tr>
                    """),_display_(Seq[Any](/*48.22*/header(2, "Computer name"))),format.raw/*48.48*/("""
                    """),_display_(Seq[Any](/*49.22*/header(3, "Introduced"))),format.raw/*49.45*/("""
                    """),_display_(Seq[Any](/*50.22*/header(4, "Discontinued"))),format.raw/*50.47*/("""
                    """),_display_(Seq[Any](/*51.22*/header(5, "Company"))),format.raw/*51.42*/("""
                </tr>
            </thead>
            <tbody>

                """),_display_(Seq[Any](/*56.18*/computers/*56.27*/.map/*56.31*/ {/*57.21*/case (computer, company) =>/*57.48*/ {_display_(Seq[Any](format.raw/*57.50*/("""
                        <tr>
                            <td><a href=""""),_display_(Seq[Any](/*59.43*/routes/*59.49*/.Application.edit(computer.id.get))),format.raw/*59.83*/("""">"""),_display_(Seq[Any](/*59.86*/computer/*59.94*/.name)),format.raw/*59.99*/("""</a></td>
                            <td>
                                """),_display_(Seq[Any](/*61.34*/computer/*61.42*/.introduced.map(_.format("dd MMM yyyy")).getOrElse/*61.92*/ {_display_(Seq[Any](format.raw/*61.94*/(""" <em>-</em> """)))})),format.raw/*61.107*/("""
                            </td>
                            <td>
                                """),_display_(Seq[Any](/*64.34*/computer/*64.42*/.discontinued.map(_.format("dd MMM yyyy")).getOrElse/*64.94*/ {_display_(Seq[Any](format.raw/*64.96*/(""" <em>-</em> """)))})),format.raw/*64.109*/("""
                            </td>
                            <td>
                                """),_display_(Seq[Any](/*67.34*/company/*67.41*/.map(_.name).getOrElse/*67.63*/ {_display_(Seq[Any](format.raw/*67.65*/(""" <em>-</em> """)))})),format.raw/*67.78*/("""
                            </td>
                        </tr>
                    """)))}})),format.raw/*71.18*/("""

            </tbody>
        </table>

        <div id="pagination" class="pagination">
            <ul>
                """),_display_(Seq[Any](/*78.18*/currentPage/*78.29*/.prev.map/*78.38*/ { page =>_display_(Seq[Any](format.raw/*78.48*/("""
                    <li class="prev">
                        <a href=""""),_display_(Seq[Any](/*80.35*/link(page))),format.raw/*80.45*/("""">&larr; Previous</a>
                    </li> 
                """)))}/*82.18*/.getOrElse/*82.28*/ {_display_(Seq[Any](format.raw/*82.30*/("""
                    <li class="prev disabled">
                        <a>&larr; Previous</a>
                    </li>
                """)))})),format.raw/*86.18*/("""
                <li class="current">
                    <a>Displaying """),_display_(Seq[Any](/*88.36*/(currentPage.offset + 1))),format.raw/*88.60*/(""" to """),_display_(Seq[Any](/*88.65*/(currentPage.offset + computers.size))),format.raw/*88.102*/(""" of """),_display_(Seq[Any](/*88.107*/currentPage/*88.118*/.total)),format.raw/*88.124*/("""</a>
                </li>
                """),_display_(Seq[Any](/*90.18*/currentPage/*90.29*/.next.map/*90.38*/ { page =>_display_(Seq[Any](format.raw/*90.48*/("""
                    <li class="next">
                        <a href=""""),_display_(Seq[Any](/*92.35*/link(page))),format.raw/*92.45*/("""">Next &rarr;</a>
                    </li> 
                """)))}/*94.18*/.getOrElse/*94.28*/ {_display_(Seq[Any](format.raw/*94.30*/("""
                    <li class="next disabled">
                        <a>Next &rarr;</a>
                    </li>
                """)))})),format.raw/*98.18*/("""
            </ul>
        </div>
        
    """)))}/*102.6*/.getOrElse/*102.16*/ {_display_(Seq[Any](format.raw/*102.18*/("""
        
        <div class="well">
            <em>Nothing to display</em>
        </div>
        
    """)))})),format.raw/*108.6*/("""

        
""")))})),format.raw/*111.2*/("""

            """))}
    }
    
    def render(currentPage:Page[scala.Tuple2[Computer, Option[Company]]],currentOrderBy:Int,currentFilter:String,flash:play.api.mvc.Flash): play.api.templates.Html = apply(currentPage,currentOrderBy,currentFilter)(flash)
    
    def f:((Page[scala.Tuple2[Computer, Option[Company]]],Int,String) => (play.api.mvc.Flash) => play.api.templates.Html) = (currentPage,currentOrderBy,currentFilter) => (flash) => apply(currentPage,currentOrderBy,currentFilter)(flash)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Jul 15 10:54:08 PDT 2013
                    SOURCE: /Users/lwang/play/samples/scala/computer-database/app/views/list.scala.html
                    HASH: 142e3999d5abf786dbccf1c41b89ef6286baed7a
                    MATRIX: 573->1|762->628|776->634|873->667|929->687|958->694|1003->703|1058->750|1143->811|1200->832|1244->854|1283->857|1310->862|1348->258|1359->262|1644->129|1673->256|1701->516|1731->626|1759->878|1797->881|1809->885|1848->887|1899->902|1972->953|2019->965|2033->970|2061->989|2112->1002|2229->1083|2258->1090|2310->1111|2389->1154|2404->1160|2452->1199|2492->1201|2594->1267|2629->1280|2809->1428|2902->1485|2917->1491|2960->1512|3050->1567|3084->1592|3118->1617|3171->1632|3327->1752|3375->1778|3433->1800|3478->1823|3536->1845|3583->1870|3641->1892|3683->1912|3801->1994|3819->2003|3832->2007|3843->2031|3879->2058|3919->2060|4027->2132|4042->2138|4098->2172|4137->2175|4154->2183|4181->2188|4293->2264|4310->2272|4369->2322|4409->2324|4455->2337|4592->2438|4609->2446|4670->2498|4710->2500|4756->2513|4893->2614|4909->2621|4940->2643|4980->2645|5025->2658|5144->2762|5304->2886|5324->2897|5342->2906|5390->2916|5499->2989|5531->2999|5616->3065|5635->3075|5675->3077|5845->3215|5954->3288|6000->3312|6041->3317|6101->3354|6143->3359|6164->3370|6193->3376|6273->3420|6293->3431|6311->3440|6359->3450|6468->3523|6500->3533|6581->3595|6600->3605|6640->3607|6806->3741|6873->3789|6893->3799|6934->3801|7072->3907|7116->3919
                    LINES: 19->1|21->16|21->16|23->16|24->17|24->17|24->17|24->17|24->17|25->18|25->18|25->18|25->18|27->6|27->6|33->1|35->5|36->11|38->15|39->20|41->22|41->22|41->22|43->24|43->24|45->26|45->26|45->26|45->26|47->28|47->28|49->30|53->34|53->34|53->34|53->34|54->35|54->35|56->37|58->39|58->39|58->39|62->43|62->43|62->43|62->43|67->48|67->48|68->49|68->49|69->50|69->50|70->51|70->51|75->56|75->56|75->56|75->57|75->57|75->57|77->59|77->59|77->59|77->59|77->59|77->59|79->61|79->61|79->61|79->61|79->61|82->64|82->64|82->64|82->64|82->64|85->67|85->67|85->67|85->67|85->67|88->71|95->78|95->78|95->78|95->78|97->80|97->80|99->82|99->82|99->82|103->86|105->88|105->88|105->88|105->88|105->88|105->88|105->88|107->90|107->90|107->90|107->90|109->92|109->92|111->94|111->94|111->94|115->98|119->102|119->102|119->102|125->108|128->111
                    -- GENERATED --
                */
            