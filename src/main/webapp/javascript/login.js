$(document).ready(function(){
	$("#submit").click(function(){
		event.preventDefault();
		validateLogin();
	});
});
function validateLogin(){
	var username = $("#username").val();
	var password = $("#password").val();
	var loginDetails = {
							"username" : username,
							"password" : password
						};
	alert(username);
	alert(password);
	$.ajax({
		type : "POST",
		url : "/qandaportal/rest/login",
		ContentType : "applicaton/json",
		data : JSON.stringify(loginDetails),
		success : function(){
					alert("Success");
				}
	});
}