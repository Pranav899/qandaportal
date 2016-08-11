<#list answers as answer>
							<div class = "display-answer" id = "display-answer">
	   						<div class = "answered-user-data">
	   								<img class = "user-image" src = "/qandaportal/images/images.png">
	   								<p class = "user-name" id = "user-name">
									${answer.userId}
									</p>
	   						</div>
	   						<div class = "answer-content">
	   							<p class = "answer" id = "answer">
	   							${answer.answer}
	   							</p>
	   							<div class = "answer-data">
	   									<div class = "answer-comments" id = "answer-comments"><a href = "/qandaportal/rest/comments/${answer.questionId}/${answer.answerId}">Comments</a></div>
	   									<div class = "give-answer-rating">
	   										<form id="positive" class = "submit-positive-rating" method = "POST" action = "/qandaportal/rest/rating/add/${answer.questionId}/${answer.answerId}/1">
	   											<span id = "positive-rating">${answer.positiveRating}</span><img src = "/qandaportal/images/like.jpg" name = "rating" value = "Like" class = "positive-value">
	   										</form>
	   										<form id="negative" class = "submit-negative-rating" method = "POST" action = "/qandaportal/rest/rating/add/${answer.questionId}/${answer.answerId}/0">
	   										    <img src = "/qandaportal/images/dislike.png" name = "rating" value = "Dislike" class= "negative-value"><span id = "negative-rating">${answer.negativeRating}</span>
											</form>
	   									</div>
	   									<div class = "clear"></div>
	   							</div>
	   						</div>
	   						<div class = "clear"></div>
						</div>
</#list>