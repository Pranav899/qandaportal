<div id = "list-of-questions-display">
	   					
<div class = "main-content-element" id = "content-header"><b>Recently Asked Questions</b></div>
<#list questions as question>
<div class = "main-content-element questions" >
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
									Related To :
	   								<span  class = "topics" id = keyword1><a href = "/qandaportal/rest/question/topic/${question.topic1}">${question.topic1}</a></span> ,
	   								<span  class = "topics" id = keyword2><a href = "/qandaportal/rest/question/topic/${question.topic2}">${question.topic2}</a></span> ,
	   								<span  class = "topics" id = keyword3><a href = "/qandaportal/rest/question/topic/${question.topic3}">${question.topic3}</a></span>
	   								<span class = "question-desc" id = "question-id-display">Question Id : ${question.questionId}</span>
	   						</div>
	   					</div>
</div>
</#list>
<div class = "main-content-element pagination-division">
	<ul class = "pagination-list">
		<#if pages[0].activePage != 1>
		<li  id = "previous-button"><</li>
		</#if>
		<#list pages as page>
			<li class = "pagination <#if page.pageNo == page.activePage> active </#if>" data-start = ${page.start} data-unique-Id = ${page.uniqueId} data-no-Of-Pages = ${page.noOfPages} data-page-No = ${page.pageNo}>${page.pageNo}</li>
		</#list>
		<#if pages[0].activePage != pages[0].noOfPages>
		<li  id = "next-button">></li>
		</#if>
	</ul>
</div>
</div>