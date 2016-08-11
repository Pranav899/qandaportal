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
							$('#sidebar').html(data);
					  }
		});
		$.ajax({
			type : 'GET',
			url : "/qandaportal/rest/question/recentlyAddedQuestionForSideView",
			success : function(data){
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
		$("#duplicate-entry-form").click(function(){
			$("#duplicate-form").css({
				"display" : "block"
			});
			$("#submit-original-id").click(function(){
				$("#submit-parent-id-form").submit();
			});
		});
		$("#answer-search-submit").click(function(){
			var searchAnswer = $("#search-answer-input").val();
			var question = $("#main-question").text();
			var encoded = encodeURIComponent(question);
			$.ajax({
				type : 'GET',
				url : "/qandaportal/rest/answer/search/"+encoded+"/"+searchAnswer,
				success : function(data){
					$("#list-of-answers-display").html(data);
				}
			});
		});
		$(document.body).on("click",".positive-value",function(){
			$(this).parent().submit();
			alert("Thank for your response. Your response will be Updated.");
		});
		$(".negative-value").click(function(){
			$(this).parent().submit();
			alert("Thank for your response. Your response will be Updated.");
		});
});