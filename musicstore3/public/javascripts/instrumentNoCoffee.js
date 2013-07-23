$(document).ready(function(){

	//CONSTANTS: Classes/ids
	//C: class
	//I: id
	//class of button stores state of button
	var LIKE_C =  "btn btn-info" // for not liked yet
	var LIKED_C = "btn btn-primary" //for already liked
	var LIKE_INFO_I = "like"
	var LIKE_BUTTON_I = "likeb"



	var currentInstrumentID = 0
	var userid = parseInt($("#data").attr("userid"), 10)
	//current user



	//Show instruments --------------------------------------
	var jsonInstrumentURL = $("#instrument-list").attr("json")
	//Populate instrument list from Json, create buttons with instrument ID
	function showInstrumentList(){


		$('#instrument-list').empty()
		$.get(jsonInstrumentURL,
		function(data, status){
			$.each(data, function(index, value){
				var name = value.name
				var id = value.instrument_id
				//var link = $('<a>').attr("href", "instruments/" + id).text(name)
				
				//Attach click handler
				var bullet = $('<li>').text(name).on("click", function(){
					displayEntries(id)
					currentInstrumentID = id
				})

				//var button = $('<button>').attr("id", id).text(name)

				//var bullet = '<li>' + value.name + '</li>'
				//$("#instrument-list").append(bullet)
				//$("#instrument-list").append(link)
				$("#instrument-list").append(bullet)

			})
		}
	)
	}

	showInstrumentList()



	//Adding an Instrument --------------------------
	$("#submitnewinstrument").on("click", addInstrument)

	function addInstrument(){
		var instrumentName = $("#newinstrument").val()
		jsRoutes.controllers.InstrumentPage.addInstrument(instrumentName).ajax({
			success: function(){
				showInstrumentList()
				$("#newinstrument").val("")
			},
			error: function(){

			}
		})
	}


	//Retrieve entries ------------------------
   	function displayEntries(id)
	{
		var jsonEntryURL = $("#entry-list").attr("json") + id
		//alert(jsonEntryURL)

		$(".form-inline").hide()//refresh artifacts
		//remove form, then show again after

		//clear table first
		$('tbody').empty()

		$.get(jsonEntryURL,
			function(data, status){
				$.each(data, 
					function(index, value){		

					//alert(value.comment)
    				$("#entry-list").find('tbody')
    				.append(
    					$('<tr>').append(
    						$('<td>').text(value.comment),
    						$('<td>').text(value.user_id),
    						$('<td>').text(value.firstName),
    						$('<td>').text(value.lastName),
    						$('<td>').text(value.date),
    						$('<td>').append(
    							$('<img>').attr({ 
    								"src": value.img,
    								"width": 100
								})
    						),
    						$('<td>').append(
    							$('<button>').text("Like").attr(
    							{
    								"class": LIKE_C,
    								"id": "likeb" + value.entry_id
    							}
    							).on("click", function(){
    							like(value.entry_id)})
    						),
    						$('<td>').attr({
    							"id": "like" + value.entry_id
    						})	
    					)
    				)

    				getlikes(value.entry_id)
					}
				)
			}
		)

		$(".form-inline").show(100)
	}
	
	

	
	//Add entry -----------------------------------------
	$("#submitButton").on("click", submitButton)

	function submitButton(){
		
		function successFn(data){
			//If succesful, refresh page
			displayEntries(currentInstrumentID) //refresh page
			$("#comment").val("") //clear inputs
		}
		function errorFn(data){
			//alert("error")
		}

		//Treat empty strings as null
		var commentString = $("#comment").val()
		if (commentString == ""){
			commentString = null
		}
		var imgString = $("#image").val()
		if (imgString ==""){
			imgString = null
		}

		var currentDate = new Date()
		var dateString = currentDate.getMonth() + "/" + currentDate.getDate() + "/" + currentDate.getFullYear() + " -" + currentDate.getHours() + ":" + currentDate.getMinutes()
		

		entry={user_id: userid, comment: commentString, date: dateString, img: imgString, entry_id: null, instrument_id: currentInstrumentID};

		entryJSON = JSON.stringify(entry)
		//alert(entryJSON)

		//jsRoutes.controllers.Application.dummy.ajax(ajaxSettings);

		//need parentheses after logout
		jsRoutes.controllers.InstrumentPage.addEntry().ajax({success: successFn, 
			error: errorFn,
		    data: entryJSON, //send over json
		    contentType: "application/json"}); //controller needs this to work properly
	}



	// Like mechanisms ----------------------------
	function getlikes(entryid){
		var numberOfLikes = 0
		var peopleWhoLiked = ""


		jsRoutes.controllers.InstrumentPage.listOfLikes(entryid).ajax({
		success: function(data){
			$.each(data, function(index, value){
				//alert(numberOfLikes)
				numberOfLikes++
				peopleWhoLiked += value + ","
				


				//disable button if user already liked
				if (value == userid){
					//$("#likeb" + entryid)
					$("#likeb" + entryid).text("Liked").attr({
						"class": LIKED_C 
					})
				}

			})

			$("#like" + entryid).text(numberOfLikes + " likes: " + peopleWhoLiked)
		}
		})
		//Asynchronous! Can't return out here
		//alert(numberOfLikes)
		//return numberOfLikes + " likes: " + peopleWhoLiked 
	}

	function like(entryid){
		//if current entry is not liked yet
		if ($("#likeb" + entryid).attr("class") == LIKE_C){
			jsRoutes.controllers.InstrumentPage.like(entryid, userid).ajax({
			success: function(data){
				getlikes(entryid)//refresh likes
			}
			})
		}else{
			jsRoutes.controllers.InstrumentPage.unlike(entryid, userid).ajax({
			success: function(data){
				$("#likeb" + entryid).text("Like").attr({
						"class": LIKE_C
					})//back to regular button
				getlikes(entryid)//refresh likes
			}
			})
		}
	}


	//Helpers------------------------------------------






});






