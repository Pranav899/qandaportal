<!DOCTYPE html>
<html id = "default">
<head>
<meta charset="UTF-8">
<title>Question And Answer Portal</title>
<link rel="stylesheet" type="text/css" href="/qandaportal/css/admin.css">
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script src="/qandaportal/javascript/admin.js"></script>
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
				<li class = "nav_items" id = "dropdown"><a class = "dorpbtn" href = "/qandaportal/rest/user/logout">Logout</a>
				</li>
			</ul>
	   </div>
	   <div class = "clear"></div>
	    </div>
	   <div id = "container">
	   		<div id = "main-content">
	   			<table id = "table">
					<thead id = "tablehead">
							<tr >
								<th class = "tableheadrows">S.No</th>
								<th class = "tableheadrows">Question_Id</th>
								<th class = "tableheadrows">Question</th>
								<th class = "tableheadrows">Parent_Question_Id</th>
								<th class = "tableheadrows">Yes</th>
								<th class = "tableheadrows">No</th>
							</tr>
					</thead>
					<tbody id = "tablebody">
							<tr >
								<td class = "tableheadrows">S.No</td>
								<td class = "tableheadrows">Question_Id</td>
								<td class = "tableheadrows">Question</td>
								<td class = "tableheadrows">Parent_Question_Id</td>
								<td class = "tableheadrows"><input type = "button" value = "confirm" class = "buttons"></td>
								<td class = "tableheadrows"><input type = "button" value = "No" class = "buttons"></td>
							</tr>
					</tbody>
			</table>
		
			<div class = "question-display">
				<div class = "submit-form">Enter Question Id : <input type = "number" id = "question-id"> <input type = "submit" id = "submit-question-id">
				</div>
				<div class = "display-question">
				</div>
	   		</div>
	   </div>
	   <div class = "clear"></div>
	   <div id = footer>
	   </div>
</body>
</html>