<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question And Answer Portal</title>
<link rel="stylesheet" type="text/css" href="/qandaportal/css/reply.css">
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script src="/qandaportal/javascript/reply.js"></script>
</head>
<body id = "main">
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
				<li class = "nav_items" id = "dropdown"><a class = "dorpbtn" href = "login.html">Login/ SignUp</a>
						 <div class="dropdown-content">
      							<a href = "login.html">Login</a>
      							<a href="/qandaportal/register.html">SignUp</a>
   						 </div>
				</li>
			</ul>
	   </div>
	   <div class = "clear"></div>
	   <div id = "container">
	   		<div id = "main-content">
	   			<h3 class = "main-content-element" id = "content-header">Answer this Question ! </h3>
	   			<div class = "main-content-element" id = "question">
	   				<h4 class = "inside-element" id = "question-heading">Question :</h4>
	   				<div class = "inside-element" id="question-data"><b>${question.question}</b></div>
	   				<div class = "inside-element" id = "question-owner">
	   						Posted By : <span>${question.userId}</span>
	   				</div>
	   			</div>
	   			<form id = "answer-form" method = "POST" action = "/qandaportal/rest/answer/${question.questionId}/add">
	   			<div class = "main-content-element" id = "answer-sharing">
	   				<h4 class = "inside-element" id = "answer-heading">Post your answer here...!</h4>
	   				<textarea id="answer-entry" name = "answer"></textarea>
	   			</div>
	   			<div class = "main-content-element" id = "answer-submi">
	   				<input type = "submit" value = "Add Answer !" id = "answer-submit">
	   			</div>
	   			</form>
	   		</div>
	   		<div id = "sidebar">
	   			
	   		</div>
	   </div>
	   <div class = "clear"></div>
	   <div id = footer>
	   </div>
</body>
</html>