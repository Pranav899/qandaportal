<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question And Answer Portal</title>
<link rel="stylesheet" type="text/css" href="/qandaportal/css/answers.css">
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script src="/qandaportal/javascript/answers.js"></script>
</head>
<body id = "main">
		<div class = "fixed-division">
		<div id = "header">
			<h1 class = "header_elements" id= "logo">AnswersHub</h1>
			<p class = "header_elements" id= "main_searchbar"><input type = "text" id = "search_box"></p>
			<p class = "header_elements" id= "search_submit"><input type = "submit" id= "search_box1" value = "Search"></p>
			<p class = "header_elements" id= "ask_box"><a href = "/qandaportal/rest/question/addQuestionPage"><input type = "submit" id = "ask_button" value = "Ask Question?"></a></p>
		</div>
		<div id = "navigation">
			<ul>
				<li class = "nav_items"><a href = "/qandaportal/rest/login/home">Home</a></li>
				<li class = "nav_items"><a href = "/qandaportal/rest/question/addQuestionPage">Ask Question ?</a></li>
				<li class = "nav_items"><a href = "/qandaportal/rest/question/unAnswered">Answer Questions</a></li>
				<li class = "nav_items"><a href = "/qandaportal/rest/login/home">Questions</a></li>
				<#if session == "absent">
				<li class = "nav_items" id = "dropdown"><a class = "dorpbtn">Login/ SignUp</a>
						 <div class="dropdown-content">
      							<a href="/qandaportal/login.html">User Login</a>
      							<a href="/qandaportal/register.html">SignUp</a>
   						 </div>
				</li>
				
				<#else>
						<li class = "nav_items" id = "dropdown"><a class = "dorpbtn" href = "/qandaportal/rest/user/logout">Logout</a></li>
				</#if>
			</ul>
	   </div>
	   <div class = "clear"></div>
	   </div>
	   <div id = "container">
	   		<div id = "main-content">
	   			<div class = "main-content-element" id = "questions">
	   					
	   					<div class = "display-question">
	   						<div class = "question-inside" id = "question_data">
	   							<h3 id = "main-question"><a href="/qandaportal/rest/answer/${question.questionId}">Q) ${question.question}</a></h3>
	   						</div>
	   						<div class = "question-inside" id = "question-description">
	   								Posted by : 
	   								<span class = "question-desc" id = "question-owner">
	   								${question.userId}
	   								</span> |  Answers
	   								<span class = "question-desc" id = "number-of-answers">
	   								${question.numberOfAnswers}
	   								</span>  | Views 
	   								<span class = "question-desc" id = "views">
	   								${question.views}
	   								</span>
	   						</div>
	   						<div class = "question-inside" id = "question_keywords">
									Topics :
	   								<span  id = keyword1><a href = "/qandaportal/rest/question/topic/${question.topic1}">${question.topic1}</a></span>,
	   								<span  id = keyword2><a href = "/qandaportal/rest/question/topic/${question.topic2}">${question.topic2}</a></span>,
	   								<span  id = keyword3><a href = "/qandaportal/rest/question/topic/${question.topic3}">${question.topic3}</a></span>
	   								<span  class = "question-desc" id = "parent-question"><a href = "/qandaportal/rest/answer/${question.parentQuestionId}"><b>Original Question</b></a></span>
	   						</div>
	   				  </div>

					
					  <div class = "display-answer" id = "display-answer">
	   						<h4 id = "no-answer">No Answers Available.</h4>
					  </div>
			</div>
</div>
	   		<div id = "sidebar">
	   				
	   		</div>
	   </div>
	   <div class = "clear"></div>
	   <div id = footer>
	   </div>
</body>
</html>
