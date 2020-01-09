$(document).ready(function() {
	$('#login_btn').click(function() {
		console.log("login clicked!!!");

		var username = $('#login_username').val();
		var password = $('#login_password').val();

		console.log("username : " + username);
		console.log("password : " + password);

		if (!username || !password) {
			alert("필수 항목을 채워주세요.");
			return;
		}

		var param = {
			username : username,
			password : password
		}

		$.ajax({
			url : "/auth",
			method : "POST",
			dataType : 'json',
			contentType : 'application/json',
			data : JSON.stringify(param)
		}).then(function(data) {
			document.cookie = "accesstoken=" + data.data.token;
			document.cookie = "userId=" + data.data.userId;
			console.log(data.data.userId);

			window.location.href = '/';
		}, function(err) {
			alert("계정 정보를 확인해주세요.");
			window.location.reload();
		});
		return false;
	});
});