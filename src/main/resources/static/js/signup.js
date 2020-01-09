$(document).ready(function(){
	$('#signup_btn').click(function(){
		console.log("signup clicked!!!");
		
		var username = $('#signup_username').val();
		var password = $('#signup_password').val();
		
		console.log("username : "+ username );
		console.log("password : "+ password );
		
		if(!username || !password) {
			alert("필수 항목을 채워주세요.");
			return;
		}
		
		var param = {
				username: username,
				password: password
		}
		
		$.ajax({
	        url: '/user',
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	console.log("data : " + data);
	    	console.log("s");
	    	alert("회원 가입이 되었습니다.");
	    	window.location.href = '/login';
	    }, function(err,status,request) {
	    	console.log("f");
	    	console.log("code : "+request.status+"\n"+"message :"+request.responseText+"\n"+"error : "+ err);
	    	alert("Username이 중복되었습니다.");
	    	window.location.reload();
	    });
		return false;
	});
});