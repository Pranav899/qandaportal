$(document).ready(function(){
	$(document.body).on("click",".confirm",function(){
		var questionId = $(this).data("questionId");
		var parentId = $(this).data("parentId");
		console.log(questionId);
		console.log(parentId);
		$.ajax({
			type  : 'POST',
			url : '/qandaportal/rest/admin/confirm/'+questionId+'/'+parentId,
			success : function(data){
				console.log(data);
				$("#tablebody").html(data);
			}
		});
	});
	$(document.body).on("click",".deny",function(){
		var questionId = $(this).data("questionId");
		var parentId = $(this).data("parentId");
		console.log(questionId);
		console.log(parentId);
		$.ajax({
			type  : 'POST',
			url : '/qandaportal/rest/admin/deny/'+questionId+'/'+parentId,
			success : function(data){
				console.log(data);
				$("#tablebody").html(data);
			}
		});
	});
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