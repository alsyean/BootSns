<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Blog Home - Start Bootstrap Template</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<style>
body {
	padding-top: 54px;
}

@media ( min-width : 992px) {
	body {
		padding-top: 56px;
	}
}
</style>
</head>

<body>
	<#include "header.ftl">

	<div class="container">

		<#if Session.userId??>
		<div class="my-5"></div>
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link active"
				data-toggle="tab" href="#myfeed" id = "myfeedBtn">My Feed</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab" id = "postBtn"
				href="#posts">All Posts</a></li>
		</ul>

		<div class="col-md-2"></div>

		<div class="tab-content">
			<div class="col-md-2"></div>
			<div class="container tab-pane active col-md-8" id="myfeed" >
				<h1 class="my-4"></h1>
			</div>
			<div class="container tab-pane fade col-md-8" id="posts" >
				<h1 class="my-4"></h1>
			</div>
			<div class="text-center" id="btn">

				<!-- <button type="button" class="btn btn-light" id ="postAllMoreBtn" value="1">더 보기</button>

				<button type="button" class="btn btn-outline-dark" id="postFeedMoreBtn" value="5">더 보기</button> -->
				
			</div>
		</div>
		<#else>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8" id="posts">
				<h1 class="my-4"></h1>
			</div>
			
			
		</div>
		
		<div class="text-center">
				
				<button type="button" class="btn btn-primary" id ="postAllMoreBtn" value="1">더 보기</button>
						
		</div>
		</#if>

	</div>

	<#include "footer.ftl">

	<div class="modal fade" id="create_post_modal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Create Post</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="create_title_text">Title</label> <input type="text"
								class="form-control" id="create_title_text" placeholder="Title">
						</div>
						<div class="form-group">
							<label for="create_content_text">Content</label>
							<textarea class="form-control" rows="3" id="create_content_text"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="save_post_btn">Save
						Post</button>
				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<script src="js/index.js"></script>
</body>

</html>
