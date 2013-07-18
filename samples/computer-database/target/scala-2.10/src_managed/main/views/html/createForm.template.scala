
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
object createForm extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[Computer],Seq[scala.Tuple2[String, String]],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(computerForm: Form[Computer], companies: Seq[(String, String)]):play.api.templates.Html = {
        _display_ {import helper._

implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.f) }};
Seq[Any](format.raw/*1.66*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*5.75*/(""" 

"""),_display_(Seq[Any](/*7.2*/main/*7.6*/ {_display_(Seq[Any](format.raw/*7.8*/("""
    
    <h1>Add a computer</h1>
    
    """),_display_(Seq[Any](/*11.6*/form(routes.Application.save())/*11.37*/ {_display_(Seq[Any](format.raw/*11.39*/("""
        
        <fieldset>
        
            """),_display_(Seq[Any](/*15.14*/inputText(computerForm("name"), '_label -> "Computer name"))),format.raw/*15.73*/("""
            """),_display_(Seq[Any](/*16.14*/inputText(computerForm("introduced"), '_label -> "Introduced date"))),format.raw/*16.81*/("""
            """),_display_(Seq[Any](/*17.14*/inputText(computerForm("discontinued"), '_label -> "Discontinued date"))),format.raw/*17.85*/("""

            """),_display_(Seq[Any](/*19.14*/select(
                computerForm("company"), 
                companies, 
                '_label -> "Company", '_default -> "-- Choose a company --",
                '_showConstraints -> false
            ))),format.raw/*24.14*/("""
            

        </fieldset>
        
        <div class="actions">
            <input type="submit" value="Create this computer" class="btn primary"> or 
            <a href=""""),_display_(Seq[Any](/*31.23*/routes/*31.29*/.Application.list())),format.raw/*31.48*/("""" class="btn">Cancel</a> 
        </div>
        
    """)))})),format.raw/*34.6*/("""
    
""")))})),format.raw/*36.2*/("""
"""))}
    }
    
    def render(computerForm:Form[Computer],companies:Seq[scala.Tuple2[String, String]]): play.api.templates.Html = apply(computerForm,companies)
    
    def f:((Form[Computer],Seq[scala.Tuple2[String, String]]) => play.api.templates.Html) = (computerForm,companies) => apply(computerForm,companies)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Jul 15 10:54:08 PDT 2013
                    SOURCE: /Users/lwang/play/samples/scala/computer-database/app/views/createForm.scala.html
                    HASH: 1ce09ab2432ab3793bf45463c86f4d11b97d03fb
                    MATRIX: 552->1|701->86|733->110|812->65|840->84|868->159|906->163|917->167|955->169|1034->213|1074->244|1114->246|1201->297|1282->356|1332->370|1421->437|1471->451|1564->522|1615->537|1848->748|2067->931|2082->937|2123->956|2209->1011|2247->1018
                    LINES: 19->1|22->5|22->5|23->1|25->4|26->5|28->7|28->7|28->7|32->11|32->11|32->11|36->15|36->15|37->16|37->16|38->17|38->17|40->19|45->24|52->31|52->31|52->31|55->34|57->36
                    -- GENERATED --
                */
            