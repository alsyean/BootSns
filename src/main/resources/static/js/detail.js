$(document).ready(function(){
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
	
//	$.ajax({
//        url: "/comments?post_id="+postId
//    }).then(function(data) {
//    	$.each(data, function(index, e) {
//    		$('#comments').append(
//    				'<div class="media mb-4"><div class="media-body"><h5 class="mt-0">' + e.user
//    				+ '</h5>' + e.comment 
//    	            + '</div></div>');
//    	});
//       console.log(data);
//    }, function(err) {
//    	console.log(err.responseJSON);
//    });
	
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
		
		console.log(postId);
		console.log(user);
		console.log(comment);
		
		var param = {
				postId: postId,
				user: user,
				comment: comment
		}
		
		$.ajax({
	        url: "/comment",
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	window.location.href = '/page/detail/'+postId;
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
	});
});