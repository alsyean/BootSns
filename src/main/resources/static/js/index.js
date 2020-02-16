$(document).ready(function(){
	
	$('.text-center#btn').append('<button type="button" class="btn btn-light" id ="postAllMoreBtn" value="1">더 보기</button>');

	$('.text-center#btn').append('<button type="button" class="btn btn-outline-dark" id="postFeedMoreBtn" value="5">더 보기</button>');

	let AllPostPage = 0;
	
	var token;
	if(document.cookie.includes("accesstoken")) {
		token = document.cookie.split('token=')[1];	
	}
	
	$.ajax({
	
        url: "/postAll/" + AllPostPage
    }).then(function(data) {
        console.log("getPost date : " +JSON.stringify(data.data));
    	$.each(data.data, function(index, e) {

    		console.log("views : " + e.views);
    		
	 	       if(e.pageCheck){
	 	    	   $('#postAllMoreBtn').remove();
	 	       }
	 	       
    		$('#posts').append(
    				'<div class="card mb-4"> <div class="card-body">' 
    				+ '<h2 class="card-title">' + e.title 
    				+ '</h2> <p class="card-text">' + e.content 
    				+ '</p> <a href="/post/detail/' + e.id 
    				+ '" class="btn btn-primary">Read More &rarr;</a> </div> ' 
    				+ '<div class="card-footer text-muted"> Posted on ' + e.createdAt.split('T')[0]
    				+ ' by ' + e.user.username + getFollowInfo(e.user)
    				+ "<label style='text-align : right;'>   &nbsp;   views :" + e.views + '</label>'
    				+ '</div> </div>');
    	});
    }, function(err) {
    	console.log(err.responseJSON);
    });
	
	function getFollowInfo(user) {
		console.log("getFollowInfo : " + user.id);
		if(user.isFollow) {
			return ' <span class="unfollow" value="' + user.id + '" style="color:blue; cursor: pointer;"> Unfollow </span>';	
		} else if(user.isFollow == null){
			return '';
		} else {
			return ' <span class="follow" value="' + user.id + '" style="color:blue; cursor: pointer;"> Follow </span>';
		}
		
	}
	
	
	if(token) {
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader('accesstoken', token);
	        },
	        url: "/post/feed/0"
	    }).then(function(data) {

		    console.log("postFeed" + data.data);
		    
	    	console.log("token : " + token);
	    	$.each(data.data, function(index, e) {
	    		  if(e.pageCheck){
		 	    	   $('#postFeedMoreBtn').remove();
		 	       }
	    		$('#myfeed').append(
	    				'<div class="card mb-4"> <div class="card-body"> <h2 class="card-title">' + e.title 
	    				+ '</h2> <p class="card-text">' + e.content 
	    				+ '</p> <a href="/post/detail/' + e.id 
	    				+ '" class="btn btn-primary">Read More &rarr;</a> </div> ' 
	    				+ '<div class="card-footer text-muted"> Posted on ' + e.createdAt.split('T')[0]
	    				+ ' by ' + e.user.username + getFollowInfo(e.user)
	    				+ "<label style='text-align : right;'>   &nbsp;   views :" + e.views + '</label>'
	    				+ '</div> </div>');
	    	});
	    }, function(err) {
	    	console.log(err.responseJSON);
	    });
	}
	
	$('#save_post_btn').click(function(){
		var title = $('#create_title_text').val();
		var content = $('#create_content_text').val();
		
		console.log(title);
		console.log(content);
		
		var param = {
			title: title,
			content: content
		}
		
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader('accesstoken', token);
	        },
	        url: "/post",
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	window.location.href = '/';
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
	});
	
	$('#header_logout_btn').click(function(){
		document.cookie = "accesstoken=; expires=Thu, 01 Jan 1970 00:00:01 GMT;";
		window.location.href = '/logout';
	});
	
	$('body').on('click', '.follow', function($event) {
		console.log($(event.target).html());
		console.log($(this).html());
		console.log("this.value : "+$(this).attr('value'));
		var userId = $(this).attr('value');
		
		var param = {
			followeeId: userId
		}
		console.log("follow param : " + userId);
		
		
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader('accesstoken', token);
	        },
	        url: "/follow",
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	window.location.reload(); 	    
	    }, function(err) {
	    	alert("error" + err.responseJSON);
	    });
	});
	
	$('body').on('click', '.unfollow', function() {
		console.log("unfollow clicked!!!");
		
		var userId = $(this).attr('value');
		
		var param = {
			followeeId: userId
		}
		console.log("unFollow param : " + userId);
		
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader('accesstoken', token);
	        },
	        url: "/follow",
	        method: "DELETE",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	window.location.reload();
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
	});
	

	$('#postAllMoreBtn').on('click', function() {
		
		let postMore =  $('#postAllMoreBtn').val();
		
		AllPostPage  =  AllPostPage + parseInt(postMore) ;
		
		console.log(AllPostPage);
		
		$.ajax({
			
	        url: "/postAll/" + AllPostPage
	    }).then(function(data) {
	        console.log("getPost date : " +data.data);
	    	$.each(data.data, function(index, e) {

	 	       console.log("getPost : " + e.pageCheck);
	 	       
	 	       if(e.pageCheck){
	 	    	   $('#postAllMoreBtn').remove();
	 	       }
	 	       
	    		$('#posts').append(
	    				'<div class="card mb-4"> <div class="card-body"> <h2 class="card-title">' + e.title 
	    				+ '</h2> <p class="card-text">' + e.content 
	    				+ '</p> <a href="/post/detail/' + e.id 
	    				+ '" class="btn btn-primary">Read More &rarr;</a> </div> ' 
	    				+ '<div class="card-footer text-muted"> Posted on ' + e.createdAt.split('T')[0]
	    				+ ' by ' + e.user.username + getFollowInfo(e.user)
	    				+ "<label style='text-align : right;'>   &nbsp;   views :" + e.views + '</label>'
	    				+ '</div> </div>');
	    	});
	    }, function(err) {
	    	console.log(err.responseJSON);
	    });
	})

	let feedPage = 0;
	
	$('#postFeedMoreBtn').on('click', function() {
		
		let postMore =  $('#postFeedMoreBtn').val();
		
		feedPage  =  feedPage + parseInt(postMore) ;
		
		console.log(feedPage);
		
		$.ajax({
			
	        url: "/post/feed/" + feedPage
	    }).then(function(data) {
	        console.log("getPost date : " +data.data);
	    	$.each(data.data, function(index, e) {

	 	       console.log("getPost : " + e.pageCheck);
	 	       
	 	       if(e.pageCheck){
	 	    	   $('#postFeedMoreBtn').remove();
	 	       }
	 	       
	 	      $('#myfeed').append(
	    				'<div class="card mb-4"> <div class="card-body"> <h2 class="card-title">' + e.title 
	    				+ '</h2> <p class="card-text">' + e.content 
	    				+ '</p> <a href="/post/detail/' + e.id 
	    				+ '" class="btn btn-primary">Read More &rarr;</a> </div> ' 
	    				+ '<div class="card-footer text-muted"> Posted on ' + e.createdAt.split('T')[0]
	    				+ ' by ' + e.user.username + getFollowInfo(e.user)
	    				+ "<label style='text-align : right;'>   &nbsp;   views :" + e.views + '</label>'
	    				+ '</div> </div>');
	    	});
	       console.log("getPost date : " +data);
	       console.log("e.user : "+e.user);
	    }, function(err) {
	    	console.log(err.responseJSON);
	    });
	})
	
	
	if(token){
		$('#postAllMoreBtn').hide();
	}
	
	
	$('#myfeedBtn').on('click', function() {
		$('#postAllMoreBtn').hide();
		$('#postFeedMoreBtn').show();
	})
	
	$('#postBtn').on('click', function() {
		$('#postFeedMoreBtn').hide();
		$('#postAllMoreBtn').show();
	})
	
});

