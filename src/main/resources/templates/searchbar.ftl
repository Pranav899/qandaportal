<div id = "list-of-questions-display">
	   					
<div class = "main-content-element" id = "content-header"><b>Search Results</b></div>
<#list questions as question>
<div class = "main-content-element" id = "questions">
	   					<div class = "display-question">
	   						<div class = "question-inside" id = "question_data">
	   							<h3 id = "main-question"><a href="/qandaportal/rest/answer/${question.questionId}">Q) ${question.question}</a></h3>
	   						</div>
	   						<div class = "question-inside" id = "question-description">
	   								AskedBy : 
	   								<span class = "question-desc" id = "question-owner">
	   								${question.userId}
	   								</span> | 		Answers
	   								<span class = "question-desc" id = "number-of-answers">
	   								${question.numberOfAnswers}
	   								</span> | Views 
	   								<span class = "question-desc" id = "views">
	   								${question.views}
	   								</span>
	   						</div>
	   						<div class = "question-inside" id = "question_keywords">
	   								<span  class = "topics" id = keyword1><a href = "/qandaportal/rest/question/topic/${question.topic1}">${question.topic1}</a></span> ,
	   								<span  class = "topics" id = keyword2><a href = "/qandaportal/rest/question/topic/${question.topic2}">${question.topic2}</a></span> ,
	   								<span  class = "topics" id = keyword3><a href = "/qandaportal/rest/question/topic/${question.topic3}">${question.topic3}</a></span>
	   								<span class = "question-desc" id = "question-id-display">Question Id : ${question.questionId}</span>
	   						</div>
	   					</div>
</div>
</#list>
</div>