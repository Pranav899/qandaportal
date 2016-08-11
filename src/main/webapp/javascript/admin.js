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
		url : "/qandaportal/rest/admin/list",
		success : function(data){
			$("#tablebody").html(data);
		}
	});
	$("#submit-question-id").click(function(){
		var questionId = $("#question-id").val();
		$.ajax({
			type : 'GET',
			url : "/qandaportal/rest/admin/"+questionId,
			success : function(data){
				$(".display-question").html(data);
			}
		});
	});
	$(".class").click(function(){
		window.reload(true);
	});
});