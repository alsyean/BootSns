$(document).ready(function(){
	let overlapCnt = 0;

	let username = $('#signup_username').val();
	
	$('#signup_btn').click(function(){
		console.log("signup clicked!!!");
		
		var password = $('#signup_password').val();
		var email = $('#signup_email').val();
		
		console.log("username : "+ username );
		console.log("password : "+ password );
		
		
		if(overlapCnt == 0){
			alert("중복체크 해주세요");
			return;
		}
		
		if(!username || !password || !email )  {
			alert("필수 항목을 채워주세요.");
			return;
		}
		
		var check = chkEmail(email);
		
		var param = {
				username: username,
				password: password,
				email : email
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
	    	alert("이메일 인증 해주세요.");
	    	window.location.href = '/login';
	    }, function(err,status,request) {
	    	console.log("f");
	    	console.log("code : "+request.status+"\n"+"message :"+request.responseText+"\n"+"error : "+ err);
	    	alert("항목을 제대로 입력 해주세요.");
	    	window.location.reload();
	    });
		return false;
	});
	
	$('.overlap').on('click',function(){
		username = $('#signup_username').val();
		
		if(!username)  {
			alert("먼저 아이디를 입력해주세요.");
			return;
		}
		
		var param = {
				username: username
				
		}
		
		console.log("username : " + username);
		
		$.ajax({
	        url: '/user/overlap',
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	console.log("code : " + data.code);
	    	console.log("s");
	    	if(data.data){
	    		$('#signup_username').val("");
	    		alert("중복된 아이디 입니다.");
	    	}else {
	    		alert("사용 가능한 아이디입니다.");
	    		$('#signup_username').closest(".form-group").html("<span id='signup_username'>" + username +"</span>");
	    		$('.overlap').remove();
	    		overlapCnt = 1;
	    	}
	    }, function(err,status,request) {
	    	console.log("f");
	    	console.log("code : "+request.status+"\n"+"message :"+request.responseText+"\n"+"error : "+ err);	    	
	    	window.location.reload();
	    });
	})
	
	
	
	$(document).on('blur','#signup_email',function(){
		var email = $('#signup_email').val();
		var check = chkEmail(email);

		if(!check){
			alert("이메일 형식이 아닙니다");
			$(document).off('blur',"#signup_email");
		}
	});
});

function chkEmail(str) {

    var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

    if (regExp.test(str)) return true;

    else return false;

}