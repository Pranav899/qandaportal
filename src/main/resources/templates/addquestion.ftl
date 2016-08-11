<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question And Answer Portal</title>
<link rel="stylesheet" type="text/css" href="/qandaportal/css/addquestion.css">
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script src="/qandaportal/javascript/addquestion.js"></script>
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
				<li class = "nav_items" id = "dropdown"><a class = "dorpbtn" href = "/qandaportal/rest/user/logout">Logout</a>
			
			</ul>
	   </div>
	   <div class = "clear"></div>
	   <div id = "container">
	   		<div id = "main-content">
	   			<h3 class = "main-content-element" id = "content-header">Ask Question ? </h3>
	   			<div class = "main-content-element" id = "addquestion">
	   					<form id = "question-form" method = "POST" action = "/qandaportal/rest/question/add">
	   						<div> 
	   							<textarea type = "text" id = "question-entry" name = "question"></textarea>
							</div>
							<h4 id = "keyword-entry-header">
								Enter Related Keywords :
							</h4>
							<div>
	   							<input type = "text" id = "keyword-entry" name = "keyword1">
	   							<input type = "text" id = "keyword-entry" name = "keyword2">
	   							<input type = "text" id = "keyword-entry" name = "keyword3">
							</div>
							<input type = "submit" id = "submit-question" value = "Post Question !">	
	   					</form>
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