/* Example using ajax
		product =
   ean: parseInt(ean)
   name: name
   description: description
jqxhr = $.ajax
   type: "PUT"
   url: productDetailsUrl(ean)
   contentType: "application/json"
   data: JSON.stringify product
jqxhr.done (response) ->
   $label = $('<span/>').addClass('label label-success')
   $row.children().last().append $label.text(response)
   $label.delay(3000).fadeOut()
Send data to the server
￼   jqxhr.fail (data) ->
      $label = $('<span/>').addClass('label label-important')
      message = data.responseText || data.statusText
      $row.children().last().append $label.text(message)
      */


		//jsRoutes.controllers..ajax(ajax1);


		/*
		//Setup
		var xmlhttp;
		if (window.XMLHttpRequest)
  		{// code for IE7+, Firefox, Chrome, Opera, Safari
  			xmlhttp=new XMLHttpRequest();
  		}	
		else
  		{// code for IE6, IE5
  			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  		}

  		var username = $("#data").attr("username")

	    xmlhttp.onreadystatechange=function()
	    {
	    if (xmlhttp.readyState==4 && xmlhttp.status==200)
	      {
	      	$("#response").text("HIIII")
	      }
	    else{
	    	alert("ERROR")
	   	  }
	    }
  		xmlhttp.open("GET", "/instruments/addEntry/", true);
  		xmlhttp.send();
  		*/






/*$("button").click(function(){
    //$("#words").toggle(500);
    if ($("#words").html() == "Hello"){
    	 $("#words").html("Bye")
    }else{
    	$("#words").html("Hello")
    }
   });

	$("#words").hover(
		function(){
			//alert($("#instrument-list").attr("json"))
		},
		function(){
			//alert("Bye!")
		}
	)*/


//alert("Submitted");

		/*ajaxSettings = {
			success: alert("succes"),
			error: alert("error")
		}*/
