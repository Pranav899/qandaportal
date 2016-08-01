$(document).ready(function(){
		$.ajax({
			type : "GET",
			url : "/qandaportal/web/questions/question",
			cache : false,
			success : function(data){
				console.log(data);
				$("#main-question").text(data.question);
				$("#question-owner").text(data.userName);
				$("#number-of-answers").text(data.numberOfAnswers);
				$("#number-of-answers").text(data.numberOfAnswers);
				$("#views").text(data.views);
				$("#keyword1").text(data.topic1);
				$("#keyword2").text(data.topic2);
				$("#keyword3").text(data.topic3);
			}
		});
		$.ajax({
			type : "GET",
			url : "/qandaportal/web/answers",
			cache : false,
			success : function(data){
				console.log(data);
				for(i=0;i<10;i++){
					if(data[i] != null){
								$("display-answer"+i+"").css({
									'display' : 'block'
								});
								$("#user-name"+i+"").text(data[i].userName);
								$("#answer"+i+"").text(data[i].answer);
								$("#answer-rating"+i+"").text(data[i].answerId);
					}
					else{
						$("#display-answer"+i+"").css({
							'display' : 'none'
						});
					};
			};
			}
		});
		
});