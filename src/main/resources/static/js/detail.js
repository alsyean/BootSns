$(document).ready(function(){
	
	$('#header_logout_btn').click(function(){
		document.cookie = "accesstoken=; expires=Thu, 01 Jan 1970 00:00:01 GMT;";
		window.location.href = '/logout';
	});
	
	var postId = $('#detail_post_id').attr("value");
	postId = postId.replace(/,/g, "");
	console.log("postId - " + postId);
	
	$.ajax({
        url: "/post/" + postId
    }).then(function(data) {
       console.log(data);
       $('#detail_title').text(data.data.title);
       $('#detail_user').text(data.data.user.username);
       $('#detail_date').text(data.data.createdAt);
       $('#detail_content').text(data.data.content);
    }, function(err) {
    	console.log(err.responseJSON);
    });
	
	$.ajax({
        url: "/comments?post_id="+postId
    }).then(function(data) {
    	console.log(data.data);
    	$.each(data.data, function(index, e) {
    		$('#comments').append(
    				'<h5 class="card-header"> writer : <span id ="comment_user" value = ' + e.user + '>' + e.user +'</span>'
    				+ '<button type="submit" class="btn btn-danger" id="delete_comment_btn" style="float:right;" data-user = ' + e.user
    				+ '>Delete</button>'+ '</h5>'
    				+ '<input type="password" class="form-control" id="comment_password" placeholder="password">'
    				+ '<div class="card-body">' 
    				+ '</h5> ' + e.comment  +'</h5>' 
    	            + '</div></div>');
    	});
       console.log(data);
    }, function(err) {
    	console.log(err.responseJSON);
    });
	
	$('.btnBD').click(function(){
		$.ajax({
	        url: "/post/" + postId
	    }).then(function(data) {
	       console.log(data);
	       $('#detail_title').text(data.data.title);
	       $('#detail_user').text(data.data.user.username);
	       $('#detail_date').text(data.data.createdAt);
	       $('#detail_content').text(data.data.content);
	    }, function(err) {
	    	console.log(err.responseJSON);
	    });
		
	})
	
	
	$('#detail_delete_btn').click(function(){
		var postId = $('#detail_post_id').attr("value");
		console.log("delete button click! - " + postId);
		$.ajax({
	        url: "/post/"+postId,
	        method: "DELETE"
	    }).then(function(data) {
	    	window.location.href = '/';
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
	});
	
	$('#modify_post_btn').click(function(){
		var postId = $('#detail_post_id').attr("value");
		var title = $('#modify_title_text').val();
		var content = $('#modify_content_text').val();
		
		console.log(postId);
		console.log(title);
		console.log(content);
		
		var param = {
			id: postId,
			title: title,
			content: content
		}
		
		$.ajax({
	        url: "/post",
	        method: "PUT",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	console.log(data);
	    	window.location.href = '/post/detail/'+postId;
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
	});
	
	$('#create_comment_btn').click(function(){
		var postId = $('#detail_post_id').attr("value");
		var user = $('#comment_user_text').val();
		var comment = $('#comment_text').val();
		var replyPassword = $('#comment_password_text').val();
		
		console.log(postId);
		console.log(user);
		console.log(comment);
		
		if(!replyPassword){
			alert('비밀번호를 입력해주세요 ')
			return ;
		}
		
		var param = {
				postId: postId,
				replyPassword : replyPassword,
				user: user,
				comment: comment
		}
		
		console.log(param);
		
		$.ajax({
			url: '/comment',
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	window.location.href = '/post/detail/'+postId;
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
	});
	
	
	$(document).on('click','#delete_comment_btn',function(){
	
		var postId = $('#detail_post_id').attr("value");
		var replyPassword = $('#comment_password').val();
		var user = $(this).data("user");

		console.log(replyPassword);
		var param = {
				postId: postId,
				replyPassword : replyPassword,
				user : user
		}
		$.ajax({
			url: '/comment/delete',
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	if(data.code == 200){
	    		alert('삭제 되었습니다.')
	    	}else{
	    		alert('실패 했습니다.')
	    	}
	    	
	    	window.location.href = '/post/detail/'+postId;
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
		
	});
	
});