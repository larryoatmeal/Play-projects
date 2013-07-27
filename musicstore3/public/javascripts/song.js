$(document).ready(function(){

	var user_id = $("#data_user_id").attr("data")
	var songs = new Array() //store al songs in actual array
	var currentSongIndex = 0


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
		$("#content").val(songs[currentSongIndex].content)

		


	}

	function renderSong(){
		//alert("Hi")
		var raw = $("#content").val()
		//alert(raw)
		//alert(raw)

		jsRoutes.controllers.ChordC.renderMusic(raw).ajax({
			success: function(data){
				//alert("Debug" + data)
				var formatted = data.replace(/\//g, '<span class="slashchord">/</span>')
				$("#render2").html(formatted)
			},
			error: function(err){
				alert("ERROR RENDER")
			}
		})

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


	}

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

