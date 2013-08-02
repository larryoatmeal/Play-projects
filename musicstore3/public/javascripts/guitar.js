$(document).ready(function(){


	// $(document).mousemove(function(e){

 //      $('#globalcoordinates').text(e.pageX +', '+ e.pageY);
 //   	});

	var picOffset = $("#picture").offset()

	$("#picturetopleft").text(picOffset.top + ',' + picOffset.left)


	$("#picture").on("click", function(e){
		var parentOffset = $(this).parent().offset(); 
  	
  		var leftRef = e.pageX
		var topRef = e.pageY

   		var relX = leftRef - parentOffset.left;
   		var relY = topRef - parentOffset.top;

   		$("#globalcoordinates").text(leftRef + "," + topRef)



	})



});

