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
		$("#add-comment").click(function(){
			$("#add-comment-form").css({
				"display" : "block"
			});
			$("submit-comment").click(function(){
				if($("#comment").val() != null && $("#comment").val() != ""){
					$("#add-comment-form").submit();
				}
			});
		});
		$(document.body).on("click",".positive-value",function(){
			var element=$(this);
			var questionId = $(this).data("questionId");
			var answerId = $(this).data("answerId");
			$.ajax({
				type : 'POST',
				url : '/qandaportal/rest/rating/add/'+questionId+'/'+answerId+'/1',
				success : function(data){
					element.prev().text(data.positiveRating);
					element.parents("div").find("#negative-rating").text(data.negativeRating);
				}
			});
		});
		$(".negative-value").click(function(){
			var element=$(this);
			var questionId = $(this).data("questionId");
			var answerId = $(this).data("answerId");
			$.ajax({
				type : 'POST',
				url : '/qandaportal/rest/rating/add/'+questionId+'/'+answerId+'/0',
				success : function(data){
					element.next().text(data.negativeRating);
					element.parents("div").find("#positive-rating").text(data.positiveRating);
				}
			});
		});
});