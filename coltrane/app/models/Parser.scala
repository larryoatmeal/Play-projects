// package models

// import scala.util.matching.Regex
// import play.api.Logger

// case class Chord(root: String, suffix: String, bass: String, beat: Int)
// case class Measure(chords: List[Chord], number: Int)
// case class Element(text: String, element_type: Int)

// object Parser{

//   //Element types
//   val Musicbar = 1
//   val Text = 2
//   val Keysig = 3

//   def parse(raw: String) = {
//     val lines = raw.split('\n') //split into lines of text



//     val linesWithElements = lines.map(getElements(_, List()))

//     //elements only
//     val elements = linesWithElements.flatten 
//     //create list of how many elements per line
//     val elementsPerLine = linesWithElements.map(_.length)

//     elements
//     elementsPerLine



//   }

//   //These patterns only match what's inside 
//   val MusicbarPattern = new Regex("""(?<=\|)[^\n]*?(?=\|)""")
//   //val KeysigPattern = new Regex("""(?<=<)[^\n]*?(?=>)""")
//   val TextPattern = new Regex("""(?<=\()[^\n]*?(?=\))""")



//   //Recursively parses elements and adds them to a list
//   def getElements(input: String, listOfElements: List[Element]): List[Element] = {
//     //First determine which element is next and what index it is at
//     val (index, element_type) = {
//       val parensIndex = (input.indexOf('('), Text)
//       val pipeIndex = (input.indexOf('|'), Musicbar)
//       val angleIndex = (input.indexOf('<'), Keysig)

//       //Get index and element_type with lowest index
//       List(parensIndex, pipeIndex, angleIndex).foldLeft((input.length-1, -1)){
//         (minimum, current) => {
//           if (current._1 == -1){//If index not found
//             minimum
//           }else if (current._1 < minimum._1) {
//             current
//           }
//           else {
//             minimum
//           }
//         } 
//       }
//     }

//     //Logger.debug(s"Step 1: Index = $index Element = $element_type")

//     //Remove anything before index where element begins (makes things easier)
//     val input2 = input.drop(index)

//     //Logger.debug(s"Input 2: $input2")

//     //Parse appropriate bar
//     element_type match {
//       case Musicbar => {
//         //Logger.debug("Musicbar:")
//         MusicbarPattern findFirstIn input2 match {
//           case Some(found) => {
//             //store element, remove from input
//             //tail.indexOf gives you second occurence as | must be at the beginning of the string
//             val newInput = input2.drop(input2.tail.indexOf('|')+1) //Must compensate for +1 because tail reduces original string length by 1
//             val newElement = Element(found, Musicbar)
//             getElements(newInput, newElement :: listOfElements)
//           } 
//           case None => listOfElements.reverse
//         }
//       }
//       case Text => {
//         //Logger.debug("Musicbar:")
//         TextPattern findFirstIn input2 match {
//           case Some(found) => {
//             val newInput = input2.drop(input2.indexOf(')')+1) //Don't keep )
//             val newElement = Element(found, Text)
//             getElements(newInput, newElement :: listOfElements)
//           } 
//           case None => {
//             listOfElements.reverse
//           }
//         }
//       }
//       // case Keysig => {
//       //   //Logger.debug("Musicbar:")
//       //   KeysigPattern findFirstIn input2 match {
//       //     case Some(found) => {
//       //       //store element, remove from input
//       //       //tail.indexOf gives you second occurence as | must be at the beginning of the string
//       //       val newInput = input2.drop(input2.indexOf('>')+1) //don't keep > 
//       //       val newElement = Element(found, Keysig) //don't store | in element
//       //       getElements(newInput, newElement :: listOfElements)
//       //     } 
//       //     case None => listOfElements.reverse
//       //   }
//       // }
//       case _ => {
//         listOfElements.reverse
//       }
//     }  
//   }

//   //Recursively parse chords








//   val sample = """
//   (hello) | a | b | c | 
//   <e>| d | f | d |
//   | d | s | f |
//   | a | b|
//   | d |
//   | d |
//   """

//   //Time signature bar
//   //Key sig bar

//   //| (Comment) |
//   //| <4/4> |
//   //| [1] |






//   val barPattern = new Regex("""(?<=\|)[^\n]*?(?=\|)""")

//   def getBars(input: String) = {

//     /*
//     Ex: | a | b | c | d
//     a, b, c will be taken, but not d
//     */
//     barPattern.findAllIn(input)
//   }







// }