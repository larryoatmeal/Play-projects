$(document).ready(function(){

	var user_id = $("#data_user_id").attr("data")
	var songs = new Array() //store al songs in actual array
	var currentSongIndex = 0
	var originalKey = "C"
	var destinationKey = "C"
	var currentTimeSig = 4


	// //Tester
	// function tester(){
	// 	jsRoutes.controllers.ChordC.dummy().ajax({
	// 		success: function(data){

	// 		},
	// 		error: function(err){

	// 		}
	// 	})
	// }
	// tester()







	//Regets all song objects, repopulates drop down, opens up first song
	function refresh(){
		jsRoutes.controllers.ChordC.getSongs(user_id).ajax({
		success: function(data){

			songs.length = 0 //clear array first

			$.each(data, function(index, value){
				//alert(data)
				//alert(index)
			 	songs[index] = value
			}
			)


			populateSongDropDown()

			//Whenever refresh is called, open first song
			//if available
			if (songs.length != 0){
				currentSongIndex = 0
				loadSong()
				renderSong()
			}
		},
		error: function(err){
			alert("error")
		}
	})	
	}

	refresh()
	
	//Populate open dropdown
	function populateSongDropDown(){
		$("#songpick").empty()//clear first

		for (var i = 0; i < songs.length; i++){
			//alert(songs[i])
			$("#songpick").append(
				$('<option>').text(songs[i].title).attr({
					"songindex" : i
					//We're going to use index in array instead of
					//actual song_id
					//"songindex" : songs[i].song_id 
				})
			)
		}



	}

	//New song
	$("#new").on("click", function(){
		jsRoutes.controllers.ChordC.newSong(user_id).ajax({
			success: function(data){
				refresh()//reget songs, fill dropdown
				//change song

				//Set current song to new song, which
				//should be last song in the list
				//currentSongIndex = songs.length - 1
				//loadSong()
			}		
		})	
	})

	//Delete song
	$("#delete").on("click", function(){
		var song_id_actual = songs[currentSongIndex].song_id

		jsRoutes.controllers.ChordC.deleteSong(song_id_actual).ajax({
			success: function(data){
				refresh()//reget songs, fill dropdown
				//change song

				//Set current song to new song, which
				//should be last song in the list
				//currentSongIndex = songs.length - 1
				//loadSong()
			}		
		})	
	})

	//Save song
	$("#save").on("click", function(){
		saveSong()
	})
	$(document).keydown(function(e) {
        if (e.keyCode == 83 && e.ctrlKey) {
            saveSong()
            
        }
    });


	function saveSong(){
		//alert("save")
		var content = $("#content").val()
		var user_id_current = parseInt(user_id, 10)
		var composer = $("#composer").val()
		var date = $("#date").val()
		var song_id = songs[currentSongIndex].song_id
		var title = $("#title").val()


		song={content: content, user_id: user_id_current, composer: composer, date: date, song_id: song_id, title: title};
		songJSON = JSON.stringify(song)
		//alert(songJSON)

		jsRoutes.controllers.ChordC.saveSong(songJSON).ajax(
		{
			success: function(){
				//Update javascript song object to reflect database change
				jsRoutes.controllers.ChordC.getSong(song_id).ajax({
					success: function(data){
						//update edited song
						songs[currentSongIndex] = data
						//update dropdown title
						
						$("#songpick option:selected").text(songs[currentSongIndex].title)

						renderSong()

					},
					error: function(){
					}
				}
				)



			},
			error: function(){
				alert("error")

			},
			data: songJSON,
			contentType: "application/json"


		}
		)

	}




	//Change song upon dropdown changed
	$("#songpick").change(function(){
		currentSongIndex = $("#songpick option:selected").attr("songindex")
		loadSong()
		renderSong()
		//refresh()
		//loadSong is inside refresh
		//We can't just call loadSong because want to update
	}



	)

	function loadSong(){
		$("#title").val(songs[currentSongIndex].title)
		$("#composer").val(songs[currentSongIndex].composer)
		$("#date").val(songs[currentSongIndex].date)
		

		// MySQL won't store triangle unicode. Will store it as
		//question mark
		//So take question mark and convert it to triangle
		//when loading song
		//User should not use question mark!

		var triangledContent = songs[currentSongIndex].content.replace(/\?/g, '\u25B2')
		$("#content").val(triangledContent)


	}

	function renderSong(){
		var raw = $("#content").val()
	
		renderObject = {content: raw, origKey: originalKey, destKey: destinationKey, timeSig: currentTimeSig}
		renderJSON = JSON.stringify(renderObject)

		// jsRoutes.controllers.ChordC.renderMusic(raw).ajax({
		// 	success: function(data){
		// 		//alert("Debug" + data)
		// 		var formatted = data.replace(/\//g, '<span class="slashchord">/</span>')
		// 		$("#render2").html(formatted)
		// 	},
		// 	error: function(err){
		// 		alert("ERROR RENDER")
		// 	}
		// })

	    //Get doesn't work! Must use PUT or POST?
		jsRoutes.controllers.ChordC.renderWithOptions(renderJSON).ajax({
			success: function(data){
				//Make slash chords grey,nonbold with css span
				var formatted = data.replace(/\//g, '<span class="slashchord">/</span>')


				$("#render2").html(formatted)
			},
			error: function(err){
				alert("ERROR RENDER")
			},
			data: renderJSON,
			contentType: "application/json"
		})


	}

	//Tranposition box

	var sharps = new Array();
	sharps[0] = "C"
	sharps[1] = "C#"
	sharps[2] = "D"
	sharps[3] = "D#"
	sharps[4] = "E"
	sharps[5] = "F"
	sharps[6] = "F#"
	sharps[7] = "G"
	sharps[8] = "G#"
	sharps[9] = "A"
	sharps[10] = "A#"
	sharps[11] = "B"

	var flats = new Array();
	flats[0] = "C"
	flats[1] = "Db"
	flats[2] = "D"
	flats[3] = "Eb"
	flats[4] = "E"
	flats[5] = "F"
	flats[6] = "Gb"
	flats[7] = "G"
	flats[8] = "Ab"
	flats[9] = "A"
	flats[10] = "Bb"
	flats[11] = "Cb"


	function fillSharpsOrig(){
		//alert("Fill sharps")
		//alert($("#origKey").val())


		$("#origKey").empty()

		sharps.forEach(function(element, index){
		$("#origKey").append(
			$('<option>').text(element)
		)
		})

	}
	function fillSharpsNew(){
		$("#newKey").empty()
		sharps.forEach(function(element, index){
		$("#newKey").append(
			$('<option>').text(element)
		)
		})

		$("#newKey").append(
			$('<option>').text("Roman")
		)	
	}
	function fillFlatsOrig(){
		$("#origKey").empty()

		flats.forEach(function(element, index){
		$("#origKey").append(
			$('<option>').text(element)
		)
		})	
	}
	function fillFlatsNew(){
		$("#newKey").empty()

		flats.forEach(function(element, index){
		$("#newKey").append(
			$('<option>').text(element)
		)
		})

		$("#newKey").append(
			$('<option>').text("Roman")
		)	
	}
	
	fillSharpsOrig()
	fillSharpsNew()




	// $("#origKey").append(
	// 	$('<option>').text("C")
	// )

	$(".oldRadio").change(function(){
		var origRadio = $('input:radio[name=origRadio]:checked').val()
		//lowercased for somereason

		if (origRadio == "sharp"){
			//alert("sharp")
			fillSharpsOrig()
		}else{
			//alert("flat")
			fillFlatsOrig()
		}

	})
	$(".newRadio").change(function(){
		var newRadio = $('input:radio[name=newRadio]:checked').val()
		//lowercased for somereason

		if (newRadio == "sharp"){
			fillSharpsNew()
		}else{
			fillFlatsNew()
		}
	})

	$("#transpose").on("click", function(){
		originalKey = $("#origKey").val()
		destinationKey = $("#newKey").val()
		renderSong()
	})

	$("#download").on("click", function(){
		//Write to file
		var currentDate = new Date()
		var month = currentDate.getMonth()
		var day = currentDate.getDay()
		var minute = currentDate.getMinutes()


		var filename = songs[currentSongIndex].title + "." + songs[currentSongIndex].composer + "." + month + "." + day + "." + minute+ ".txt"
		
		var raw = $("#content").val()
	
		//Get text without special html formatting
		renderObject = {content: raw, origKey: originalKey, destKey: destinationKey, timeSig: currentTimeSig}
		renderJSON = JSON.stringify(renderObject)

		jsRoutes.controllers.ChordC.renderWithOptions(renderJSON).ajax({
			success: function(data){
				//Upload, then download
				jsRoutes.controllers.ChordC.writefile(data, filename).ajax({
					success: function(){

						var url = jsRoutes.controllers.ChordC.download(filename).url;
						//alert(url)
						location.href = url


					},
					error: function(){
						alert("Error downloading")
					}


				})
			},
			error: function(err){
				alert("ERROR RENDER")
			},
			data: renderJSON,
			contentType: "application/json"
		})

	})

	$("#musicxml").on("click", function(){
		var data = $("#content").val()
		var url = jsRoutes.controllers.ChordC.musicXML(data).url;
		location.href = url

	})

		//Diminished/half dimished
		//Change all 0's to dim
		//Change all o's to halfdim

	$("#dim").on("click", function(){
			//alert("Dim")
			var score = $("#content").val()
			var dimScore = score.replace(/0/g,'\u00B0')
			$("#content").val(dimScore)
	})

	$("#halfdim").on("click", function(){
			//alert("halfdim")
			var score = $("#content").val()
			var halfdimScore = score.replace(/o/g,'\u00D8')
			$("#content").val(halfdimScore)

	})

	$("#triangle").on("click", function(){
			var score = $("#content").val()
			var halfdimScore = score.replace(/\^/g,'\u25B2')
			$("#content").val(halfdimScore)

	})

	$("#timeSig").change(function(){
		currentTimeSig = parseInt($("#timeSig").val(), 10)
		renderSong()

	})



		//refresh()
		//loadSong is inside refresh
		//We can't just call loadSong because want to update

	//For some reason this doesn't work ???? Exact same thing

		// jsRoutes.controllers.ChordC.render(raw).ajax({
		// 	success: function(data){
		// 		//$("#render").val(data)
		// 		//alert("success")
		// 		var formatted = data.replace(/\//g, '<span class="slashchord">/</span>')
		// 		$("#render2").html(formatted)
				
  //   			//var formatted = $("#render").val().replace(/\//g, "*")


		// 		//$("#render").val(formatted)
		// 	},
		// 	error: function(err){
		// 		alert("ERROR RENDER")
		// 	}

		// })




	// jsRoutes.controllers.ChordC.dummy().ajax({
	// 	success: function(data){
	// 		alert(data)
	// 	},
	// 	error: function(err){

	// 	},
	// 	data: "Hello",
	// 	contentType: "application/json"


	// })



	//New: Reget JSON, reget drop down, return to first selection
	//Save: Reget JSON, don't do anything with drop down
	//Delete: Reget JSON, reget drop down, return to first selection




	//$("[href='default.htm']")
	// $('.target').change(function() {
 //  		alert('Handler for .change() called.');
	// });



	// //Populate song selector
	// jsRoutes.controllers.ChordM.addEntry().ajax({success: successFn, 
	// 		error: errorFn,
	// 	    data: entryJSON, //send over json
	// 	    contentType: "application/json"});








	// //CONSTANTS: Classes/ids
	// //C: class
	// //I: id
	// //class of button stores state of button
	// var LIKE_C =  "btn btn-info" // for not liked yet
	// var LIKED_C = "btn btn-primary" //for already liked
	// var LIKE_INFO_I = "like"
	// var LIKE_BUTTON_I = "likeb"



	// var currentInstrumentID = 0
	// var userid = parseInt($("#data").attr("userid"), 10)
	// //current user



	// //Show instruments --------------------------------------
	// var jsonInstrumentURL = $("#instrument-list").attr("json")
	// //Populate instrument list from Json, create buttons with instrument ID
	// function showInstrumentList(){


	// 	$('#instrument-list').empty()
	// 	$.get(jsonInstrumentURL,
	// 	function(data, status){
	// 		$.each(data, function(index, value){
	// 			var name = value.name
	// 			var id = value.instrument_id
	// 			//var link = $('<a>').attr("href", "instruments/" + id).text(name)
				
	// 			//Attach click handler
	// 			var bullet = $('<li>').text(name).on("click", function(){
	// 				displayEntries(id)
	// 				currentInstrumentID = id
	// 			})

	// 			//var button = $('<button>').attr("id", id).text(name)

	// 			//var bullet = '<li>' + value.name + '</li>'
	// 			//$("#instrument-list").append(bullet)
	// 			//$("#instrument-list").append(link)
	// 			$("#instrument-list").append(bullet)

	// 		})
	// 	}
	// )
	// }

	// showInstrumentList()



	// //Adding an Instrument --------------------------
	// $("#submitnewinstrument").on("click", addInstrument)

	// function addInstrument(){
	// 	var instrumentName = $("#newinstrument").val()
	// 	jsRoutes.controllers.InstrumentPage.addInstrument(instrumentName).ajax({
	// 		success: function(){
	// 			showInstrumentList()
	// 			$("#newinstrument").val("")
	// 		},
	// 		error: function(){

	// 		}
	// 	})
	// }


	// //Retrieve entries ------------------------
 //   	function displayEntries(id)
	// {
	// 	var jsonEntryURL = $("#entry-list").attr("json") + id
	// 	//alert(jsonEntryURL)

	// 	$(".form-inline").hide()//refresh artifacts
	// 	//remove form, then show again after

	// 	//clear table first
	// 	$('tbody').empty()

	// 	$.get(jsonEntryURL,
	// 		function(data, status){
	// 			$.each(data, 
	// 				function(index, value){		

	// 				//alert(value.comment)
 //    				$("#entry-list").find('tbody')
 //    				.append(
 //    					$('<tr>').append(
 //    						$('<td>').text(value.comment),
 //    						$('<td>').text(value.user_id),
 //    						$('<td>').text(value.firstName),
 //    						$('<td>').text(value.lastName),
 //    						$('<td>').text(value.date),
 //    						$('<td>').append(
 //    							$('<img>').attr({ 
 //    								"src": value.img,
 //    								"width": 100
	// 							})
 //    						),
 //    						$('<td>').append(
 //    							$('<button>').text("Like").attr(
 //    							{
 //    								"class": LIKE_C,
 //    								"id": "likeb" + value.entry_id
 //    							}
 //    							).on("click", function(){
 //    							like(value.entry_id)})
 //    						),
 //    						$('<td>').attr({
 //    							"id": "like" + value.entry_id
 //    						})	
 //    					)
 //    				)

 //    				getlikes(value.entry_id)
	// 				}
	// 			)
	// 		}
	// 	)

	// 	$(".form-inline").show(100)
	// }
	
	

	
	// //Add entry -----------------------------------------
	// $("#submitButton").on("click", submitButton)

	// function submitButton(){
		
	// 	function successFn(data){
	// 		//If succesful, refresh page
	// 		displayEntries(currentInstrumentID) //refresh page
	// 		$("#comment").val("") //clear inputs
	// 	}
	// 	function errorFn(data){
	// 		//alert("error")
	// 	}

	// 	//Treat empty strings as null
	// 	var commentString = $("#comment").val()
	// 	if (commentString == ""){
	// 		commentString = null
	// 	}
	// 	var imgString = $("#image").val()
	// 	if (imgString ==""){
	// 		imgString = null
	// 	}

	// 	var currentDate = new Date()
	// 	var dateString = currentDate.getMonth() + "/" + currentDate.getDate() + "/" + currentDate.getFullYear() + " -" + currentDate.getHours() + ":" + currentDate.getMinutes()
		

	// 	entry={user_id: userid, comment: commentString, date: dateString, img: imgString, entry_id: null, instrument_id: currentInstrumentID};

	// 	entryJSON = JSON.stringify(entry)
	// 	//alert(entryJSON)

	// 	//jsRoutes.controllers.Application.dummy.ajax(ajaxSettings);

	// 	//need parentheses after logout
	// 	jsRoutes.controllers.InstrumentPage.addEntry().ajax({success: successFn, 
	// 		error: errorFn,
	// 	    data: entryJSON, //send over json
	// 	    contentType: "application/json"}); //controller needs this to work properly
	// }



	// // Like mechanisms ----------------------------
	// function getlikes(entryid){
	// 	var numberOfLikes = 0
	// 	var peopleWhoLiked = ""


	// 	jsRoutes.controllers.InstrumentPage.listOfLikes(entryid).ajax({
	// 	success: function(data){
	// 		$.each(data, function(index, value){
	// 			//alert(numberOfLikes)
	// 			numberOfLikes++
	// 			peopleWhoLiked += value + ","
				


	// 			//disable button if user already liked
	// 			if (value == userid){
	// 				//$("#likeb" + entryid)
	// 				$("#likeb" + entryid).text("Liked").attr({
	// 					"class": LIKED_C 
	// 				})
	// 			}

	// 		})

	// 		$("#like" + entryid).text(numberOfLikes + " likes: " + peopleWhoLiked)
	// 	}
	// 	})
	// 	//Asynchronous! Can't return out here
	// 	//alert(numberOfLikes)
	// 	//return numberOfLikes + " likes: " + peopleWhoLiked 
	// }

	// function like(entryid){
	// 	//if current entry is not liked yet
	// 	if ($("#likeb" + entryid).attr("class") == LIKE_C){
	// 		jsRoutes.controllers.InstrumentPage.like(entryid, userid).ajax({
	// 		success: function(data){
	// 			getlikes(entryid)//refresh likes
	// 		}
	// 		})
	// 	}else{
	// 		jsRoutes.controllers.InstrumentPage.unlike(entryid, userid).ajax({
	// 		success: function(data){
	// 			$("#likeb" + entryid).text("Like").attr({
	// 					"class": LIKE_C
	// 				})//back to regular button
	// 			getlikes(entryid)//refresh likes
	// 		}
	// 		})
	// 	}
	// }






});

