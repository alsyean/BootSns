<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="/">Code Presso SNS</a>
		<#if Session.userId??>
		<div class="row">
			<button class="btn btn-secondary mx-3" id="create_btn" data-toggle="modal"
			data-target="#create_post_modal">Create Post</button>
			<button class="btn" id="header_logout_btn">Logout</button>
		</div>
		<#else>
		<div class="row">
			<a href="/signup"><button class="btn btn-secondary mr-3" id="header_signup_btn">Sign Up</button></a>
			<a href="/login"><button class="btn btn-secondary" id="header_login_btn">Login</button></a>
		</div>
		</#if>
	</div>
</nav>
