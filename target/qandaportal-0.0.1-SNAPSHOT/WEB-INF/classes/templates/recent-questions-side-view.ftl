<div id = "most-viewed-questions">  			
<div class = "sidebar-element"><b>Recently Added Questions</b></div>
<#list questions as question>
	  <div class = "sidebar-element"><a href="/qandaportal/rest/answer/${question.questionId}">${question.question}</a></div>
</#list>
</div>