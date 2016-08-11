$(document).ready(function(){
	$(document.body).on("mouseover","a",function(){
		$(this).css({
			"text-decoration" : "underline"
		});
	});
	$(document.body).on("mouseout","a",function(){
		$(this).css({
			"text-decoration" : "none"
		});
	});
	$.ajax({
		type : 'GET',
		url : "/qandaportal/rest/question/recentlyAdded",
		success : function(data){
						$('#main-content').html(data);
				  }
	});
	$.ajax({
		type : 'GET',
		url : "/qandaportal/rest/question/mostViewed",
		success : function(data){
						$('#sidebar').html(data);
				  }
	});
	$("#search_box1").click(function(){
		var searchInput = $("#search_box").val();
		$.ajax({
			type : 'GET',
			url : "/qandaportal/rest/question/search/topic/"+searchInput,
			success : function(data){
				$("#main-content").html(data);
			}
		});
	});
	$(document.body).on("click",".pagination",function(){
		alert($(this).attr('id'));
		var uniqueId = $(this).data("uniqueId");
		var start = $(this).data("start");
		var pageNo = $(this).data("pageNo");
		var noOfPages = $(this).data("noOfPages");
		console.log(uniqueId);
		console.log(start);
		console.log(pageNo);
		console.log(noOfPages);
		$.ajax({
			type : 'GET',
			url : "/qandaportal/rest/question/recentlyAdded/"+uniqueId+"/"+start+"/"+noOfPages,
			success : function(data){
				$('#main-content').html(data);
			}
		});
	});
	$(document.body).on("click","#previous-button",function(){
		var uniqueId = $(".active").data("uniqueId");
		var start = ($(".active").data("start"))-5;
		var pageNo = $(".active").data("pageNo");
		var noOfPages = $(".active").data("noOfPages");
		console.log(uniqueId);
		console.log(start);
		console.log(pageNo);
		console.log(noOfPages);
		$.ajax({
			type : 'GET',
			url : "/qandaportal/rest/question/recentlyAdded/"+uniqueId+"/"+start+"/"+noOfPages,
			success : function(data){
				$('#main-content').html(data);
			}
		});
	});
	$(document.body).on("click","#next-button",function(){
		var uniqueId = $(".active").data("uniqueId");
		var start = ($(".active").data("start"))+5;
		var pageNo = $(".active").data("pageNo");
		var noOfPages = $(".active").data("noOfPages");
		console.log(uniqueId);
		console.log(start);
		console.log(pageNo);
		console.log(noOfPages);
		$.ajax({
			type : 'GET',
			url : "/qandaportal/rest/question/recentlyAdded/"+uniqueId+"/"+start+"/"+noOfPages,
			success : function(data){
				$('#main-content').html(data);
			}
		});
	});
});