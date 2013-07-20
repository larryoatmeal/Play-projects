$(document).ready(function(){

   // jQuery methods go here...
   	function displayEntries(id)
	{
		var jsonEntryURL = $("#entry-list").attr("json") + id
		//alert(jsonEntryURL)

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
    						)
    					)
    				)



					}
				)
			}
		)
	}









   $("button").click(function(){
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
	)




	var jsonInstrumentURL = $("#instrument-list").attr("json")
	//Populate instrument list from Json, create buttons with instrument ID
	$.get(jsonInstrumentURL,
		function(data, status){
			$.each(data, function(index, value){
				var name = value.name
				var id = value.instrument_id
				//var link = $('<a>').attr("href", "instruments/" + id).text(name)
				
				//Attach click handler
				var bullet = $('<li>').text(name).on("click", function(){
					displayEntries(id)
				})
				//var button = $('<button>').attr("id", id).text(name)

				//var bullet = '<li>' + value.name + '</li>'
				//$("#instrument-list").append(bullet)
				//$("#instrument-list").append(link)
				$("#instrument-list").append(bullet)

			})
		}
	)





});