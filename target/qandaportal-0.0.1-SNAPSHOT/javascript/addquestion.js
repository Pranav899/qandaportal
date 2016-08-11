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
			url : "/qandaportal/rest/question/mostViewed",
			success : function(data){
							console.log(data);
							$('#sidebar').html(data);
					  }
		});
		$.ajax({
			type : 'GET',
			url : "/qandaportal/rest/question/recentlyAddedQuestionForSideView",
			success : function(data){
							console.log(data);
							$('#sidebar').append(data);
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
		$("#submit-question").click(function(){
			event.preventDefault();
			if($("#question-entry").val() != null && $("#question-entry").val() != " "){
				$("#question-form").submit();
			}
		});
});