$(document).ready(function(){
	$.ajax({
		type : 'GET',
		url : "qandaportal/web/questions",
		cache: false,
		success : function(data){
						console.log(data);
						for(i=0;i<10;i++){
								if(data[i] != null){
											$("#questions"+i+"").css({
												'display' : 'block'
											});
											$("#main-question"+i+"").text(data[i].question);
											$("#question-owner"+i+"").text(data[i].userName);
											$("#number-of-answers"+i+"").text(data[i].numberOfAnswers);
											$("#keyword1"+i+"").text(data[i].topic1);
											$("#keyword2"+i+"").text(data[i].topic2);
											$("#keyword3"+i+"").text(data[i].topic3);
											$("#views"+i+"").text(data[i].views);
								}
								else{
									$("#questions"+i+"").css({
										'display' : 'none'
									});
								};
						};
				  }
	});
});