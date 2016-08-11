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
		$(".positive-value").change(function(){
			$(this).parent().submit();
			alert("Thank for your response. Your response will be Updated.");
		});
		$(".negative-value").change(function(){
			$(this).parent().submit();
			alert("Thank for your response. Your response will be Updated.");
		});
});