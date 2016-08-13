<!DOCTYPE html>
<html id = "default">
<head>
<meta charset="UTF-8">
<title>Question And Answer Portal</title>
<link rel="stylesheet" type="text/css" href="/qandaportal/css/stylesheet.css">
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script src="/qandaportal/javascript/default.js"></script>
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
				</li>
			</ul>
	   </div>
	   <div class = "clear"></div>
	   </div>
	   <div id = "container">
	   		<div id = "main-content">
	   			
	   		</div>
	   		<div id = "sidebar">
	   				
	   		</div>
	   </div>
	   <div class = "clear"></div>
	   <div id = footer>
	   </div>
</body>
</html>